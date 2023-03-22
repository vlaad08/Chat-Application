package chat.server;

import java.io.*;
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

  private void communicate() throws IOException
  {
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    PrintWriter writer = new PrintWriter(outputStream);
    try{
      while (true)
      {
        String message=reader.readLine();
        System.out.println(message);
        if (message.equals("connect"))
        {
          writer.println("connected");
          System.out.println("connected");
          writer.flush();

          while (true)
          {
            message=reader.readLine();
            System.out.println(message);
            writer.println(message);
            writer.flush();
            if (message.equals("exit"))
            {
              break;
            }
          }
        }
      }
    }
    finally
    {
      synchronized (broadcaster)
      {
        socket.close();
      }
    }
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
