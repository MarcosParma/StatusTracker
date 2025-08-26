package com.mp.StatusTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class mailsender {
	
	@Autowired
    private JavaMailSender mailSender;
	
	public void sendMail(String servicio, String descripcion) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("marcos.parma.sanchez@gmail.com");
        message.setSubject("ERROR SERVICIO: " + servicio);
        message.setText("ERROR de Comunicacion en el servicio: " + servicio + " // Descripcion: " + descripcion);
        try {
            mailSender.send(message);
            System.out.println("[OK] Mail enviado correctamente");
        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo enviar el mail: " + e.getMessage());
        }
    }

}
