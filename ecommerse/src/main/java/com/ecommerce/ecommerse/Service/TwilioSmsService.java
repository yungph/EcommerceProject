package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.Models.Order;
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.rest.lookups.v1.PhoneNumber;
import com.twilio.type.PhoneNumber;
//import lombok.Value;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class TwilioSmsService {

    @Value("${twilio.account.sid}")
    String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    @Autowired
    public TwilioSmsService(@Value("${twilio.account.sid}") String accountSid,
                            @Value("${twilio.auth.token}") String authToken) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        Twilio.init(accountSid, authToken); // Initialize Twilio after properties are set
    }

    public void sendSmsToSeller(Order order) {
        String toPhoneNumber = getSellerPhoneNumber(); // Retrieve seller's phone number
        String message = "New Order Placed: Order ID " + order.getId() + ", Product: " + order.getProduct().getName();

        try {
            Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(fromPhoneNumber),
                    message
            ).create();
        } catch (Exception e) {
            // Log the exception
            System.err.println("Failed to send SMS: " + e.getMessage());
        }
    }

    private String getSellerPhoneNumber() {
        // Replace this with actual logic to retrieve the seller's phone number
        return "+201207355500";
    }
}


