package chat;

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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyApplication extends Application
{

  @Override public void start(Stage primaryStage) throws Exception
  {
    Registry registry = LocateRegistry.getRegistry(1099);
    Communicator communicator = (Communicator) registry.lookup("communicator");


    Model model = new ModelManager(communicator);
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
    {
      @Override public void handle(WindowEvent event)
      {
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