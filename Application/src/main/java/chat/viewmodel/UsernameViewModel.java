package chat.viewmodel;

import chat.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UsernameViewModel
{
  private Model model;

  private StringProperty inputUsername;

  public UsernameViewModel(Model model)
  {
    this.model = model;
    inputUsername = new SimpleStringProperty("");
  }


  public void bindUsername(StringProperty property)
  {
    inputUsername.bindBidirectional(property);
  }

  public void setUsername(String username)
  {
    model.setUsername(username);
  }
}
