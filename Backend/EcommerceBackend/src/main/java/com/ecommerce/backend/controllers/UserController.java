package com.ecommerce.backend.controllers;

import com.ecommerce.backend.models.Address;
import com.ecommerce.backend.models.User;
import com.ecommerce.backend.models.UserAddress;
import com.ecommerce.backend.payload.request.AddAddressRequest;
import com.ecommerce.backend.payload.response.MessageResponse;
import com.ecommerce.backend.repository.AddressRepository;
import com.ecommerce.backend.repository.UserAddressRepository;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.security.jwt.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserAddressRepository userAddressRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/address/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addAddress(@Valid @RequestBody AddAddressRequest address,
                                        @RequestHeader(name = "Authorization") String token) {

        token = token.substring(7); //we just drop the word "bearer" from the token's signature
        List<Address> addressList = addressRepository.findAll();
        boolean isAddressPresent = false;
        Address newAddress = new Address();
        User currentUser = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token)).get();
        for (Address addr : addressList) {
            if (this.equalAddresses(address, addr)) {
                isAddressPresent = true; // we already have that address in the db
                System.out.println("Address in the db.");
                newAddress = addr;
                break;
            }   
        }
        if (! isAddressPresent) {
            newAddress = new Address(address.getStreet(), address.getStreet_nr(), address.getCity(),
                    address.getPostal_code(), address.getCountry(), address.getReceiver());
            addressRepository.save(newAddress);
            System.out.println("New address added in the db.");
        }
        else { // we check that the user doesn't have that address associated with him
            List <UserAddress> userAddress = userAddressRepository.findAllByUser(currentUser);
            boolean userHasAddress = false;
            for (UserAddress userAdd : userAddress) {
                Address addr = userAdd.getAddress();
                if (this.equalAddresses(address, addr)) {
                    userHasAddress = true; // we already have that address associated to the user in the db
                    break;
                }
            }
            if (userHasAddress) {
                return ResponseEntity.badRequest().
                        body(new MessageResponse("Error: This user has already this address associated with him."));
            }
        }

        UserAddress newUserAddress = new UserAddress(currentUser, newAddress, false);
        userAddressRepository.save(newUserAddress);
        return ResponseEntity.ok(new MessageResponse("Address correctly added to the User."));
    }

    @DeleteMapping("address/remove/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    //notice that this is the id of the Address not of the UserAddress
    public ResponseEntity<?> removeAddressFromUser(@PathVariable Long id,
                                                   @RequestHeader(name = "Authorization") String token) {

        if(!addressRepository.existsById(id)){ //check if we have that address in the db
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: This Address doesn't exist."));
        }
        else {
            token = token.substring(7); //we just drop the word "bearer" from the token's signature
            User currentUser = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token)).get();
            List<UserAddress> userAddresses = userAddressRepository.findAllByUser(currentUser);
            boolean userHasAddress = false;
            UserAddress targetAddress = new UserAddress();
            for (UserAddress userAddr: userAddresses){
                Address addr = userAddr.getAddress();
                if(addr.getId().equals(id)){ // check if the user is associated with that address
                    userHasAddress = true;
                    targetAddress = userAddr;
                    break;
                }
            }
            if(userHasAddress){
                userAddressRepository.deleteById(targetAddress.getId());
                return ResponseEntity.ok(new MessageResponse("Address has been removed from the user's list."));
            }
            else{
                return ResponseEntity.badRequest().
                        body(new MessageResponse("Error: This address is not associated with the user."));
            }
        }

    }

    @PutMapping("address/make/default/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    //notice that this is the id of the Address not of the UserAddress
    public ResponseEntity<?> makeDefaultAddress(@PathVariable Long id,
                                                @RequestHeader(name = "Authorization") String token) {
        if (!addressRepository.existsById(id)) { //check if we have that address in the db
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: This Address doesn't exist."));
        } else {
            token = token.substring(7); //we just drop the word "bearer" from the token's signature
            User currentUser = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token)).get();
            List<UserAddress> userAddresses = userAddressRepository.findAllByUser(currentUser);
            boolean userHasAddress = false;
            UserAddress targetAddress = new UserAddress();
            for (UserAddress userAddr : userAddresses) {
                Address addr = userAddr.getAddress();
                if (addr.getId().equals(id)) { // check if the user is associated with that address
                    userHasAddress = true;
                    targetAddress = userAddr;
                    break;
                }
            }
            if (userHasAddress) {
                this.setDefaultAddress(userAddresses, id);
                return ResponseEntity.ok(new MessageResponse("Address has been set has default address."));
            } else {
                return ResponseEntity.badRequest().
                        body(new MessageResponse("Error: This address is not associated with the user."));
            }
        }
    }
    
    private boolean equalAddresses(AddAddressRequest addr1, Address addr2){
        return addr1.getCountry().equals(addr2.getCountry()) &&
                addr1.getPostal_code().equals(addr2.getPostal_code()) &&
                addr1.getCity().equals(addr2.getCity()) &&
                addr1.getStreet().equals(addr2.getStreet()) &&
                addr1.getStreet_nr().equals(addr2.getStreet_nr()) &&
                addr1.getReceiver().equals(addr2.getReceiver());
    }

    private void setDefaultAddress(List<UserAddress> userAddresses, Long id){
        for (UserAddress userAddr : userAddresses) { //we set false all the addresses except the target one
            userAddr.setDefaultaddr(userAddr.getAddress().getId().equals(id));
            userAddressRepository.save(userAddr);
        }
    }

}