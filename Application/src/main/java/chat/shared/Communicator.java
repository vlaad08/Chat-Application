package chat.shared;



import chat.model.Message;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Communicator extends Remote
{
  void sendMessage(Message message) throws IOException;
  void addPropertyChangeListener(RemotePropertyChangeListener<String> listener) throws RemoteException;
  void removePropertyChangeListener(RemotePropertyChangeListener<String> listener) throws RemoteException;
}
