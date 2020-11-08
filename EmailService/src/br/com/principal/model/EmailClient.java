/**
 * License: MIT Copyright 2020 - Marcos Paulo Paixão < marcospaixaodeveloper@gmail.com >
 * Date...: 2020-11-03
 * Project: TorrentzFilmes - ADS Senai Fatesg
 * Goal...: TCP/IP Message Sending Server
 * Version: 1.0.0
 * @LicenseHeader
 */

package br.com.principal.model;

import java.io.Serializable;

/**
 *
 * @author marcos
 */
public class EmailClient implements Serializable{
    
    /* Attributes */
    private String emailSender = "";
    private String password = "";
    private String emailRecipient = "";
    private String subject = "";
    private String message = "";
    private String token = "";
    
    /* Methods */
    /**
     * 
     * @param emailRecipient
     * @param subject
     * @param message
     * @param token 
     */
    public EmailClient(String emailRecipient,  String subject, String message, String token ){
          this("development.suporte@gmail.com", "dev304045",emailRecipient,subject,message, token);
    }
    
   /**
    * 
    * @param emailFrom
    * @param password
    * @param emailRecipient
    * @param subject
    * @param message
    * @param token 
    */
    public EmailClient(String emailFrom, String password,String emailRecipient,  String subject, String message, String token){
        this.emailSender = emailFrom;
        this.password = password;
        this.emailRecipient = emailRecipient;
        this.subject = subject;
        this.message = message;
        this.token = token;
    }
   
    /**
     * @return the emailSender
     */
    public String getEmailSender() {
        return emailSender;
    }

    /**
     * @param emailSender the emailFrom to set
     */
    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param senha the password to set
     */
    public void getPassword(String senha) {
        this.password = senha;
    }

    /**
     * @return the emailRecipient
     */
    public String getEmailRecipient() {
        return emailRecipient;
    }

    /**
     * @param emailRecipient the emailRecipient to set
     */
    public void setEmailRecipient(String emailRecipient) {
        this.emailRecipient = emailRecipient;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
    
}
