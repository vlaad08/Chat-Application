package chat.client;

import chat.MyApplication;
import javafx.application.Application;

import java.io.IOException;

public class StartClient
{
  public static void main(String[] args) throws IOException
  {
    ClientImplementation client=new ClientImplementation("localhost", 4567,"230.0.0.0",8888);

    client.communicate();


    Application.launch(MyApplication.class);
  }

}
