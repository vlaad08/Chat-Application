package chat.client;

import java.beans.PropertyChangeListener;
import java.io.Closeable;
import java.io.IOException;

public interface Client extends Closeable
{
  void communicate() throws IOException, InterruptedException;

  void addPropertyChangeListener(PropertyChangeListener listener);

  void removePropertyChangeListener(PropertyChangeListener listener);

  void sendMessage(String json);
}
