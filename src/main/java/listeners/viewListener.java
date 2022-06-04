package listeners;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface viewListener {

    static void showSelectQuestionAndID(String toString, ArrayList<Integer> idArray) {
    }

    void showStringInUI();

    void addAmericanQuestionUI(String text, ArrayList<String> answerArray, ArrayList<Boolean> correctnessArray);

    void addOpenQuestionUI(String text, String text1);


    //add
    void updateQuestionTextUI();

    void showIDInUI();

    void showQuestionsAndIdToUpdate();

    void getNumOfQuestions();

    void createAutoExam(int value) throws FileNotFoundException;

    void showChosenQuestionToUpdate(int id);

    void changeQuestionText(String text, int id);

    void updateDeleteAmericanAnswer();

    void updateAnswerView();

    void showAnswerToUpdate(int intValue);


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
