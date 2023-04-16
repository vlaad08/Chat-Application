package chat.model;

import chat.viewmodel.ChatViewModel;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model
{
  void setUsername(String username);
  String getUsername();

  void sendMessage(String message, String username) throws IOException;

  ArrayList<String> getMessages();


  void receivedMessageFromServer(String message);

  int getConnectedUsers() throws IOException;

  void closeLogFile();
  void addPropertyChangeListener(PropertyChangeListener listener);

  void removePropertyChaneListener(PropertyChangeListener listener);

  void userLoggedIn() throws RemoteException;
  void userLoggedOut() throws RemoteException;

  void setUsers(int x);
}
