package com.ecommerce.backend.services;

import com.ecommerce.backend.services.util.OrderEmailData;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class EmailService {

    @Value("${sendgrid.sender}")
    private String senderEmail;
    @Value("${sendgrid.apikey}")
    private static String apiKey;

    public static void main(String[] args) throws IOException {
        Email from = new Email("market.mate.ecommerce@gmail.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("poolceffa98@gmail.com");
        Content content = new Content("text/html", "<b>and easy to do anywhere, even with Java</b>");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

    public boolean sendOrderRecapEmail(String receiverEmail, OrderEmailData orderEmailData){
        return false;
    }

    public boolean send(String receiverEmail, String emailBody, String subject, String type){
        Email from = new Email(this.senderEmail);
        Email to = new Email();
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
