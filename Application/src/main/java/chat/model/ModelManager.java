package chat.model;

import chat.client.Client;
import chat.client.ClientImplementation;
import com.google.gson.Gson;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private User user;
  private MessageList messageList;
  private PropertyChangeSupport support;
  private Gson gson;
  private Client client;

  public ModelManager(Client client) throws IOException
  {
    this.client = client;
    messageList = new MessageList();
    support = new PropertyChangeSupport(this);
    gson = new Gson();
    client.addPropertyChangeListener(new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        if(evt.getPropertyName().equals("messageSentClient"))
        {
          String text = (String) evt.getNewValue();
          receivedMessageFromServer(text);
          support.firePropertyChange("reloadMessages", true, false);
        }
      }
    });
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
    String json = gson.toJson(Message.getInstance(message));
    client.sendMessage(json);
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
}
