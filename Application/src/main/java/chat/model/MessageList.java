package chat.model;

import java.util.ArrayList;

public class MessageList
{
  private ArrayList<String> messages;

  public MessageList()
  {
    messages = new ArrayList<>();
  }

  public void addMessage(String msg)
  {
    messages.add(msg);
  }

  public ArrayList<String> getMessages()
  {
    return messages;
  }
}
