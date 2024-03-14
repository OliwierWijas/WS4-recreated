package dk.via.ws4.sockets.ex2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5679);
            System.out.println("Waiting for client to connect...");

            while (true) {
                try {
                    //Accept client connections, prints client info
                    Socket clientSocket = serverSocket.accept();
                    String client = clientSocket.getInetAddress().getHostAddress();
                    System.out.println("Client connected: " + client);

                    //Inputs and outputs
                    OutputStream outputStream = clientSocket.getOutputStream();
                    InputStream inputStream = clientSocket.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    PrintWriter writer = new PrintWriter(outputStream);

                    //Receives and prints message from client
                    String connectMsg = reader.readLine();
                    System.out.println("Received from " + client + ": " + connectMsg);

                    //Checks if message from client is "connect"
                    if (!"connect".equalsIgnoreCase(connectMsg)) {
                        writer.println("Disconnected.");
                        writer.flush();
                        System.out.println("Sent to " + client + ": Disconnected");
                    } else {
                        writer.println("Connected");
                        writer.flush();
                        System.out.println("Sent to " + client + ": Connected");

                        String message;
                        while ((message = reader.readLine()) != null) {
                            System.out.println(client + " says: " + message);
                        }
                        System.out.println("Client disconnected: " + client);
                    }
                } catch (IOException e) {
                    System.err.println("xxx");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("xxxMM");
            e.printStackTrace();
        }
    }
}
