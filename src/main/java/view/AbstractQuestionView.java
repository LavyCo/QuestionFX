package view;

import controller.Controller;
import listeners.viewListener;

public interface AbstractQuestionView {
  void registerListener(viewListener newListener);

  String addOpenQuestionToUI(String updateUserMessage);

}
