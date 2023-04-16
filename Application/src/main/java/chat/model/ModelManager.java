package chat.model;

import chat.shared.Communicator;
import chat.shared.Message;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ModelManager implements Model
{
  private User user;
  private final MessageList messageList;
  private final PropertyChangeSupport support;
  private final Communicator client;
  private final FileOutputStream fileOut = new FileOutputStream("chatLog.txt");

  private final PrintWriter fileWriter = new PrintWriter(fileOut);


  public ModelManager(Communicator client) throws IOException
  {
    this.client = client;
    messageList = new MessageList();
    support = new PropertyChangeSupport(this);
  }

  @Override public void setUsername(String username)
  {
    user = new User(username);
  }

  @Override public String getUsername()
  {
    return user.getUsername();
  }

  @Override public void sendMessage(String message, String username)
      throws IOException
  {
    message = username + ":\n" + message;
    Message msg = Message.getInstance(message);
    client.communicate(msg);
  }



  @Override public ArrayList<String> getMessages()
  {
    return messageList.getMessages();
  }


  @Override public void receivedMessageFromServer(String message)
  {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    Date date = new Date();
    messageList.addMessage(message);

    support.firePropertyChange("reloadMessages", false, true);

    String log = format.format(date) +  " " + message + "\n";
    fileWriter.write(log);
  }

  @Override public int getConnectedUsers()
  {
    return 0;
  }

  @Override public void closeLogFile()
  {
    fileWriter.close();
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChaneListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }


}
