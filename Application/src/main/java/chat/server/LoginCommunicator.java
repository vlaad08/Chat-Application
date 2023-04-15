package chat.server;

import chat.model.Message;
import chat.shared.Communicator;
import com.google.gson.Gson;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class LoginCommunicator extends UnicastRemoteObject implements Communicator
{
  private final Gson gson;
  private final RemotePropertyChangeSupport<String> support;
  private  final int connectedClients = 0;

  public LoginCommunicator() throws RemoteException
  {
    this.support=new RemotePropertyChangeSupport<>();
    gson=new Gson();
  }

  @Override public void sendMessage(Message message) throws IOException
  {
    String m= gson.toJson(message);
    support.firePropertyChange("messageSentClient",null,m);
  }


  @Override public void addPropertyChangeListener(RemotePropertyChangeListener<String> listener) throws RemoteException
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(RemotePropertyChangeListener<String> listener) throws RemoteException
  {
    support.removePropertyChangeListener(listener);
  }
}
