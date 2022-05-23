package listeners;

import java.util.ArrayList;

public interface viewListener {

    String showAllQuestionsInUI();

    String addOpenQuestion(String questionText, String answerText);

    String addAmericanQuestion(String text, ArrayList<String> answerArray, ArrayList<Boolean> correctnessArray);

    String PrintAllQuestions();

    ArrayList<Integer> GetAllIDfromModel();


    String changeOpenQuestionAnswerUI(String newAnswerText, int id);

    String showChosenQuestion(int id);

    String ChangeWording(String text, int id);

    int addAmericanAnswersSizeToUI(int size);

    String changeAmericanAnswerUI(int id,String answerText,boolean correctness,int numOfAnswer);


    int getNumOfAnswersToUI(int id);

    String showAmericanQuestionUI();

    ArrayList<Integer> getAmericanQuestionIDArrayListUI();

    boolean checkIfAmerican(int id);
}
