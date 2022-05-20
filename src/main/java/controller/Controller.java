package controller;

import id206214280_id316650399.QuestionReservoir;
import listeners.modelListener;
import listeners.viewListener;
import view.AbstractQuestionView;
import view.QuestionFxView;

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
    public void AddOpenQuestion(String questionText, String answerText) {
        qrModel.addOpenQuestion(questionText,answerText);
    }

    @Override
    public void addOpenQuestionmsg(String updateUserMessage) {
        questionView.addOpenQuestionToUI(updateUserMessage);
    }
}
