package chat.client;

import chat.MyApplication;
import javafx.application.Application;

import java.io.IOException;

public class StartClient
{
  public static void main(String[] args) throws IOException
  {
    ClientImplementation client=new ClientImplementation("10.154.192.130", 8080,"230.0.0.0",8888);
    Thread thread = new Thread(client);

    thread.start();
  }

}
