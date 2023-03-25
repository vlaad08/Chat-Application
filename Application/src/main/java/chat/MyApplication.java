package chat;

import chat.client.Client;
import chat.client.ClientImplementation;
import chat.model.Model;
import chat.model.ModelManager;
import chat.view.ViewHandler;
import chat.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MyApplication extends Application
{

  @Override public void start(Stage primaryStage) throws Exception
  {
    ClientImplementation client = new ClientImplementation("localhost", 8080,"230.0.0.0",8888);
    Model model = new ModelManager(client);
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
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