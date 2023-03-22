package chat.client;

import chat.MyApplication;
import javafx.application.Application;

import java.io.IOException;

public class StartClient
{
  public static void main(String[] args) throws IOException
  {
    ClientImplementation client=new ClientImplementation("localhost", 8080,"230.0.0.0",8888);

    Application.launch(MyApplication.class);
    client.communicate();
  }

}
