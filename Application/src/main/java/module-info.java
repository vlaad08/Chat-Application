module chat {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  //  requires com.google.gson;

  opens chat to javafx.fxml;
  opens chat.view to javafx.fxml;

  exports chat;
}