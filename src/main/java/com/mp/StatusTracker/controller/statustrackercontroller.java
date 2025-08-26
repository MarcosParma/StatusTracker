package com.mp.StatusTracker.controller;

import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("Prueba")
public class statustrackercontroller {
	
	private final RestTemplate restTemplate = new RestTemplate();

    private final mailsender mail;

    public statustrackercontroller(mailsender mail) {
        this.mail = mail;
    }
	

	@GetMapping("/pruebaResponse")
	public String pruebaResponse() {	
		
		return "Servidor Disponible";
	}
	
	@Scheduled(fixedDelay = 1000)
	public void prueba(){
		
		String URL = "http://api.creditodelacasa.com.uy/cobranzaexterna/GetStatusVersion";
	    String username = "getstatus";
	    String password = "getstatus";

	    RestTemplate restTemplate = new RestTemplate();

	    // Crear header con Basic Auth
	    HttpHeaders headers = new HttpHeaders();
	    String auth = username + ":" + password;
	    String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
	    headers.set("Authorization", "Basic " + encodedAuth);

	    HttpEntity<String> entity = new HttpEntity<>(headers);

	    try {
	        ResponseEntity<String> response = restTemplate.exchange(
	                URL,
	                HttpMethod.GET,
	                entity,
	                String.class
	        );

	        if (response.getStatusCode().is2xxSuccessful()) {
	            System.out.println("Respuesta correcta:");
	        } else {
	            System.out.println("HTTP ERROR: " + response.getStatusCodeValue());
	            mail.sendMail("Nombre Servicio", "Descripcion Sevricio");
	        }
	    } catch (Exception e) {
	        System.out.println("Error al llamar al servicio: " + e.getMessage());
	    }
	}
	
	
	
	

}
