package chat.server;

import chat.model.Message;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LoginCommunicator implements Runnable
{
  private final UDPBroadcaster broadcaster;
  private final Socket socket;
  private Gson gson;

  private  int connectedClients = 0;

  public LoginCommunicator(Socket socket, UDPBroadcaster broadcaster)
      throws FileNotFoundException
  {
    this.socket = socket;
    this.broadcaster = broadcaster;
  }

  private void communicate() throws IOException
  {

    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    PrintWriter writer = new PrintWriter(outputStream);
    try{
      loop : while (true)
      {
        String text = reader.readLine();
        if(text == null)
        {
          break loop;

        }
        else if(text.equals("connect"))
        {
          connectedClients++;
          writer.println("connected");
          writer.flush();
        }
        else if(text.equals("returnNumberOfConnectedClients"))
        {
          writer.println(connectedClients);
          writer.flush();
        }
        else
        {
          broadcaster.broadcast(text);
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
