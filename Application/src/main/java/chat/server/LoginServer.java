package chat.server;

import chat.shared.Communicator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LoginServer {

  public static void main(String[] args) throws IOException, AlreadyBoundException {
    ServerSocket serverSocket = new ServerSocket(8080);
    Registry registry = LocateRegistry.getRegistry(1099);

    while (true) {
      System.out.println("Server is ready for input on port 8080");
      Socket socket = serverSocket.accept();
      Communicator communicator = new LoginCommunicator(socket);
      Remote loginCommunicator=UnicastRemoteObject.exportObject(communicator,8080);
      registry.bind("login", loginCommunicator);
      Thread communicatorThread = new Thread((Runnable) loginCommunicator);
      communicatorThread.start();
    }

  }

}


