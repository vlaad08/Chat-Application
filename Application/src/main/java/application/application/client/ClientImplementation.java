package application.application.client;

import java.io.*;
import java.net.Socket;

public class ClientImplementation implements Client
{
  private final Socket socket;
  private final PrintWriter writer;
  private final BufferedReader reader;
  private String name;

  public ClientImplementation(String host, int port,String name) throws IOException
  {
    this.name=name;
    socket = new Socket(host, port);
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    reader = new BufferedReader(inputStreamReader);
    writer = new PrintWriter(outputStream);
  }

  public String getName()
  {
    return name;
  }

  @Override public void createUser() throws IOException
  {
    writer.println("connect");
    System.out.println("connect");
    writer.flush();
    if (reader.readLine().equals("connected"))
    {

    }
  }
}
