package chat.view;

import chat.viewmodel.ViewModelFactory;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewFactory vfactory;

  public ViewHandler(ViewModelFactory viewModelFactory){
    vfactory = new ViewFactory(this,viewModelFactory);
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage){
    this.primaryStage = primaryStage;
    openView("usernameView");
  }

  public void openView(String id){
    Region vfactoryRegion = vfactory.loadView(id);
    currentScene.setRoot(vfactoryRegion);
    primaryStage.setScene(currentScene);
    primaryStage.setTitle("ChatApp");
    primaryStage.show();
  }

  public void closeView(){
    primaryStage.close();
  }

}
