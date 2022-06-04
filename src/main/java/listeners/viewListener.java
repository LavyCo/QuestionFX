package listeners;

import java.util.ArrayList;

public interface viewListener {

    void showStringInUI();

    void addAmericanQuestionUI(String text, ArrayList<String> answerArray, ArrayList<Boolean> correctnessArray);

    void addOpenQuestionUI(String text, String text1);

    void updateQuestionUI();


    //    void showAllQuestionsInUI();
//
//    void addOpenQuestion(String questionText, String answerText);
//
//    void addAmericanQuestion(String text, ArrayList<String> answerArray, ArrayList<Boolean> correctnessArray);
//
//    String PrintAllQuestions();
//
//
//
//    String changeOpenQuestionAnswerUI(String newAnswerText, int id);
//
//    String showChosenQuestion(int id);
//
//
//    int addAmericanAnswersSizeToUI(int size);
//
//    String changeAmericanAnswerUI(int id,String answerText,boolean correctness,int numOfAnswer);
//
//
//    int getNumOfAnswersToUI(int id);
//
//    String showAmericanQuestionUI();
//
//    ArrayList<Integer> getAmericanQuestionIDArrayListUI();
//
//    boolean checkIfAmerican(int id);
}
