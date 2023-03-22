package chat.client;

import java.io.IOException;

public interface Client extends Runnable
{
  void communicate() throws IOException;
}
