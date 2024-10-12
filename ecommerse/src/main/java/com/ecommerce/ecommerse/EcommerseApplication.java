package com.ecommerce.ecommerse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EcommerseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerseApplication.class, args);


//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String rawPassword = "adminpassword";  // Replace with your raw password
//		String encodedPassword = encoder.encode(rawPassword);
//		System.out.println(encodedPassword);
	}

}
