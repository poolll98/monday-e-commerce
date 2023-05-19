package com.ecommerce.backend.controllers;
import com.ecommerce.backend.models.CartItem;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.ShoppingCart;
import com.ecommerce.backend.models.User;
import com.ecommerce.backend.payload.request.AddCartItemRequest;
import com.ecommerce.backend.payload.request.UpdateQCartItemRequest;
import com.ecommerce.backend.payload.response.AddElementMessage;
import com.ecommerce.backend.payload.response.GetCartItem;
import com.ecommerce.backend.payload.response.MessageResponse;
import com.ecommerce.backend.repository.CartItemRepo;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.ShopCartRepo;

import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/shopcart")
public class ShopController {

    @Autowired ShopCartRepo shopRepo;
    @Autowired CartItemRepo cartRepo;
    @Autowired ProductRepository prodRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addCartItem(@Valid @RequestBody AddCartItemRequest item,
                                         @RequestHeader(name = "Authorization") String token) {
        /* Policy: we can have just one active shopping cart at the time for each user.
           If we try to add a new item to a shopping cart, if the user doesn't have an active shopping cart
           we first create a new one, otherwise we add that item to the current active shopping cart.
         */

        token = token.substring(7); //we just drop the word "bearer" from the token's signature
        User currentUser = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token)).get();
        ShoppingCart currentShoppingCart = null;
        boolean userHasAnActiveCart = false;
        List<ShoppingCart> cartList =  shopRepo.findByIsActiveAndUser(true, currentUser);
        if(cartList.size() != 0){
            currentShoppingCart = cartList.get(0);
            userHasAnActiveCart = true;
        }

        /* some checks ... */
        String message = "";
        if (! prodRepo.existsById(item.getProdId())){
            message += "Error: This product doesn't exist.";
        }
        if (userHasAnActiveCart &&
                (! cartRepo.findProductInTheCartById(currentShoppingCart.getId(), item.getProdId()).isEmpty())) {
            message += "Error: This Product is already in this cart.";
        }
        if (!message.equals("")){
            return ResponseEntity.badRequest().body(new MessageResponse(message));
        }

        if(! userHasAnActiveCart){ //we create the shopping cart if the user doesn't have one
            shopRepo.save(new ShoppingCart(currentUser, true));
            currentShoppingCart = shopRepo.findByIsActiveAndUser(true, currentUser).get(0);
        }

        /* now we can add the cart item to the current shopping cart */
        Product product = prodRepo.findById(item.getProdId()).get();
        CartItem itemAdd = new CartItem(item.getQuantity(), currentShoppingCart,  product);
        cartRepo.save(itemAdd);

        Long cartItemId = cartRepo.findProductInTheCartById(currentShoppingCart.getId(), item.getProdId()).get(0).getId();
        message = "";
        if (! userHasAnActiveCart) message += "A new Shopping has been created.";
        message += " The product has been added to the cart.";
        return ResponseEntity.ok(new AddElementMessage(message, cartItemId)); // id of the new CartItem
     }        

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> removeCartItem(@PathVariable Long id) { //notice that this is the id of the CartItem not of the Product
        /*
        Policy: if the cart item that we remove is the last one in that shopping cart, we remove also the shopping cart.
         */
        if(! cartRepo.existsById(id)){
            return ResponseEntity.status(404).body("Error: This Item is not present in the Cart.");
        }
        CartItem cartItem = cartRepo.findById(id).get();
        ShoppingCart shoppingCart = cartItem.getShoppingCart();
        cartRepo.deleteById(id);
        String message = "Item has been removed from the cart.";
        if(cartRepo.countDistinctByShoppingCart(shoppingCart) == 0){
            shopRepo.deleteById(shoppingCart.getId());
            message += " The shopping cart has been delete since it was empty.";
        }
        return ResponseEntity.ok(new MessageResponse(message));
    }

    @PutMapping("/editq")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> editCartItem(@RequestBody UpdateQCartItemRequest updateQCartItemRequest) {
        if (!cartRepo.existsById(updateQCartItemRequest.getCartItemId())) {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: This CartItem doesn't exist."));
        }
        if(updateQCartItemRequest.getQuantity() <= 0){
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: quantity must be greater then 0."));
        }
        CartItem updatedCartItem = cartRepo.findById(updateQCartItemRequest.getCartItemId()).get();
        updatedCartItem.setQuantity(updateQCartItemRequest.getQuantity());
        cartRepo.save(updatedCartItem);

        return ResponseEntity.ok(new MessageResponse(updatedCartItem.getId().toString()+": quantity updated."));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getCurrentShoppingCart(@RequestHeader(name = "Authorization") String token) {
        token = token.substring(7); //we just drop the word "bearer" from the token's signature
        User currentUser = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token)).get();
        ShoppingCart currentShoppingCart = null;
        List<ShoppingCart> cartList =  shopRepo.findByIsActiveAndUser(true, currentUser);
        if(cartList.size() == 0){
            return  ResponseEntity.ok(new ArrayList<>());
        }
        else{
            List<CartItem> itemsList = cartRepo.findCartItemsByCartId(cartList.get(0).getId());
            List<GetCartItem> resultList = itemsList.stream().map(p -> {
                return new GetCartItem(p.getProduct().getId(),
                        p.getProduct().getName(), p.getProduct().getMedia(), p.getProduct().getPrice(), p.getQuantity());
            }).toList();
            return ResponseEntity.ok(resultList);
        }
    }

}