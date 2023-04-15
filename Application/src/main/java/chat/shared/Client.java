package chat.shared;

import chat.model.Message;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.beans.PropertyChangeListener;
import java.io.Closeable;
import java.io.IOException;

public interface Client extends Closeable
{
//  void communicate() throws IOException, InterruptedException;

  void addPropertyChangeListener(RemotePropertyChangeListener<Message> listener);

  void removePropertyChangeListener(RemotePropertyChangeListener<Message> listener);

  int requestNumberOfConnectedUsers() throws IOException;

  void sendMessage(String json) throws IOException;
}
