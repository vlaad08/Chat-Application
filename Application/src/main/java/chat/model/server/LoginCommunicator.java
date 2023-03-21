package chat.model.server;

import java.io.IOException;
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

  private void communicate() throws IOException{

  }

  @Override public void run()
  {
    try{
      communicate();
    }catch (IOException e){
      e.printStackTrace();
    }
  }
}
