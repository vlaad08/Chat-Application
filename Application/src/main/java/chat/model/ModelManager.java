package chat.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private User user;
  private MessageList messageList;
  private PropertyChangeSupport support;

  public ModelManager()
  {
    messageList = new MessageList();
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
  {
    message = username + ":\n" + message;
    messageList.addMessage(message);
    support.firePropertyChange("messageSent", false, true);
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
}
