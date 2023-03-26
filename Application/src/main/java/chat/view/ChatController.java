package chat.view;

import chat.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatController
{
  @FXML private ListView<String> chatListView;
  @FXML private TextField inputTextField;
  @FXML private Button sendButton;

  @FXML private MenuItem changeUsernameMenuItem;


  private Region root;
  private ViewHandler viewHandler;
  private ChatViewModel viewModel;

  public void init(ViewHandler viewHandler, ChatViewModel chatViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = chatViewModel;
    this.root = root;

    viewModel.bindChat(chatListView.itemsProperty());


    inputTextField.setOnKeyPressed(event -> {
      if(event.getCode() == KeyCode.ENTER)
      {
        onSend();
      }
    });
  }

  public Region getRoot()
  {
    return root;
  }

  public void  reset()
  {
    inputTextField.setText("");
  }


  @FXML public void onSend()
  {
    String text = inputTextField.getText();
    if(!text.equals(""))
    {
      viewModel.sendMessage(text, viewModel.getUsername());
      reset();
    }
  }

  @FXML public void changeUsername()
  {
    viewHandler.openView("usernameView");
  }

  @FXML public void requestUsersConnected() throws IOException
  {
    Alert information = new Alert(Alert.AlertType.INFORMATION);
    information.setContentText("There are " + viewModel.getUsersConnected() + " user(s) connected at the moment");
    Stage stage = (Stage) information.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image("informationIcon.png"));
    information.show();
  }


}
