package chat.model;

import chat.shared.Communicator;
import com.google.gson.Gson;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ModelManager implements Model, RemotePropertyChangeListener<String>
{
  private User user;
  private final MessageList messageList;
  private final RemotePropertyChangeSupport<String> remoteSupport;
  private final PropertyChangeSupport support;
  private Communicator client;
  private FileOutputStream fileOut = new FileOutputStream("chatLog.txt");

  private PrintWriter fileWriter = new PrintWriter(fileOut);


  public ModelManager(Communicator client) throws IOException
  {
    this.client = client;
    messageList = new MessageList();
    remoteSupport = new RemotePropertyChangeSupport<>();
    support = new PropertyChangeSupport(this);
  }

  @Override public void setUsername(String username)
  {
    user = new User(username);
  }

  @Override public String getUsername()
  {
    return user.getUsername();
  }

  @Override public void sendMessage(String message, String username)
      throws IOException
  {
    message = username + ":\n" + message;
    remoteSupport.firePropertyChange("MessageSentClient", "", message);
  }



  @Override public ArrayList<String> getMessages()
  {
    return messageList.getMessages();
  }

  public void addRemotePropertyChangeListener(RemotePropertyChangeListener<String> listener)
  {
    remoteSupport.addPropertyChangeListener(listener);
  }
  public void removeRemotePropertyChangeListener(RemotePropertyChangeListener<String> listener)
  {
    remoteSupport.removePropertyChangeListener(listener);
  }

  @Override public void receivedMessageFromServer(String message)
  {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    Date date = new Date();
    messageList.addMessage(message);

    support.firePropertyChange("reloadMessages", false, true);

    String log = format.format(date) +  " " + message + "\n";
    fileWriter.write(log);
  }

  @Override public int getConnectedUsers() throws IOException
  {
    return 0;
  }

  @Override public void closeLogFile()
  {
    fileWriter.close();
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChaneListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void propertyChange(
      RemotePropertyChangeEvent<String> remotePropertyChangeEvent)
      throws RemoteException
  {
      if(remotePropertyChangeEvent.getPropertyName().equals("MessageSentServer"))
      {
        String message = remotePropertyChangeEvent.getNewValue();
        receivedMessageFromServer(message);
      }
  }
}
