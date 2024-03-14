package dk.via.ws4.sockets.ex2;

import java.io.IOException;

public class StartClient {
    public static void main(String[] args) {

        Client client = new Client();

        try{
            boolean result = client.connect();
            System.out.println("Login result: "+(result ? "Succesful" : "Failed"));

            client.sendMessage("Hey");
            client.sendMessage("Come to VIA");
            client.sendMessage("It's me, Tomas from 2X class");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
