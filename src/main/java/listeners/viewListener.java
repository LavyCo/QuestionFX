package listeners;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public interface viewListener {


    void showStringInUI();

    void addAmericanQuestionUI(String text, ArrayList<String> answerArray, ArrayList<Boolean> correctnessArray);

    void addOpenQuestionUI(String text, String text1);


    void showQuestionsAndIdToUpdate();

    void getNumOfQuestions();

    void createAutoExam(int value) throws FileNotFoundException;

    void showChosenQuestionToUpdate(int id);

    void changeQuestionText(String text, int id);


    void updateAnswerView();

    void showAnswerToUpdate(int intValue);

    void updateAmericanAnswer(String text, int id, int answerNumber, boolean opt);

    void updateOpenQuestionViewToModel(String text, int id);

    void cloneLastExam(int chose) throws FileNotFoundException, CloneNotSupportedException;

    void saveQuestions() throws IOException, ClassNotFoundException;

    void deleteAmericanAnswer();

    void getAmericanQuestionToDelete(int intValue);

    void deleteAmericanAnswerFromModel(int id, int numberOfAnswerToDelete);

    void manualExamUI();


    void addQuestionToManualUI(int questionNumber, int size);

    void addAmericanQuestionManualExamUI(int questionNumber, Vector<Integer> chosenAmericanAnswers, int size);


}
