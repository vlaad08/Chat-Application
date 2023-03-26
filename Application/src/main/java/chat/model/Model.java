package chat.model;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public interface Model
{
  void setUsername(String username);
  String getUsername();

  void sendMessage(String message, String username);

  ArrayList<String> getMessages();

  void addPropertyChangeListener(PropertyChangeListener listener);
  void removePropertyChangeListener(PropertyChangeListener listener);

  void receivedMessageFromServer(String message);

  int getConnectedUsers() throws IOException;

  void closeLogFile();
}
