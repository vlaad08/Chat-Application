package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginServer
{
  public static void main(String[] args) throws IOException
  {
    ServerSocket serverSocket = new ServerSocket(8080);
    UDPBroadcaster broadcaster = new UDPBroadcaster("10.154.212.128", 8888);
    while(true) {
      System.out.println("Server is ready for input port 8080");
      Socket socket = serverSocket.accept();
      LoginCommunicator communicator = new LoginCommunicator(socket, broadcaster);
      Thread communicatorThread = new Thread(communicator);
      communicatorThread.start();
    }
  }
}
