package application.application.view;

import application.application.viewmodel.UsernameViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

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
  }

  public void reset(){}

  public Region getRoot(){
    return this.root;
}

  @FXML public void onCreateUser(){
    //create user
    vhandler.openView("usernameView");
  }

  @FXML public void onShowChat(){
    vhandler.openView("usernameView");
  }



}
