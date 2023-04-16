package chat.shared;



import dk.via.remote.observer.RemotePropertyChangeListener;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Communicator extends Remote{
  void communicate(Message message) throws IOException;
  void addPropertyChangeListener(RemotePropertyChangeListener<Message> listener) throws RemoteException;
  void removePropertyChangeListener(RemotePropertyChangeListener<Message> listener) throws RemoteException;
}
