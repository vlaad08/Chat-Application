module application.application {
  requires javafx.controls;
  requires javafx.fxml;

  opens application.application to javafx.fxml;
  exports application.application;
}