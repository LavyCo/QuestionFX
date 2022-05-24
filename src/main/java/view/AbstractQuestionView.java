package view;

import listeners.viewListener;

public interface AbstractQuestionView {
  void registerListener(viewListener newListener);

  String addOpenQuestionToUI(String updateUserMessage);


  void showAmricanQuestionMsg(String american_question_added);

  void printAllQuestionstoString(String allQuestions);
}
