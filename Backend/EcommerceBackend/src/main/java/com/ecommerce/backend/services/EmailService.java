package com.ecommerce.backend.services;

import com.ecommerce.backend.services.util.OrderEmailData;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class EmailService {

    //@Value("${sendgrid.sender}")
    private String senderEmail = "market.mate.ecommerce@gmail.com";

    //@Value("${sendgrid.apikey}")
    private static final String apiKey = "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";
    private static final Double marketFee = 0.05;

    public boolean sendOrderRecapEmail(OrderEmailData orderEmailData){
        String emailBody;
        try {
            String sellerBodyRecapEmail = "/src/main/java/com/ecommerce/backend/services/email/body/" +
                    "seller_order_body.html";
            String filePath = System.getProperty("user.dir") + sellerBodyRecapEmail;
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            emailBody = stringBuilder.toString();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        // put the real values in the html code:
        emailBody = emailBody.replace("**Transaction_Number**", orderEmailData.getTransactionNumber().toString());
        String pattern = "E MMM dd HH:mm:ss yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        String date = sdf.format(orderEmailData.getTransactionDate());
        emailBody = emailBody.replace("**Transaction_Date**", date);
        emailBody = emailBody.replace("**Client**", orderEmailData.getFirstName() + " " +
                                       orderEmailData.getLastName());
        String address = orderEmailData.getAddress().getStreet() + " " + String.format("%d",
                orderEmailData.getAddress().getStreet_nr()) + ", " + String.format("%d",
                orderEmailData.getAddress().getPostal_code()) + " " + orderEmailData.getAddress().getCity();
        emailBody = emailBody.replace("**Address**", address);
        emailBody = emailBody.replace("**Client_Email**", orderEmailData.getBuyerEmail());
        emailBody = emailBody.replace("**Product_Name**", orderEmailData.getProductName());
        emailBody = emailBody.replace("**Product_Quantity**", orderEmailData.getQuantity().toString());
        emailBody = emailBody.replace("**Product_Price**", orderEmailData.getPrice().toString());
        double tot = orderEmailData.getPrice()*orderEmailData.getQuantity();
        emailBody = emailBody.replace("**Total**", String.format("%.2f", tot));
        emailBody = emailBody.replace("**Fee**", String.format("%.2f", tot*EmailService.marketFee));
        emailBody = emailBody.replace("**Grand_Total**",  String.format("%.2f", tot*(1-EmailService.marketFee)));
        //System.out.println(emailBody);
        return this.send(orderEmailData.getSellerEmail(), emailBody, "You got a new order!", "html");
    }

    public boolean send(String receiverEmail, String emailBody, String subject, String type){
        Email from = new Email(this.senderEmail);
        Email to = new Email(receiverEmail);
        Content content;
        switch (type) {
            case "html" -> content = new Content("text/html", emailBody);
            case "text" -> content = new Content("text/plain", emailBody);
            default -> {
                System.out.println("Bad email type selected. Using plain text by default.");
                content = new Content("text/plain", emailBody);
            }
        }

        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(EmailService.apiKey);
        Request request = new Request();
        boolean flag = true;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            if(response.getStatusCode() != 200 && response.getStatusCode() != 202) flag = false;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            flag = false;
        }
        return flag;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }
}
