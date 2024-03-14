package dk.via.ws4.sockets.ex1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try{
            ServerSocket serverSocket = new ServerSocket(5678);
            System.out.println("Waiting for client to connect...");

            while(true){
                //Accept client connections, prints client info
                Socket clientSocket = serverSocket.accept();
                String client = clientSocket.getInetAddress().getHostAddress();
                System.out.println("Client connected: "+client);

                //Create input and output stream
                OutputStream outputStream = clientSocket.getOutputStream();
                InputStream inputStream = clientSocket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                PrintWriter writer = new PrintWriter(outputStream);

                //Receives and prints connect message from the client
                String connectMsg = reader.readLine();
                System.out.println("Received from "+client+": "+connectMsg);

                if (!connectMsg.equals("connect")){
                    writer.println("Disconnected.");
                    writer.flush();
                    System.out.println("Sent to "+client+": Disconnected");
                    clientSocket.close();
                    System.out.println("Client disconnected: "+client);
                    continue;
                }

                //Asks for the username
                writer.println("Username?");
                System.out.println("Sent to "+client+": Username?");
                writer.flush();

                //Reads the username String
                String username = reader.readLine();
                System.out.println("Received from "+client+": "+username);

                //Asks for the password
                writer.println("Password?");
                System.out.println("Sent to "+client+": Password?");
                writer.flush();

                //Reads the password String
                String password = reader.readLine();
                System.out.println("Receives from "+client+": "+password);

                //Prints "Approved"
                writer.println("Approved");
                System.out.println("Sent to "+client+": Approved");
                writer.flush();

                //Closes the connection
                clientSocket.close();
                System.out.println("Client disconnected: "+client);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
