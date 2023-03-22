package chat.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.*;
import java.nio.channels.AsynchronousCloseException;

public class MessageListener implements Runnable, PropertyChangeListener
{
  private final ClientImplementation client;
  private final MulticastSocket multicastSocket;
  private final InetSocketAddress socketAddress;
  private final NetworkInterface netInterface;

  public MessageListener(ClientImplementation client, String groupAddress, int port) throws
      IOException
  {
    this.client = client;
    multicastSocket = new MulticastSocket(port);
    InetAddress group = InetAddress.getByName(groupAddress);
    socketAddress = new InetSocketAddress(group, port);
    netInterface = NetworkInterface.getByInetAddress(group);
  }

  public void run() {
    try {
      listen();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void listen() throws IOException {
    multicastSocket.joinGroup(socketAddress, netInterface);
    try {
      byte[] content = new byte[32768];
      while (true) {
        DatagramPacket packet = new DatagramPacket(content, content.length);
        multicastSocket.receive(packet);
        String message = new String(packet.getData(), 0, packet.getLength());
        client.receiveBroadcast(message);
      }
    } catch (SocketException e) {
      if (!(e.getCause() instanceof AsynchronousCloseException)) throw e;
    }
  }

  public void close() throws IOException {
    multicastSocket.leaveGroup(socketAddress, netInterface);
    multicastSocket.close();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals("messageSent"))
    {
      String message = (String) evt.getNewValue();
      try
      {
        client.receiveBroadcast(message);
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}
