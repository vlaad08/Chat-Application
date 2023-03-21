module application.application {
  requires javafx.controls;
  requires javafx.fxml;
  requires com.google.gson;

  opens application.application to javafx.fxml;
  exports application.application;
}