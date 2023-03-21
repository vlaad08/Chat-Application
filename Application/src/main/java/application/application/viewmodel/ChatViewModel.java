package application.application.viewmodel;

import application.application.model.Model;
import application.application.model.ModelManager;
import javafx.beans.property.ObjectProperty;

public class ChatViewModel
{
  private Model model;


  public ChatViewModel(Model model)
  {
    this.model = model;
  }
}
