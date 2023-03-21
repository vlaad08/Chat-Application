package application.application.view;

import application.application.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ChatController
{
  @FXML private TextArea chatTextArea;
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
  }

  public Region getRoot()
  {
    return root;
  }

  public void  reset()
  {
    inputTextField.setText("");
  }


}
