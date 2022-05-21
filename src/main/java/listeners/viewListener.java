package listeners;

import java.util.ArrayList;

public interface viewListener {

    String showAllQuestionsInUI();

    String addOpenQuestion(String questionText, String answerText);

    String addAmericanQuestion(String text, ArrayList<String> answerArray, ArrayList<Boolean> correctnessArray);

    String PrintAllQuestions();

    ArrayList<Integer> GetAllIDfromModel();

    String ChangeWording(String text, int id);
}
