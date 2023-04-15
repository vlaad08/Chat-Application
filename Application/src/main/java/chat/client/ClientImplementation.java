package chat.client;

import chat.MyApplication;
import chat.model.Message;
import chat.shared.Communicator;
import com.google.gson.Gson;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Application;
import javafx.application.Platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientImplementation extends UnicastRemoteObject implements Client,
    RemotePropertyChangeListener<String>
{
  private final Socket socket;
  private final PrintWriter writer;
  private final PropertyChangeSupport support;
  private final BufferedReader reader;
  private final Gson gson;
  private Communicator communicator;


  public ClientImplementation(String host, int port, Communicator communicator) throws IOException
  {
    socket = new Socket(host, port);
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    reader = new BufferedReader(inputStreamReader);
    writer = new PrintWriter(outputStream);
    support=new PropertyChangeSupport(this);
    gson = new Gson();
    this.communicator=communicator;
    communicator.addPropertyChangeListener(this);
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

  @Override public int requestNumberOfConnectedUsers() throws IOException
  {
    writer.println("returnNumberOfConnectedClients");
    writer.flush();
    String numberString = reader.readLine();
    return Integer.parseInt(numberString);
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
    socket.close();
  }

  @Override public void propertyChange(RemotePropertyChangeEvent<String> remotePropertyChangeEvent) throws RemoteException
  {
    if (remotePropertyChangeEvent.getPropertyName().equals("message"))
    {
      String message=remotePropertyChangeEvent.getNewValue();
      Platform.runLater(() -> {
        Message msg = gson.fromJson(message, Message.class);
        support.firePropertyChange("messageSentClient", false,msg.getMessage());
      });
    }
  }
}
