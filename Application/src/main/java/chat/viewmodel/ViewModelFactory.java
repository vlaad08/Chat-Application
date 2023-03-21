package chat.viewmodel;

import chat.model.Model;

public class ViewModelFactory
{

  private UsernameViewModel usernameViewModel;
  private ChatViewModel chatViewModel;


  public ViewModelFactory(Model model)
  {
    this.usernameViewModel = new UsernameViewModel(model);
    this.chatViewModel = new ChatViewModel(model);

  }

  public ChatViewModel getChatViewModel()
  {
    return chatViewModel;
  }

  public UsernameViewModel getUsernameViewModel()
  {
    return usernameViewModel;
  }
}
