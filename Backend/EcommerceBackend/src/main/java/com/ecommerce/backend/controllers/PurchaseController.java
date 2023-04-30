package com.ecommerce.backend.controllers;

import com.ecommerce.backend.models.Address;
import com.ecommerce.backend.models.CartItem;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.Purchase;
import com.ecommerce.backend.models.PurchaseProduct;
import com.ecommerce.backend.models.ShoppingCart;
import com.ecommerce.backend.models.User;
import com.ecommerce.backend.models.UserPayment;
import com.ecommerce.backend.payload.request.AddCartItemRequest;
import com.ecommerce.backend.payload.request.PurchaseRequest;
import com.ecommerce.backend.payload.request.UpdateQCartItemRequest;
import com.ecommerce.backend.payload.response.AddElementMessage;
import com.ecommerce.backend.payload.response.MessageResponse;
import com.ecommerce.backend.repository.AddressRepository;
import com.ecommerce.backend.repository.CartItemRepo;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.PurchaseProductRepo;
import com.ecommerce.backend.repository.ShopCartRepo;
import com.ecommerce.backend.repository.UserPaymentRepo;
import com.ecommerce.backend.repository.PurchaseRepository;

import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/order")
public class PurchaseController {
    

    @Autowired
    UserRepository userRepository;

    @Autowired 
    ProductRepository prodRepo;

    @Autowired
    PurchaseRepository purchaseRepo;

    @Autowired
    CartItemRepo cartRepo;

    @Autowired
    ShopCartRepo shopRepo;

    @Autowired
    AddressRepository addrRepo;

    @Autowired
    UserPaymentRepo userPayRepo;

    @Autowired
    PurchaseProductRepo purhcaseProdRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

    
    public ResponseEntity<?> addPurchase(@Valid @RequestBody PurchaseRequest purchaseRequest,
                                        @RequestHeader(name = "Authorization") String token){

        token = token.substring(7); //we just drop the word "bearer" from the token's signature
        User currentUser = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token)).get();

        ShoppingCart cart = shopRepo.findByIsActiveAndUser(true, currentUser).get(0);

        boolean paymentok = PaymentService();
        System.out.println("paymentok is "+ paymentok);

        if (paymentok){
            Address ordadr = addrRepo.findById(purchaseRequest.getAddressId()).get();

            List <CartItem> cartitems = cartRepo.findCartItemsByCartId(cart.getId());

            float totalprice = 0;
            List <Product> prodsInCart = new ArrayList<>();

            for (CartItem cartItem : cartitems) {
                float price = cartItem.getProduct().getPrice() * cartItem.getQuantity();
                totalprice += price;
                prodsInCart.add(cartItem.getProduct());
            }

            UserPayment paymentMethod = userPayRepo.findById(purchaseRequest.getPaymentId()).get();
            // add purchase record
            Purchase order = new Purchase(currentUser, new Date(), true, ordadr, totalprice, paymentMethod, cart);
            this.purchaseRepo.save(order);
            Purchase usePurchase = purchaseRepo.findPurchasesByUser(currentUser).get(purchaseRepo.
                    findPurchasesByUser(currentUser).size()-1); //get the last record
            for (Product prodsincart : prodsInCart){
                PurchaseProduct newProdPurchase = new PurchaseProduct(usePurchase,prodsincart);
                this.purhcaseProdRepo.save(newProdPurchase);
            }
            cart.setActive(false);
            this.shopRepo.save(cart);
            Long purchase_id = usePurchase.getId();
            return ResponseEntity.ok(new AddElementMessage("Order created successfully.", purchase_id));
        }
        else
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Payment Service 10% Probability Failure."));
        }
    }
    
    public boolean PaymentService (){

        Random r = new Random();
        double rangeMin = 0;
        double rangeMax = 1;

        boolean paymentprocess = true;
        double status = rangeMin + (rangeMax - rangeMin) * r.nextDouble();

        if (status > 0.9) {paymentprocess = false;}

        return paymentprocess;
    }

}
