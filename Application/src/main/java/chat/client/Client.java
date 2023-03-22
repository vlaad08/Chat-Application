package chat.client;

import java.beans.PropertyChangeListener;
import java.io.IOException;

public interface Client extends PropertyChangeListener
{
  void communicate() throws IOException;
}
