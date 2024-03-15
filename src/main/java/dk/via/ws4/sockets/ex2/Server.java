package dk.via.ws4.sockets.ex2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.AsynchronousCloseException;

public class Server implements Runnable{
    private Socket socket;

    public Server(Socket socket)
    {
        this.socket = socket;
    }

    public synchronized void communicate() throws IOException
    {
        try {
            String client = socket.getInetAddress().getHostAddress();
          OutputStream outputStream = socket.getOutputStream();
          InputStream inputStream = socket.getInputStream();
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader);
          PrintWriter writer = new PrintWriter(outputStream);
            loop:while (true) {
                    String message = reader.readLine();

                    if (message != null)
                    {
                      if (message.equalsIgnoreCase("connect"))
                      {
                        writer.println("Connected");
                        writer.flush();
                        System.out.println("Sent to " + client + ": Connected");
                      }
                      else if (message.equalsIgnoreCase("disconnect"))
                      {
                        writer.println("Disconnected.");
                        writer.flush();
                        System.out.println("Sent to " + client + ": Disconnected");
                        break loop;
                      }
                      else
                      {
                        System.out.println(client + " says: " + message);
                      }
                    }
                }
        } finally {
            socket.close();
        }
    }

    @Override public void run()
    {
        try {
            communicate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
