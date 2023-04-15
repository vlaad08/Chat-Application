package chat.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class Message implements Serializable
{
  private static final HashMap<Integer, Message> instances = new HashMap<>();
  private String text;

  private static int key;

  private Message(String text)
  {
    this.text = text;
  }

  public static int generateKey()
  {
    Random random = new Random();
    return random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public static synchronized Message getInstance(String text)
  {
    key = generateKey();
    if(!instances.containsKey(key))
    {
      instances.put(key, new Message(text));
    }
    return instances.get(key);
  }

  public String getMessage(){
    return text;
  }

  public int getKey()
  {
    return key;
  }


}
