package view;

import listeners.viewListener;

import java.util.ArrayList;
import java.util.Vector;

public interface AbstractQuestionView {

  void showAmericanAnswerInView(Vector<String> americanAnswerText, int numOfAmericanAnswers, int id);

  void registerListener(viewListener newListener);

  void showStatusMassage(String result);

  void showQuestionsUI();

  void addQuestionToUI();

  void printAllQuestionsToString(String questionString);


  //add
  void showStringAndIDinQuestionView(ArrayList<Integer> idArray, String questionsString);

  void getNumOfQuestionFromModel(int numberOfQuestions);

  void showAutoExamToUI(String autoExam);

  void showIdInUpdateQuestionView(ArrayList<Integer> idArray);

    void showChosenQuestionToUpdateInView(String questionText,int id);

  void updateAnswerMenuView(String toString, ArrayList<Integer> idArray);

  void updateOpenAnswerMainView(String questionText, String answerText, int id);

  void massageFromModel(String msg);

  void SaveMsgFromModel(String msg);
}
