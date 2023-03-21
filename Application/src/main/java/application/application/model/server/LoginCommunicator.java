package application.application.model.server;

import java.net.Socket;


public class LoginCommunicator implements Runnable
{
  private final UDPBroadcaster broadcaster;
  private final Socket socket;
  //private final Gson gson;

  public LoginCommunicator(Socket socket, UDPBroadcaster broadcaster){
    this.socket = socket;
    this.broadcaster = broadcaster;
    //this.gson = new Gson();
  }

  @Override public void run()
  {

  }
}
