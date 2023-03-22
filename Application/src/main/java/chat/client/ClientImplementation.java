package chat.client;

import chat.MyApplication;
import javafx.application.Application;

import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientImplementation implements Client
{
  private final Socket socket;
  private final PrintWriter writer;
  private final PropertyChangeSupport support;
  private final BufferedReader reader;
  private final MessageListener listener;

  private final Scanner scanner=new Scanner(System.in);

  public ClientImplementation(String host, int port, String groupAddress, int groupPort) throws IOException
  {

    socket = new Socket(host, port);
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    reader = new BufferedReader(inputStreamReader);
    writer = new PrintWriter(outputStream);
    support=new PropertyChangeSupport(this);

    listener=new MessageListener(this,groupAddress,groupPort);
    Thread thread=new Thread(listener);
    thread.start();
  }


  @Override public void communicate() throws IOException
  {
    writer.println("connect");
    System.out.println("connect");
    writer.flush();
    if (reader.readLine().equals("connected"))
    {
      while (true)
      {
//        System.out.println(reader.readLine());
        String message=scanner.nextLine();
        writer.println(message);
        writer.flush();

      }
    }
  }

  public void receiveBroadcast(String message) throws IOException
  {
    System.out.println(message);
  }
}
