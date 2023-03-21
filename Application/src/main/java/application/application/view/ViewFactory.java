package application.application.view;

import application.application.viewmodel.ChatViewModel;
import application.application.viewmodel.UsernameViewModel;
import application.application.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory
{
  private ViewHandler vhandler;
  private ViewModelFactory vmfactory;
  private ChatController cvmodel;
  private UsernameController unmodel;

  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
    this.vmfactory = viewModelFactory;
    this.vhandler = viewHandler;
    cvmodel = null;
    unmodel = null;
  }

  public Region loadView(String id){
    return switch (id){
      case"usernameView" -> loadUsernameView();
      case"chatView" -> loadChatView();
      default -> throw new IllegalArgumentException("Unknown view: " +id);
    };
  }

  public Region loadUsernameView(){

    if (unmodel == null) {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("application/application/username.fxml"));
      try {
        Region root = loader.load();
        unmodel = loader.getController();
        unmodel.init(vhandler, vmfactory.getUsernameViewModel(), root);
      } catch (IOException e) {
        throw new IOError(e);
      }
    }

    unmodel.reset();
    return unmodel.getRoot();
  }

  public Region loadChatView(){

    if (cvmodel == null) {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("application/application/chat.fxml"));
      try {
        Region root = loader.load();
        cvmodel = loader.getController();
        cvmodel.init(vhandler, vmfactory.getChatViewModel(), root);
      } catch (IOException e) {
        throw new IOError(e);
      }
    }

    cvmodel.reset();
    return cvmodel.getRoot();
  }

}

}
