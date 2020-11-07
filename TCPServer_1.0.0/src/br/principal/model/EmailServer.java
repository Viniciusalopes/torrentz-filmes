/**
 * License: MIT Copyright 2020 - Marcos Paulo Paixão < marcospaixaodeveloper@gmail.com >
 * Date...: 2020-11-03
 * Project: TorrentzFilmes - ADS Senai Fatesg
 * Goal...: TCP/IP Message Sending Server
 * Version: 1.0.0
 * @LicenseHeader
 */
package br.principal.model;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author marcos
 */
public class EmailServer {
    
    /* Attributes */
    private String hostname = "";
    private int port = 0;
    private String emailSender = "";
    private String password = "";
    private SimpleEmail email = null;
    
    /* Methods */
    /**
     * @default
     */
    public EmailServer(){
        this("development.suporte@gmail.com", "dev304045");
    }
    
    /**
     * 
     * @param emailSender
     * @param password 
     */
    public EmailServer(String emailSender, String password){
        this.hostname = "smtp.gmail.com";
        this.port = 465;
        this.emailSender = emailSender;
        this.password = password;
        this.email = new SimpleEmail();

    }
    public EmailServer(EmailClient client){
        this(client.getEmailSender(),client.getPassword());
    }
    
   /**
    * 
    * @param emailRecipient
    * @param subject
    * @param message
    * @return 
    */
    public String enviar(String emailRecipient, String subject, String message){
        this.configure();
        try {
            email.setFrom(emailSender);//Email Sender
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(emailRecipient);
            email.send();
            return "Email enviado para " + emailRecipient +" com sucesso!";
        } catch (EmailException e) {
            return e.getMessage();
        }
    }
    
    /**
     * Email configuration
     * @config
     */
    private void configure(){
        email.setHostName(hostname);//Configure google host (gmail)
        email.setSmtpPort(port);//Configure google host port
        email.setAuthenticator(new DefaultAuthenticator(emailSender, password));//Perssonal Authentication email
        email.setSSLOnConnect(true);//Activating connection securely
    }
}
