package listeners;

import java.util.ArrayList;

public interface modelListener {

    void fireQuestionString(String questionString);

    void fireAmericanQuestionAddedResult(String string);

    void fireOpenQuestionAddedResult(String string);

    void fireIdToUI(ArrayList<Integer> idArray);

    void fireQuestionsStringAndId(String toString, ArrayList<Integer> idArray);

    void fireNumberOfQuestion(int numberOfQuestions);

    void fireAutoExam(String autoExam);

    void fireQuestionText(String questionText,int id);

    void updateResult(String msg);

    void fireIdArrayAndQuestionStringToUpdateAnswer(String toString, ArrayList<Integer> idArray);

    void fireAmericanAnswersString(String toString, int numOfAmericanAnswers, int id);


//    void addOpenQuestionMsg(String updateUserMessage);
//
//    String returnChosenQuestion(int id);
//    int returnAnswersSizeFromModel(int id);
//    String changeOpenQuestionAnswerInModel(String newAnswerText,int id);
//    int sendNumOfAnswersFromModel(int id);
//    String changeAmericanQuestionAnswerInModel(int id,String answerText,boolean correctness,int numOfAnswer);
//    String getAmericanQuestionsStringFromModel();
//    ArrayList getAmericanQuestionIDArrayFromModel();
//
//    void fireUpdateUserAddedQuestion(String result);
//    void fireQuestionString(String questionsString);
}
