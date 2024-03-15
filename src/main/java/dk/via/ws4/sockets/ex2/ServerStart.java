package dk.via.ws4.sockets.ex2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart
{
  public static void main(String[] args) throws IOException
  {
    ServerSocket serverSocket = new ServerSocket(5679);
    while (true) {
      System.out.println("Server ready for input.");
      Socket socket = serverSocket.accept();
      Server server = new Server(socket);
      Thread thread = new Thread(server);
      thread.start();
    }
  }
}
