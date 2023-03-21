package chat.view;

import chat.viewmodel.ChatViewModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;


public class ChatController
{
  @FXML private ListView chatListView;
  @FXML private TextField inputTextField;
  @FXML private Button sendButton;

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
    viewModel.sendMessage(inputTextField.getText(), viewModel.getUsername());
    inputTextField.setText("");
  }



}
