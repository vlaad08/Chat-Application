package chat.server;

import chat.model.Listener;
import chat.shared.Communicator;
import chat.shared.Message;
import com.google.gson.Gson;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.io.*;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class LoginCommunicator implements Communicator
{

  private final RemotePropertyChangeSupport<Message> support;
  private final RemotePropertyChangeSupport<Integer> usersSupport;

  private int users;

  public LoginCommunicator()
  {
    this.support = new RemotePropertyChangeSupport<>();
    usersSupport = new RemotePropertyChangeSupport<>();
    users = 0;
  }

  @Override public void communicate(Message message) throws IOException
  {
    support.firePropertyChange("MessageSentServer", null, message);
  }

  @Override public void addPropertyChangeListener(
      RemotePropertyChangeListener<Message> listener) throws RemoteException
  {
      support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      RemotePropertyChangeListener<Message> listener) throws RemoteException
  {
      support.removePropertyChangeListener(listener);
  }

  @Override public void addUsersListener(
      RemotePropertyChangeListener<Integer> listener) throws RemoteException
  {
    usersSupport.addPropertyChangeListener(listener);
  }

  @Override public void removeUsersListener(
      RemotePropertyChangeListener<Integer> listener) throws RemoteException
  {
    usersSupport.addPropertyChangeListener(listener);
  }

  @Override public void userLoggedIn() throws RemoteException
  {
    users++;
    updateUsers();
  }

  @Override public void userLoggedOut() throws RemoteException
  {
    users--;
    updateUsers();
  }

  @Override public void updateUsers() throws RemoteException
  {
    usersSupport.firePropertyChange("users", null, users);

  }

}
