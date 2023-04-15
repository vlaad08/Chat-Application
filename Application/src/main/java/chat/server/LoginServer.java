package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LoginServer
{
  public static void main(String[] args)
      throws IOException, AlreadyBoundException
  {
    ServerSocket serverSocket = new ServerSocket(8080);
    Registry registry = LocateRegistry.createRegistry(1099);
    while(true) {
      System.out.println("Server is ready for input port 8080");
      Socket socket = serverSocket.accept();
      LoginCommunicator communicator = new LoginCommunicator(socket);
      registry.bind("login",communicator);
      Thread communicatorThread = new Thread(communicator);
      communicatorThread.start();
    }




  }
}
