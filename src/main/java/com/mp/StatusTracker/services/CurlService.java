package com.mp.StatusTracker.services;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mp.StatusTracker.mail.Mailsender;

@Service
public class CurlService {

	private final OkHttpClient client = new OkHttpClient();
	
	@Autowired
	private Mailsender mail;
	
	public void servicioPrueba() throws Exception {
		String url = "https://jsonplaceholder.typicode.com/todos/1";

	    Request request = new Request.Builder()
	            .url(url)
	            .get() // GET
	            .addHeader("Content-Type", "application/json")
	            .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Respuesta exitosa: " + "servicioPrueba");
            } else {
                System.err.println("Error HTTP: " + response.code() + " --> " + response.message());
                mail.sendMail("nombreServicio", "Error HTTP: " + response.code() + " --> " + response.message());
            }
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
            mail.sendMail("nombreServicio", e.getMessage());
        }
	}
	
	//AGREGAR MAS LLAMADOS A SERVICIOS...
	
	
}
