package chat.shared;



import dk.via.remote.observer.RemotePropertyChangeListener;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Communicator extends Remote
{
  void communicate() throws IOException;
  void addPropertyChangeListener(RemotePropertyChangeListener<String> listener) throws RemoteException;
  void removePropertyChangeListener(RemotePropertyChangeListener<String> listener) throws RemoteException;
}
