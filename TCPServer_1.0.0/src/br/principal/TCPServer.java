/**
 * License: MIT Copyright 2020 - Marcos Paulo Paixão < marcospaixaodeveloper@gmail.com >
 * Date...: 2020-11-03
 * Project: TorrentzFilmes - ADS Senai Fatesg
 * Goal...: TCP/IP Message Sending Server
 * Version: 1.0.0
 * @LicenseHeader
 */
package br.principal;

import br.principal.model.EmailClient;
import br.principal.model.EmailServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author marcos
 */
public class TCPServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        int port = 6789;
        ServerSocket welcomeSocket = new ServerSocket(port);
        while(true){
            
            System.out.println("Servidor aguardando o client!");
            Socket conectionSocket = welcomeSocket.accept();
            ObjectInputStream inFromClient = new ObjectInputStream(conectionSocket.getInputStream());
            ObjectOutputStream outToClient = new ObjectOutputStream(conectionSocket.getOutputStream());
            
            try {
                EmailClient emailClient = (EmailClient) inFromClient.readObject();
                
                if(authenticated(emailClient.getToken())){


                    EmailServer serve = new EmailServer(emailClient);
                    String message = serve.enviar(emailClient.getEmailRecipient(), emailClient.getSubject(), emailClient.getMessage());

                    System.out.println(message);

                    outToClient.writeObject(message);
                }else{
                    throw new RuntimeException("Token inválido!");
                }
                
            } catch (RuntimeException | IOException | ClassNotFoundException e) {
                outToClient.writeObject(e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }
    private static boolean authenticated(String senha){
        return (senha.equals("39389f5e019a3f4c462b92206bd9d0267211dd9c6e4e7fa8db66250fcca22f80"));
    }
}
