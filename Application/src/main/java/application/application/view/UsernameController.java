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

  @FXML private TextField username;
  @FXML private Button createUser;
  @FXML private Button viewChatAsGuest;

  private Region root;

  public void init(ViewHandler viewHandler, UsernameViewModel usernameViewModel, Region root){

    this.unmodel = usernameViewModel;
  }




}
