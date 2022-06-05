package controller;

import id206214280_id316650399.AmericanQuestions;
import id206214280_id316650399.QuestionReservoir;
import listeners.modelListener;
import listeners.viewListener;
import view.AbstractQuestionView;
import view.MenuView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;

public class  Controller implements modelListener, viewListener {
    private QuestionReservoir qrModel;
    private AbstractQuestionView questionView;
    public Controller(QuestionReservoir qrModel,MenuView questionView )throws Exception {
        this.qrModel=qrModel;
        this.questionView=questionView;
        this.questionView.registerListener(this);
        this.qrModel.registerListener(this);
    }


    @Override
    public void showStringInUI() {
        qrModel.getAllQuestionsToView();
    }


    @Override
    public void fireQuestionString(String questionString) {
        questionView.printAllQuestionsToString(questionString);
    }

    @Override
    public void addAmericanQuestionUI(String text, ArrayList<String> answerArray, ArrayList<Boolean> correctnessArray) {
        qrModel.addAmericanQuestion(text,answerArray,correctnessArray);

    }

    @Override
    public void addOpenQuestionUI(String text, String text1) {
        qrModel.addOpenQuestion(text,text1);
    }


    @Override
    public void updateQuestionTextUI() {

    }

    //add
    @Override
    public void fireIdToUI(ArrayList<Integer> idArray) {
        questionView.showIdInUpdateQuestionView(idArray);
    }

    @Override
    public void fireQuestionsStringAndId(String toString, ArrayList<Integer> idArray) {
        questionView.showStringAndIDinQuestionView(idArray,toString);
    }

    @Override
    public void fireNumberOfQuestion(int numberOfQuestions) {
        questionView.getNumOfQuestionFromModel(numberOfQuestions);

    }

    @Override
    public void fireAutoExam(String autoExam) {
        questionView.showAutoExamToUI(autoExam);

    }


    //add
    @Override
    public void showIDInUI() {
        qrModel.getIDNumberArray();
    }

    //update question
    @Override
    public void showQuestionsAndIdToUpdate() {
        qrModel.sendIdAndQuestionsToUpdate();
    }

    @Override
    public void showChosenQuestionToUpdate(int id) {
        qrModel.getQuestionTextById(id);
    }


    @Override
    public void fireQuestionText(String questionText,int id) {
        questionView.showChosenQuestionToUpdateInView(questionText,id);
    }

    @Override
    public void updateResult(String msg) {
        questionView.showStatusMassage(msg);
    }

    @Override
    public void fireIdArrayAndQuestionStringToUpdateAnswer(String toString, ArrayList<Integer> idArray) {
        questionView.updateAnswerMenuView(toString,idArray);
    }

    @Override
    public void fireAmericanAnswersString(Vector<String> americanAnswerVector, int numOfAmericanAnswers, int id) {
        questionView.showAmericanAnswerInView(americanAnswerVector,numOfAmericanAnswers,id);
    }

    @Override
    public void fireUpdateAmericanAnswerResult(String msg) {
        questionView.showStatusMassage(msg);
    }

    @Override
    public void fireOpenAnswerUpdateString(String questionText, String answerText, int id) {
        questionView.updateOpenAnswerMainView(questionText,answerText,id);
    }

    @Override
    public void fireOpenAnswerUpdateResult(String s) {
        questionView.showStatusMassage(s);
    }

    @Override
    public void changeQuestionText(String text, int id) {
        qrModel.changeQuestionWording(text,id);

    }

    @Override
    public void updateDeleteAmericanAnswer() {

    }

    @Override
    public void updateAnswerView() {
        qrModel.sendIdAndQuestionsToUpdateAnswer();
    }

    @Override
    public void showAnswerToUpdate(int id) {
        System.out.println("test2");
        qrModel.getAnswerById(id);
    }

    @Override
    public void updateAmericanAnswer(String text, int id, int answerNumber, boolean opt) {
        qrModel.changeAnswerWordingOfAmericanQuestions(text, (AmericanQuestions) qrModel.fetchQuestionById(id),answerNumber,opt);
    }

    @Override
    public void updateOpenQuestionViewToModel(String text, int id) {
        qrModel.changeAnswerWordingOfOpenQuestion(text,qrModel.fetchQuestionById(id));
    }


    @Override
    public void getNumOfQuestions() {
        qrModel.getNumberOfQuestions();
    }

    @Override
    public void createAutoExam(int value) throws FileNotFoundException {
        qrModel.automaticExam(value);
    }



    @Override
    public void fireAmericanQuestionAddedResult(String string) {
        questionView.showStatusMassage(string);

    }

    @Override
    public void fireOpenQuestionAddedResult(String string) {
        questionView.showStatusMassage(string);
    }





//    @Override
//    public void showAllQuestionsInUI() {
//        qrModel.toString();
//
//    }
//
//    @Override
//    public void addOpenQuestion(String questionText, String answerText) {
//         qrModel.addOpenQuestion(questionText,answerText);
//    }
//
//    @Override
//    public String returnChosenQuestion(int id) {
//        return qrModel.fetchQuestionById(id).toString();
//    }
//
//    @Override
//    public int returnAnswersSizeFromModel(int id) {
//        if(qrModel.fetchQuestionById(id) instanceof AmericanQuestions){
//        return ((AmericanQuestions)qrModel.fetchQuestionById(id)).getNumOfAmericanAnswers();}
//        return 0;
//
//    }
//
//
//    @Override
//    public int addAmericanAnswersSizeToUI(int id) {
//        return returnAnswersSizeFromModel(id);
//    }
//
//    @Override
//    public String changeAmericanAnswerUI(int id, String answerText, boolean correctness, int numOfAnswer) {
//        return changeAmericanQuestionAnswerInModel(id,answerText,correctness,numOfAnswer);
//    }
//
//
//    @Override
//    public String changeAmericanQuestionAnswerInModel(int id, String answerText, boolean correctness,int numOfAnswer) {
//        return qrModel.changeAnswerWordingOfAmericanQuestions(answerText,(AmericanQuestions)qrModel.fetchQuestionById(id),numOfAnswer,correctness);
//    }
//
//
//
//    @Override
//    public int getNumOfAnswersToUI(int id) {
//        return ((AmericanQuestions)qrModel.fetchQuestionById(id)).getNumOfAmericanAnswers();
//    }
//
//    @Override
//    public String showAmericanQuestionUI() {
//        return getAmericanQuestionsStringFromModel();
//    }
//
//    @Override
//    public ArrayList<Integer> getAmericanQuestionIDArrayListUI() {
//        return getAmericanQuestionIDArrayFromModel();
//    }
//
//    @Override
//    public boolean checkIfAmerican(int id) {
//
//        return qrModel.checkIfAmerican(id);
//    }
//
//
//    @Override
//    public String getAmericanQuestionsStringFromModel() {
//        return qrModel.getAmericanQuestionsToPrint();
//    }
//
//    @Override
//    public ArrayList getAmericanQuestionIDArrayFromModel() {
//        return qrModel.fireAmericanQuestionIDArrayList();
//    }
//
//    @Override
//    public void fireUpdateUserAddedQuestion(String american_question_added) {
//        questionView.showAmericanQuestionMsg(american_question_added);
//    }
//
//    @Override
//    public void fireQuestionString(String toString) {
//        questionView.printAllQuestionstoString(toString);
//    }
//
//
//    @Override
//    public int sendNumOfAnswersFromModel(int id) {
//        return getNumOfAnswersToUI(id);
//    }
//
//
//
//    @Override
//    public String changeOpenQuestionAnswerInModel(String newAnswerText, int id) {
//        return qrModel.changeAnswerWordingOfOpenQuestion(newAnswerText,qrModel.fetchQuestionById(id));
//    }
//
//
//
//    @Override
//    public String changeOpenQuestionAnswerUI(String newAnswerText, int id) {
//        return changeOpenQuestionAnswerInModel(newAnswerText,id);
//    }
//
//
//    @Override
//    public String showChosenQuestion(int id) {
//        return returnChosenQuestion(id);
//    }
//
//    @Override
//    public void addAmericanQuestion(String text, ArrayList<String> answerArray, ArrayList<Boolean> correctnessArray) {
//      qrModel.addAmericanQuestion(text,answerArray,correctnessArray);
//    }
//
//    @Override
//    public String PrintAllQuestions() {
//        return qrModel.PrintAllQustionsModel();
//    }
//

//
//    @Override
//    public void addOpenQuestionMsg(String updateUserMessage) {
//        questionView.addOpenQuestionToUI(updateUserMessage);
//    }
}
