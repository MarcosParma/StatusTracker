# StatusTracker

StatusTracker es un proyecto en **Java con Spring Boot** diseñado para monitorear servicios REST de forma automatizada.  
Permite ejecutar múltiples llamadas a servicios, validar la respuesta HTTP, y enviar alertas por correo en caso de error o fallo de comunicación.

---

## Características

- Monitoreo programado de servicios REST mediante `@Scheduled`.
- Soporte para llamadas GET/POST con headers configurables.
- Registro de errores y envío automático de correos cuando un servicio falla.
- Configuración sencilla mediante `application.properties`.
- Extensible: se pueden agregar nuevos servicios simplemente creando métodos en `CurlService`.

---

## Estructura del Proyecto

- `com.mp.StatusTracker.mail.Mailsender`  
  Servicio encargado de enviar mails de alerta en caso de error.

- `com.mp.StatusTracker.services.CurlService`  
  Contiene los métodos que ejecutan los servicios REST a monitorear.  
  Cada método maneja su propia ejecución, validación de respuesta y envío de mails si falla.

- `com.mp.StatusTracker.controller.StatusTrackerController`  
  Contiene el endpoint REST de prueba `/StatusTracker/GetStatusServices` y el método `validacionSevricios()` que ejecuta periódicamente todos los servicios definidos en `CurlService`.

- `com.mp.StatusTracker.config.SpringSecurityConfig`  
  Configura seguridad Basic Auth para los endpoints REST.  
  Actualmente usa `NoOpPasswordEncoder` para pruebas.

- `StatusTrackerApplication`  
  Clase principal que arranca la aplicación y habilita el scheduling.

---

## Configuración de Variables

Todas las variables se definen en `src/main/resources/application.properties`:

```properties
# Intervalo de ejecución de los servicios en milisegundos
my.fixedDelay=5000

# Configuración de usuario y contraseña para Spring Security (Basic Auth)
app.security.user=prueba123
app.security.password=prueba123

# Configuración de correo (SMTP)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=TU_CORREO@gmail.com
spring.mail.password=TU_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.debug=true

# Puerto de la aplicación
server.port=9090
