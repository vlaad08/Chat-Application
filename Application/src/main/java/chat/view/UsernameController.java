package chat.view;

import chat.viewmodel.UsernameViewModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class UsernameController
{

  private UsernameViewModel unmodel;
  private ViewHandler vhandler;

  @FXML private TextField usernameField;
  @FXML private Button createUserField;
  @FXML private Button viewChatAsGuestField;

  private Region root;

  public void init(ViewHandler viewHandler, UsernameViewModel usernameViewModel, Region root){

    this.unmodel = usernameViewModel;
    this.vhandler = viewHandler;
    this.root = root;

    //binds??

    usernameField.setOnKeyPressed(event -> {
      if(event.getCode() == KeyCode.ENTER)
      {
        try
        {
          onCreateUser();
        }
        catch (RemoteException e)
        {
          throw new RuntimeException(e);
        }
      }
    });

    unmodel.bindUsername(usernameField.textProperty());
  }

  public void reset(){}

  public Region getRoot(){
    return this.root;
}

  @FXML public void onCreateUser() throws RemoteException
  {
    //create user
    if(usernameField.getText() == null || usernameField.getText().equals(""))
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("Please insert a username or continue as a guest");
      alert.show();
    }
    else
    {
      unmodel.setUsername(usernameField.getText());
      vhandler.openView("chatView");
    }
  }

  @FXML public void onShowChat() throws RemoteException
  {
    unmodel.setUsername("Guest");
    vhandler.openView("chatView");
  }



}
