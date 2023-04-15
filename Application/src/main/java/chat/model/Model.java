package chat.model;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model
{
  void setUsername(String username) throws RemoteException;
  String getUsername();

  void sendMessage(String message, String username) throws IOException;

  ArrayList<String> getMessages();

  void addPropertyChangeListener(PropertyChangeListener listener);
  void removePropertyChangeListener(PropertyChangeListener listener);

  void receivedMessageFromServer(String message);

  int getConnectedUsers() throws IOException;

  void closeLogFile();
}
