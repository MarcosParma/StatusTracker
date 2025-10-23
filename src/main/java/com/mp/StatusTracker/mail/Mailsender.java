package com.mp.StatusTracker.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Mailsender {
	
	@Autowired
    private JavaMailSender mailSender;
	
	public void sendMail(String servicio, String descripcion) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("tucorreo@gmail.com"); //Reemplazar por el Correo Destinatario de la Alerta
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
