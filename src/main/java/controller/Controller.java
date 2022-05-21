package controller;

import id206214280_id316650399.QuestionReservoir;
import listeners.modelListener;
import listeners.viewListener;
import view.AbstractQuestionView;
import view.QuestionFxView;

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
    public String addAmericanQuestion(String text, ArrayList<String> answerArray, ArrayList<Boolean> correctnessArray) {
      return  qrModel.addAmericanQuestion(text,answerArray,correctnessArray);
    }


    @Override
    public void addOpenQuestionmsg(String updateUserMessage) {
        questionView.addOpenQuestionToUI(updateUserMessage);
    }
}
