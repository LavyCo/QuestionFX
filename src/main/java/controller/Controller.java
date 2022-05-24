package controller;

import id206214280_id316650399.AmericanQuestions;
import id206214280_id316650399.QuestionReservoir;
import listeners.modelListener;
import listeners.viewListener;
import view.AbstractQuestionView;

import java.util.ArrayList;

public class Controller implements modelListener, viewListener {
    private QuestionReservoir qrModel;
    private AbstractQuestionView questionView;
    public Controller(QuestionReservoir qrModel,AbstractQuestionView questionView )throws Exception {
        this.qrModel=qrModel;
        this.questionView=questionView;
        this.questionView.registerListener(this);
        this.qrModel.registerListener(this);


    }


    @Override
    public void showAllQuestionsInUI() {
        qrModel.toString();

    }

    @Override
    public void addOpenQuestion(String questionText, String answerText) {
         qrModel.addOpenQuestion(questionText,answerText);
    }

    @Override
    public String returnChosenQuestion(int id) {
        return qrModel.fetchQuestionById(id).toString();
    }

    @Override
    public int returnAnswersSizeFromModel(int id) {
        if(qrModel.fetchQuestionById(id) instanceof AmericanQuestions){
        return ((AmericanQuestions)qrModel.fetchQuestionById(id)).getNumOfAmericanAnswers();}
        return 0;

    }


    @Override
    public int addAmericanAnswersSizeToUI(int id) {
        return returnAnswersSizeFromModel(id);
    }

    @Override
    public String changeAmericanAnswerUI(int id, String answerText, boolean correctness, int numOfAnswer) {
        return changeAmericanQuestionAnswerInModel(id,answerText,correctness,numOfAnswer);
    }


    @Override
    public String changeAmericanQuestionAnswerInModel(int id, String answerText, boolean correctness,int numOfAnswer) {
        return qrModel.changeAnswerWordingOfAmericanQuestions(answerText,(AmericanQuestions)qrModel.fetchQuestionById(id),numOfAnswer,correctness);
    }



    @Override
    public int getNumOfAnswersToUI(int id) {
        return ((AmericanQuestions)qrModel.fetchQuestionById(id)).getNumOfAmericanAnswers();
    }

    @Override
    public String showAmericanQuestionUI() {
        return getAmericanQuestionsStringFromModel();
    }

    @Override
    public ArrayList<Integer> getAmericanQuestionIDArrayListUI() {
        return getAmericanQuestionIDArrayFromModel();
    }

    @Override
    public boolean checkIfAmerican(int id) {

        return qrModel.checkIfAmerican(id);
    }


    @Override
    public String getAmericanQuestionsStringFromModel() {
        return qrModel.getAmericanQuestionsToPrint();
    }

    @Override
    public ArrayList getAmericanQuestionIDArrayFromModel() {
        return qrModel.fireAmericanQuestionIDArrayList();
    }

    @Override
    public void fireUpdateUserAddedQuestion(String american_question_added) {
        questionView.showAmericanQuestionMsg(american_question_added);
    }

    @Override
    public void fireQuestionString(String toString) {
        questionView.printAllQuestionstoString(toString);
    }


    @Override
    public int sendNumOfAnswersFromModel(int id) {
        return getNumOfAnswersToUI(id);
    }



    @Override
    public String changeOpenQuestionAnswerInModel(String newAnswerText, int id) {
        return qrModel.changeAnswerWordingOfOpenQuestion(newAnswerText,qrModel.fetchQuestionById(id));
    }



    @Override
    public String changeOpenQuestionAnswerUI(String newAnswerText, int id) {
        return changeOpenQuestionAnswerInModel(newAnswerText,id);
    }


    @Override
    public String showChosenQuestion(int id) {
        return returnChosenQuestion(id);
    }

    @Override
    public void addAmericanQuestion(String text, ArrayList<String> answerArray, ArrayList<Boolean> correctnessArray) {
      qrModel.addAmericanQuestion(text,answerArray,correctnessArray);
    }

    @Override
    public String PrintAllQuestions() {
        return qrModel.PrintAllQustionsModel();
    }

    @Override
    public ArrayList<Integer> GetAllIDfromModel() {
        ArrayList<Integer>allID=new ArrayList<>();
        for (int i=0;i<qrModel.getNumberOfQuestions();i++){
            allID.add(qrModel.getQuestionArray().get(i).getQuestionId());

        }
        return allID;
    }


    @Override
    public String ChangeWording(String text, int id) {
        return qrModel.changeQuestionWording(text,id);
    }



    @Override
    public void addOpenQuestionMsg(String updateUserMessage) {
        questionView.addOpenQuestionToUI(updateUserMessage);
    }
}
