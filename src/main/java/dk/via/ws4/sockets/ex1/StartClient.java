package dk.via.ws4.sockets.ex1;

import dk.via.ws4.sockets.ex1.Client;

import java.io.IOException;

public class StartClient {
    public static void main(String[] args) {

        Client client = new Client();

        try{
            boolean result = client.login("tomasko", "heslo");
            System.out.println("Login result: "+(result ? "Successful" : "Failed"));

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            client.close();
        }
    }
}
