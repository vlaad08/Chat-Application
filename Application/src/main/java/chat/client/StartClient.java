package chat.client;

import java.io.IOException;

public class StartClient
{
  public static void main(String[] args) throws IOException
  {
    ClientImplementation client=new ClientImplementation("10.154.212.120", 8080,"GECISHUGYOSFASZ", "230.0.0.0",8888);
    ClientImplementation client1=new ClientImplementation("10.154.212.120", 8080,"FASZOSZAR", "230.0.0.0",8888);
    client.communicate();
    client1.communicate();
  }

}
