package view;

import listeners.viewListener;

import java.util.ArrayList;

public interface AbstractQuestionView {

  void registerListener(viewListener newListener);

  void showStatusMassage(String result);

  void showQuestionsUI();

  void addQuestionToUI();

  void updateQuestionTextUI();

  void printAllQuestionsToString(String questionString);



}
