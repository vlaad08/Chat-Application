package chat.model;

import chat.client.Client;
import chat.client.ClientImplementation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ModelManager implements Model,PropertyChangeListener
{
  private User user;
  private MessageList messageList;
  private PropertyChangeSupport support;

  private Client clientImplementation;

  public ModelManager() throws IOException
  {
    messageList = new MessageList();
    support = new PropertyChangeSupport(this);
    clientImplementation=new ClientImplementation("localhost", 4567,"230.0.0.0",8888);

    clientImplementation.addPropertyChangeListener(this);
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
  {
    message = username + ":\n" + message;
    messageList.addMessage(message);
    support.firePropertyChange("messageSent", false, message);
  }



  @Override public ArrayList<String> getMessages()
  {
    return messageList.getMessages();
  }

  public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void receivedMessageFromServer(String message)
  {
    messageList.addMessage(message);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("newMessage"))
    {
      support.firePropertyChange("newViewModelMessage",null,evt.getNewValue());
    }
  }
}
