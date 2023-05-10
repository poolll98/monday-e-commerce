package com.ecommerce.backend.controllers;

import com.ecommerce.backend.models.Address;
import com.ecommerce.backend.models.CartItem;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.Purchase;
import com.ecommerce.backend.models.PurchaseProduct;
import com.ecommerce.backend.models.ShoppingCart;
import com.ecommerce.backend.models.User;
import com.ecommerce.backend.models.UserPayment;
import com.ecommerce.backend.payload.request.PurchaseRequest;
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
import com.ecommerce.backend.services.EmailService;
import com.ecommerce.backend.services.util.OrderEmailData;
import org.apache.commons.lang3.tuple.Pair;
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
            List<OrderEmailData> emailData = new ArrayList<>();

            Address ordadr = addrRepo.findById(purchaseRequest.getAddressId()).get();

            List <CartItem> cartitems = cartRepo.findCartItemsByCartId(cart.getId());

            float totalprice = 0;
            List <Product> prodsInCart = new ArrayList<>();

            for (CartItem cartItem : cartitems) {
                float price = cartItem.getProduct().getPrice() * cartItem.getQuantity();
                totalprice += price;
                prodsInCart.add(cartItem.getProduct());
                emailData.add(new OrderEmailData(cartItem.getProduct().getSeller().getEmail(), new Date(),
                        currentUser.getFirstname(), currentUser.getLastname(), ordadr, currentUser.getEmail(),
                        cartItem.getProduct().getName(), cartItem.getQuantity(), cartItem.getProduct().getPrice()));
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
            for(OrderEmailData orderEmailData: emailData) orderEmailData.setTransactionNumber(purchase_id);
            String message = "Order created successfully. ";
            message += this.sendRecapEmailToSellers(emailData);
            return ResponseEntity.ok(new AddElementMessage(message, purchase_id));
        }
        else
        {
            return ResponseEntity.status(402).body("Payment Service 10% Probability Failure.");
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

    private String sendRecapEmailToSellers(List<OrderEmailData> emailData){
        final List<String> emailMessages = new ArrayList<>();
        emailData.forEach(d -> {boolean status = new EmailService().sendOrderRecapEmail(d);
            if(status){
                System.out.println("Email correctly send to: " + d.getSellerEmail());
                emailMessages.add("Email correctly send to: " + d.getSellerEmail()+". ");
            }
            else{
                System.out.println("Failed to send to: "+ d.getSellerEmail());
                emailMessages.add("Failed to send to: "+ d.getSellerEmail()+". ");
            }
        });
        StringBuilder finalMessage = new StringBuilder();
        for(String message: emailMessages) finalMessage.append(message);
        return finalMessage.toString();
    }

}
