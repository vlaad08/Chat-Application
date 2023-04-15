package chat;
import chat.client.ClientImplementation;
import chat.model.Model;
import chat.model.ModelManager;
import chat.server.LoginCommunicator;
import chat.shared.Communicator;
import chat.view.ViewHandler;
import chat.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyApplication extends Application
{

  @Override public void start(Stage primaryStage) throws Exception
  {
    Registry registry = LocateRegistry.getRegistry(1099);
    Communicator communicator = (Communicator) registry.lookup("login");
    ClientImplementation client=new ClientImplementation("localhost", 8080,communicator);

    Model model = new ModelManager(client);
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
    client.communicate();
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
    {
      @Override public void handle(WindowEvent event)
      {
        try
        {
          client.close();
        }
        catch (IOException e)
        {
          throw new RuntimeException(e);
        }
        model.closeLogFile();
        viewHandler.closeView();
      }
    });
  }

  public static void main(String[] args)
  {
    launch();
  }

}