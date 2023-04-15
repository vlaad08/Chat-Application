package chat.server;

import chat.model.Message;
import chat.shared.Communicator;
import com.google.gson.Gson;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.io.*;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LoginCommunicator extends UnicastRemoteObject implements Communicator
{
  private Gson gson;
  private RemotePropertyChangeSupport<String> support;
  private  int connectedClients = 0;

  public LoginCommunicator() throws FileNotFoundException, RemoteException, AlreadyBoundException
  {
    this.support=new RemotePropertyChangeSupport<>();
    gson=new Gson();
  }

  @Override public void sendMessage(Message message) throws IOException
  {
    String m= gson.toJson(message);
    support.firePropertyChange("messageSentClient",null,m);
  }

//  public void communicate() throws IOException
//  {
//    InputStream inputStream = socket.getInputStream();
//    OutputStream outputStream = socket.getOutputStream();
//    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//    PrintWriter writer = new PrintWriter(outputStream);
//    try{
//      loop : while (true)
//      {
//        String text = reader.readLine();
//        if(text == null)
//        {
//          break loop;
//
//        }
//        else if(text.equals("connect"))
//        {
//          connectedClients++;
//          writer.println("connected");
//          writer.flush();
//        }
//        else if(text.equals("returnNumberOfConnectedClients"))
//        {
//          writer.println(connectedClients);
//          writer.flush();
//        }
//        else
//        {
//          support.firePropertyChange("message",null, text);
//        }
//      }
//    }
//
//    finally
//    {
//      socket.close();
//    }
//  }


  @Override public void addPropertyChangeListener(RemotePropertyChangeListener<String> listener) throws RemoteException
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(RemotePropertyChangeListener<String> listener) throws RemoteException
  {
    support.removePropertyChangeListener(listener);
  }
}
