package chat.model;

public class Message
{
  public static Message instance;
  private String text;

  private Message(String text)
  {
    this.text = text;
  }

  public static synchronized Message getInstance(String text)
  {
    if(instance == null)
    {
      instance = new Message(text);
    }
    return instance;
  }

  public String getMessage(){
    return text;
  }
}
