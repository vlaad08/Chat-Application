package chat.viewmodel;

import chat.model.Model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public class ChatViewModel implements PropertyChangeListener
{
  private final Model model;

  private final ObjectProperty<ObservableList<String>> chat;

  public ChatViewModel(Model model)
  {
    this.model = model;
    this.chat = new SimpleObjectProperty<>();


    model.addPropertyChangeListener(this);
  }

  public void bindChat(ObjectProperty<ObservableList<String>> property)
  {
    property.bindBidirectional(chat);
  }

  public String getUsername()
  {
    return model.getUsername();
  }

  public void sendMessage(String message, String username) throws IOException
  {
    model.sendMessage(message, username);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals("reloadMessages"))
    {
      ArrayList<String> list = model.getMessages();
      ObservableList<String> observableList= FXCollections.observableList(list);

      chat.set(observableList);
    }
  }

  public int getUsersConnected() throws IOException
  {
    return model.getConnectedUsers();
  }
}
