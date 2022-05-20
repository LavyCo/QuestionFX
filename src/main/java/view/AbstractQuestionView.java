package view;

import controller.Controller;
import listeners.viewListener;

public interface AbstractQuestionView {
  void registerListener(viewListener newListener);

  void addOpenQuestionToUI(String updateUserMessage);
}
