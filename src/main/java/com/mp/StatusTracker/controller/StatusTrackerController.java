package com.mp.StatusTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mp.StatusTracker.mail.Mailsender;
import com.mp.StatusTracker.services.CurlService;

@RestController
@RequestMapping("StatusTracker")
public class StatusTrackerController {
	
	@Value("${validator.time}")
    private static int timepoValidacion;
	
	@Autowired
    private CurlService curlService;
	
	@Autowired
	private Mailsender mail;
	

	@GetMapping("/GetStatusServices")
	public String getStatus() {	
		return "StatusTracker Disponible";
	}
	
	
	@Scheduled(fixedDelayString = "${my.fixedDelay}")
	public void validacionSevricios(){
		
	    try {	
	    	curlService.servicioPrueba();
	    } catch (Exception e) {
	        System.out.println("Error al llamar al servicio: servicioPrueba" + " // MensajeError: " + e.getMessage());
	        mail.sendMail("nombreServicio", e.getMessage());
	    }
	    
	    try {	
	    	//agregar aqui mas SERVICIOS...
	    } catch (Exception e) {
	        System.out.println("Error al llamar al servicio: servicioPrueba" + " // MensajeError: " + e.getMessage());
	        mail.sendMail("nombreServicio", e.getMessage());
	    }
	    
	}
	
	

}
