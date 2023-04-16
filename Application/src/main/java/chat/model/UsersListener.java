package chat.model;

import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UsersListener extends UnicastRemoteObject implements
    RemotePropertyChangeListener<Integer>
{
  private Model model;

  public UsersListener(Model model) throws RemoteException
  {
    this.model = model;
  }



  @Override public void propertyChange(
      RemotePropertyChangeEvent<Integer> remotePropertyChangeEvent)
      throws RemoteException
  {
    if(remotePropertyChangeEvent.getPropertyName().equals("users"))
    {
      int x = remotePropertyChangeEvent.getNewValue();
      model.setUsers(x);
    }
  }
}
