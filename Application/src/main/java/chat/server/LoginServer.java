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

public class LoginServer
{

  public static void main(String[] args) throws IOException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    Communicator communicator=new LoginCommunicator();
    registry.bind("communicator",communicator);
    System.out.println("Server is running");

  }
}




