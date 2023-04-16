package chat.server;

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

  public LoginCommunicator()
  {
    this.support = new RemotePropertyChangeSupport<>();
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

}
