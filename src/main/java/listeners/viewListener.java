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


    int getNumOfAnswersToUI(int id);
}
