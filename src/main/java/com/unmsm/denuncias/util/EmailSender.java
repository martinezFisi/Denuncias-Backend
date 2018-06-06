package com.unmsm.denuncias.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
	
	public static final String EMAIL_SENDER = "antonymartinez12@gmail.com";
	public static final String EMAIL_PASSWORD_SENDER = "sistemasfisi1";
	
	 public static void sendMail(String emailReceptor, String mensaje)
     {
         //Mail
         Session session;
         //Conjunto de valores necesarios para la conexión
         Properties p = new Properties();
         //Mensaje
         MimeMessage message;
         
         //Nombre del host de correo, es smtp.gmail.com
         p.setProperty( "mail.smtp.host", "smtp.gmail.com");
         //TLS si está disponible
         p.setProperty( "mail.smtp.starttls.enable", "true");
         //Puerto de gmail para envio de correos
         p.setProperty( "mail.smtp.port", "587");
         //Nombre del usuario
         p.setProperty( "mail.smtp.user", EMAIL_SENDER);
         //Si requiere o no usuario y password para conectarse
         p.setProperty( "mail.smtp.auth", "true");
             
         //Instanciamos una session
         session = Session.getDefaultInstance( p );
         //Ver errores en consola
         session.setDebug( true );
             
         //Instanciamos un mensaje
         message = new MimeMessage( session );
         
         try
         {
             //Quién envía el correo
             message.setFrom( new InternetAddress(EMAIL_SENDER) );
             //A quién va dirigido
             message.addRecipient( Message.RecipientType.TO, new InternetAddress(emailReceptor) );
             //Asunto
             message.setSubject( "Sistema de Denuncias Ciudadanas" );
             
             //Mensaje de texto
             BodyPart texto = new MimeBodyPart();
             texto.setText(mensaje);
         
             //Imagen adjunta
//             BodyPart adjunto = new MimeBodyPart();
//             adjunto.setDataHandler( new DataHandler( new FileDataSource("intruso"+contadorIntruso+".png") ) );
//             adjunto.setFileName( "intruso"+contadorIntruso+".png" );
         
             //Juntamos el texto y la imagen adjunta
             MimeMultipart multiparte = new MimeMultipart();
             multiparte.addBodyPart(texto);
//            multiparte.addBodyPart(adjunto);
             
             //Mensaje a enviar
             message.setContent( multiparte );
             
             //Enviar el mensaje
             Transport t = session.getTransport("smtp");
             //Establecer conexion
             t.connect(EMAIL_SENDER, EMAIL_PASSWORD_SENDER);
             //Enviar
             t.sendMessage( message, message.getAllRecipients() );
             
             //Cerrar conexion
             t.close();
             
         }
         catch( Exception e )
         {
             System.out.println("Error: "+e.toString());
         }
         
         
         
         
     }
	
}
