package chat.model;

import chat.shared.Message;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Listener extends UnicastRemoteObject implements
    RemotePropertyChangeListener<Message>
{
  private final Model model;

  public Listener(Model model) throws RemoteException
  {
    this.model = model;
  }


  @Override public void propertyChange(
      RemotePropertyChangeEvent<Message> remotePropertyChangeEvent)
      throws RemoteException
  {
    if(remotePropertyChangeEvent.getPropertyName().equals("MessageSentServer"))
    {
      Platform.runLater(()->
      {
        Message message = remotePropertyChangeEvent.getNewValue();
        model.receivedMessageFromServer(message.getMessage());
      });
    }
  }
}
