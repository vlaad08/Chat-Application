package chat.client;

import chat.MyApplication;
import chat.model.Message;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.application.Platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
  private final Gson gson;


  public ClientImplementation(String host, int port, String groupAddress, int groupPort) throws IOException
  {

    socket = new Socket(host, port);
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    reader = new BufferedReader(inputStreamReader);
    writer = new PrintWriter(outputStream);
    support=new PropertyChangeSupport(this);

    gson = new Gson();
    listener=new MessageListener(this,groupAddress,groupPort);
    Thread thread=new Thread(listener);
    thread.start();
  }


  @Override public synchronized void communicate()
      throws IOException, InterruptedException
  {
    writer.println("connect");
    System.out.println("connect");
    writer.flush();
    if (reader.readLine().equals("connected"))
    {
      System.out.println("connected");
    }

  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void sendMessage(String json)
  {
    writer.println(json);
    writer.flush();
  }

  public void receiveBroadcast(String message) throws IOException
  {
    Platform.runLater(() -> {
      Message msg = gson.fromJson(message, Message.class);
      support.firePropertyChange("messageSentClient", false,msg.getMessage());
    });
  }

  @Override public void close() throws IOException
  {
    listener.close();
    socket.close();
  }
}
