package listeners;

import java.util.ArrayList;
import java.util.Vector;

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

    void fireAmericanAnswersString(Vector<String> americanAnswers, int numOfAmericanAnswers, int id);

    void fireUpdateAmericanAnswerResult(String msg);

    void fireOpenAnswerUpdateString(String questionText, String answerText, int id);

    void fireOpenAnswerUpdateResult(String s);

    void fireCloneMassege(String msg);

    void fireSaveMsg(String msg);

    void fireAmericanId(String americanQuestionsString, Vector<Integer> americanIdVector);

    void fireAmericanQuestionData(String americanQuestionString, Vector<String> americanAnswerString, int id);


    void fireRemoveAmericanAnswerMsg(String toString, Vector<String> americanAnswerToDelete, int id, String result);

    void fireQuestionStringManualExam(Vector<String> allQuestionString);



    void fireAmericanAnswersManualExam(String questionText,int questionNumber, int size, Vector<String> answersString);

    void fireShowManualExam(String toString);


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
