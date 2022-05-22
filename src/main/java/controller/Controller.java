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
    public String showAllQuestionsInUI() {

        return qrModel.toString() ;
    }

    @Override
    public String addOpenQuestion(String questionText, String answerText) {
       return  qrModel.addOpenQuestion(questionText,answerText);
    }

    @Override
    public String returnChosenQuestion(int id) {
        return qrModel.getQuestionArray().get(id).toString();
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
    public String addAmericanQuestion(String text, ArrayList<String> answerArray, ArrayList<Boolean> correctnessArray) {
      return  qrModel.addAmericanQuestion(text,answerArray,correctnessArray);
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
