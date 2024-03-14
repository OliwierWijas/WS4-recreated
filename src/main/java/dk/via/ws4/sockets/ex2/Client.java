package dk.via.ws4.sockets.ex2;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    public Client(){
        try{
            //Connects to the server running on localhost and port "5679"
            socket = new Socket("localhost", 5679);

            //Creates input and output stream
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            writer = new PrintWriter(outputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean connect() throws IOException {
        try{
            //Sends "connect" String to the server
            writer.println("connect");
            writer.flush();
            System.out.println("Sent to server: connect");

            //Receives "Connected"/"Disconnected" from server
            String connectionResult = reader.readLine();
            System.out.println("Received from server: "+connectionResult);

            return connectionResult.equalsIgnoreCase("Connected");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void close() throws IOException{
        try {
            //Sends disconnect to the server
            writer.println("disconnect");
            writer.flush();
            System.out.println("Sent to server: disconnect");

            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void sendMessage(String message) throws IOException{
        writer.println(message);
        writer.flush();
        System.out.println("Sent tp server: "+message);
    }
}
