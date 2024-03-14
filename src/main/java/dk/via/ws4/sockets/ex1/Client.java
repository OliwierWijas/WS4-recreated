package dk.via.ws4.sockets.ex1;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client(){
        try{
            //Connects to the server running on localhost and port "5678"
            socket = new Socket("localhost", 5678);

            //Create input and output stream
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            writer = new PrintWriter(outputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public boolean login(String username, String password) throws IOException{
        try{
            //Sends "connect" to the server
            writer.println("connect");
            System.out.println("Sent to server: connect");
            writer.flush();

            //Receives username request from server
            String usernameReq = reader.readLine();
            System.out.println("Received from server: "+usernameReq);

            //If response from server is not "Username?", throws an exception
            if (!usernameReq.equals("Username?")){
                throw new IOException("Unexpected response from server.");
            }

            //Sends username to the server
            writer.println(username);
            System.out.println("Sent to server: "+username);
            writer.flush();

            //Receives password request from server
            String passwordReq = reader.readLine();
            System.out.println("Received from server: "+passwordReq);

            //If response from server is not "Password?", throws an exception
            if (!passwordReq.equals("Password?")){
                throw new IOException("Unexpected response from server.");
            }

            //Sends password to the server
            writer.println(password);
            System.out.println("Sent to server: "+password);
            writer.flush();

            //Receives "Approved"/"Disconnected" from server
            String response = reader.readLine();
            System.out.println("Received from server: "+response);

            //Checks if response is "Approved"
            return response.equalsIgnoreCase("Approved");
        }catch (IOException e){
            e.printStackTrace();
            throw e;
        }
    }

    public void close(){
        try{
            //Sends disconnect to the server
            writer.println("disconnect");
            System.out.println("Sent to server: disconnect");
            writer.flush(); //Flushes the data before sending to the server

            //Receives response from server
            //String response = reader.readLine();
            //System.out.println("Received from server: "+response);

            //  I set these "reading commands" from server as a comment, because when it was there, it was
            //  throwing an exception, because the client wanted to read from the server, but
            //  the connection was already closed from server's end...


            //Closes the socket
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


