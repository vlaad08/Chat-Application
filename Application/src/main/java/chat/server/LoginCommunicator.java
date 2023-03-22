package chat.server;

import chat.model.Model;
import chat.model.ModelManager;

import java.io.*;
import java.net.Socket;


public class LoginCommunicator implements Runnable
{
  private final UDPBroadcaster broadcaster;
  private final Socket socket;


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
        if (message.equals("connect"))
        {
          writer.println("connected");
          System.out.println("connected");
          writer.flush();



          while (true)
          {
            message=reader.readLine();
            writer.println(message);
            writer.flush();
            if (message.equals("exit"))
            {
              socket.close();

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
