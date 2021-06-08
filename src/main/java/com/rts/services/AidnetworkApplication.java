package com.rts.services;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class AidnetworkApplication {
	  static String FB_BASE_URL="https://reliefmanagement.firebaseio.com";
	public static void main(String[] args) {
		
		SpringApplication.run(AidnetworkApplication.class, args);
		 try {
	            FirebaseOptions options = new FirebaseOptions.Builder().setServiceAccount(new ClassPathResource("/google-services.json").getInputStream())
	                    .setDatabaseUrl(FB_BASE_URL)
	                    .build();
	            if(FirebaseApp.getApps().isEmpty()) { //<--- check with this line
	                FirebaseApp.initializeApp(options);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

}
