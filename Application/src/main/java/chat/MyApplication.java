package chat;

import chat.model.Model;
import chat.model.ModelManager;
import chat.view.ViewHandler;
import chat.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyApplication extends Application
{

  @Override public void start(Stage primaryStage) throws Exception
  {
    Model model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
  }

}