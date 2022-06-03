package listeners;

import java.util.ArrayList;

public interface modelListener {

    void fireQuestionString(String questionString);

    void fireAmericanQuestionAddedResult(String string);

    void fireOpenQuestionAddedResult(String string);

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
