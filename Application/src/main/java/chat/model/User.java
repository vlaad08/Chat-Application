package chat.model;

import chat.shared.Client;
import chat.shared.Communicator;
import com.google.gson.Gson;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class User extends UnicastRemoteObject implements Client, RemotePropertyChangeListener<String>
{
  private String username;
  private final Gson gson;
  private Communicator communicator;
  private RemotePropertyChangeSupport<Message> support;

  public User (String username, Communicator communicator) throws RemoteException
  {
    this.username = username;
    gson=new Gson();
    support=new RemotePropertyChangeSupport<>();
    this.communicator=communicator;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getUsername()
  {
    return username;
  }

//  @Override public void communicate() throws IOException, InterruptedException
//  {
//
//  }

  @Override public void addPropertyChangeListener(RemotePropertyChangeListener<Message> listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(RemotePropertyChangeListener<Message> listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public int requestNumberOfConnectedUsers() throws IOException
  {
    return 0;
  }

  @Override public void sendMessage(String json) throws IOException
  {
    Message message=gson.fromJson(json,Message.class);
    communicator.sendMessage(message);
  }

  @Override public void propertyChange(
      RemotePropertyChangeEvent<String> remotePropertyChangeEvent)
      throws RemoteException
  {

  }

  @Override public void close() throws IOException
  {

  }
}
