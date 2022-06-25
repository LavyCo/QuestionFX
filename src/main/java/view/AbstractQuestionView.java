package view;

import listeners.viewListener;

import java.util.ArrayList;
import java.util.Vector;

public interface AbstractQuestionView {



  void registerListener(viewListener newListener);

  void showStatusMassage(String result);

  void showQuestionsUI();


  void printAllQuestionsToString(String questionString);


  //add
  void showStringAndIDinQuestionView(ArrayList<Integer> idArray, String questionsString);

  void getNumOfQuestionFromModel(int numberOfQuestions);

  void showAutoExamToUI(String autoExam);


  void showChosenQuestionToUpdateInView(String questionText,int id);

  void updateAnswerMenuView(String toString, ArrayList<Integer> idArray);

  void updateOpenAnswerMainView(String questionText, String answerText, int id);

  void massageFromModel(String msg);

  void SaveMsgFromModel(String msg);

  void showAmericanQuestionId(String americanQuestionsString, Vector<Integer> americanIdVector);

  void showChosenAmericanQuestionToDelete(String americanQuestionString, Vector<String> americanAnswerString, int id);

    void updateDeleteAnswerView(String toString, Vector<String> americanAnswerToDelete, int id, String result);

  void showIdInUpdateQuestionView(ArrayList<Integer> idArray);

    void showManualExamSelection(Vector<String> allQuestionString);


  void showAmericanAnswerInView(Vector<String> americanAnswerVector, int numOfAmericanAnswers, int id);


  void showAmericanAnswersSelectionManualExams(String questionText, int questionNumber, int size, Vector<String> answersString);

    void showManualExamUI(String examString);
}
