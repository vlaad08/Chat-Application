package chat.server;

import chat.model.Message;
import chat.shared.Communicator;
import com.google.gson.Gson;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.io.*;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LoginCommunicator implements Communicator
{
  private Socket socket;
  private Gson gson;
  private final RemotePropertyChangeSupport<String> support;
  //private  int connectedClients = 0;

  public LoginCommunicator()
      throws RemoteException, AlreadyBoundException
  {
    //this.socket = socket;
    this.support=new RemotePropertyChangeSupport<>();
    support.addPropertyChangeListener(this);
  }

  public void communicate(String message) throws IOException
  {
    System.out.println(message);
    support.firePropertyChange("MessageSentServer", "", message);
  }


  @Override public void addPropertyChangeListener(RemotePropertyChangeListener<String> listener) throws RemoteException
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(RemotePropertyChangeListener<String> listener) throws RemoteException
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void propertyChange(
      RemotePropertyChangeEvent remotePropertyChangeEvent)
      throws RemoteException
  {
    if(remotePropertyChangeEvent.getPropertyName().equals("MessageSentClient"))
    {
      String message = (String) remotePropertyChangeEvent.getNewValue();

      try
      {
        communicate(message);
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}
