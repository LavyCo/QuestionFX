package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import listeners.viewListener;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public  class  MenuView implements AbstractQuestionView {


    private ComboBox<String> cmdAllQuestions = new ComboBox<String>();
    private Vector<viewListener> allViewListeners=new Vector<>();
    private  AddQuestionView addQuestionView=new AddQuestionView(allViewListeners,this);
    private UpdateQuestionTextView updateQuestionTextView=new UpdateQuestionTextView(allViewListeners,this);
    private PrintQuestionsView printQuestionsView=new PrintQuestionsView(allViewListeners,this);
    private AutomaticExamView automaticExamView=new AutomaticExamView(allViewListeners,this);
    private UpdateAnswerWordingView updateAnswerView=new UpdateAnswerWordingView(allViewListeners,this);
    private CloneExamView cloneExamView=new CloneExamView(allViewListeners,this);
    private DeleteAmericanAnswerView deleteAmericanAnswerView=new DeleteAmericanAnswerView(allViewListeners,this);
    private ManualExamView manualExamView=new ManualExamView(allViewListeners,this);

    public MenuView() {
        Stage theStage=new Stage();
        showMainMenu(theStage);
    }

    public void showMainMenu(Stage theStage){
        theStage.setTitle("Exam generator");
        GridPane gpRoot = new GridPane();
        gpRoot.setPadding(new Insets((10)));
        gpRoot.setHgap(10);
        gpRoot.setHgap(10);
        Button selectButtonMain = new Button();
        selectButtonMain.setText("Select");
        Label welcomelbl = new Label("Welcome to the Exam generator program");
        Label choselbl = new Label("Please choose an option from the menu below");
        ToggleGroup tglOpt = new ToggleGroup();
        RadioButton printrb = new RadioButton("Show all questions and their answers in the in the reservoir");
        RadioButton addQrb = new RadioButton("Add questions");
        RadioButton changeWordQrb = new RadioButton("Change the question wording");
        RadioButton updateAnsWordrb = new RadioButton("Update the wording of an answer");
        RadioButton deleteAns = new RadioButton("Delete an answer to a question");
        RadioButton manualExamrb = new RadioButton("Create a test manually");
        RadioButton autoExamrb = new RadioButton("Create a test automatically");
        RadioButton clonerb = new RadioButton("Clone an exam");
        RadioButton saveExitrb = new RadioButton("Save changes in questions reservoir and exit program");
        //make the radio button group
        printrb.setToggleGroup(tglOpt);
        addQrb.setToggleGroup(tglOpt);
        changeWordQrb.setToggleGroup(tglOpt);
        updateAnsWordrb.setToggleGroup(tglOpt);
        deleteAns.setToggleGroup(tglOpt);
        manualExamrb.setToggleGroup(tglOpt);
        autoExamrb.setToggleGroup(tglOpt);
        clonerb.setToggleGroup(tglOpt);
        saveExitrb.setToggleGroup(tglOpt);
        //change color text
        printrb.setTextFill(Color.GREEN);
        addQrb.setTextFill(Color.GREEN);
        changeWordQrb.setTextFill(Color.GREEN);
        updateAnsWordrb.setTextFill(Color.GREEN);
        deleteAns.setTextFill(Color.GREEN);
        manualExamrb.setTextFill(Color.GREEN);
        autoExamrb.setTextFill(Color.GREEN);
        clonerb.setTextFill(Color.GREEN);
        saveExitrb.setTextFill(Color.RED);
        Scene newScene=new Scene(gpRoot,500,300);
        theStage.setScene(newScene);
        gpRoot.add(welcomelbl, 0, 0);
        gpRoot.add(choselbl, 0, 1);
        gpRoot.add(printrb, 0, 2);
        gpRoot.add(addQrb, 0, 3);
        gpRoot.add(changeWordQrb, 0, 4);
        gpRoot.add(updateAnsWordrb, 0, 5);
        gpRoot.add(deleteAns, 0, 6);
        gpRoot.add(manualExamrb, 0, 7);
        gpRoot.add(autoExamrb, 0, 8);
        gpRoot.add(clonerb, 0, 9);
        gpRoot.add(saveExitrb, 0, 10);
        gpRoot.add(selectButtonMain, 1, 11);
        theStage.show();

        selectButtonMain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(printrb.isSelected()){
                    theStage.hide();
                    showQuestionsUI();
                }

                if(addQrb.isSelected()){
                    theStage.hide();
                    addQuestionToUI();
                }

                if(changeWordQrb.isSelected()){
                    theStage.hide();
                    for(viewListener l:allViewListeners){
                        l.showQuestionsAndIdToUpdate();
                    }
                }
                if(updateAnsWordrb.isSelected()){
                    theStage.hide();
                    for(viewListener l:allViewListeners){
                        l.updateAnswerView();
                    }
                }
                if(deleteAns.isSelected()){
                    theStage.hide();
                        for (viewListener l : allViewListeners) {
                            l.deleteAmericanAnswer();}
                }
                if(manualExamrb.isSelected()){
                    theStage.hide();
                    for(viewListener l:allViewListeners){
                        l.manualExamUI();
                    }
                }
                if (autoExamrb.isSelected()){
                    theStage.hide();
                    automaticExamView.automaticExam();
                }
                if(clonerb.isSelected()){
                    theStage.hide();
                    cloneExamView.cloneExam();

                }
                if(saveExitrb.isSelected()){
                    for (viewListener l:allViewListeners){
                        try {
                            l.saveQuestions();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    theStage.close();
                }

            }

        });

    }


    @Override
    public void getNumOfQuestionFromModel(int numberOfQuestions) {
        automaticExamView.showNumbersOfQuestions(numberOfQuestions);
    }

    @Override
    public void showAutoExamToUI(String autoExam) {
        automaticExamView.showExam(autoExam);

    }


    @Override
    public void showChosenQuestionToUpdateInView(String questionText,int id) {
        updateQuestionTextView.changeQuestionTextView(questionText,id);
    }

    @Override
    public void updateAnswerMenuView(String toString, ArrayList<Integer> idArray) {
        updateAnswerView.showQuestionsAndId(toString,idArray);
    }

    @Override
    public void updateOpenAnswerMainView(String questionText, String answerText, int id) {
        updateAnswerView.updateOpenAnswerView(questionText,answerText,id);
    }

    @Override
    public void massageFromModel(String msg) {
        JOptionPane.showMessageDialog(null,msg);
    }

    @Override
    public void SaveMsgFromModel(String msg) {
        JOptionPane.showMessageDialog(null,msg);
    }

    @Override
    public void showAmericanQuestionId(String americanQuestionsString, Vector<Integer> americanIdVector) {
        DeleteAmericanAnswerView deleteAmericanAnswerView=new DeleteAmericanAnswerView(this,allViewListeners,americanIdVector,americanQuestionsString);
        deleteAmericanAnswerView.showAmericanQuestionsData();

    }

    @Override
    public void showChosenAmericanQuestionToDelete(String americanQuestionString, Vector<String> americanAnswerString, int id) {
        deleteAmericanAnswerView.showChosenAmericanQuestionDelete(americanQuestionString,americanAnswerString,id);
    }

    @Override
    public void updateDeleteAnswerView(String toString, Vector<String> americanAnswerToDelete, int id, String result) {
        JOptionPane.showMessageDialog(null,result);
        deleteAmericanAnswerView.showChosenAmericanQuestionDelete(toString,americanAnswerToDelete,id);
    }

    @Override
    public void showIdInUpdateQuestionView(ArrayList<Integer> idArray) {

    }

    @Override
    public void showManualExamSelection(Vector<String> allQuestionString) {
        ManualExamView manualExamView=new ManualExamView(allViewListeners,this,allQuestionString);
        manualExamView.showQuestionsForManualExam();
    }

    @Override
    public void showAmericanAnswerInView(Vector<String> americanAnswerVector, int numOfAmericanAnswers, int id) {

    }

    @Override
    public void showAmericanAnswersSelectionManualExams(String questionText, int questionNumber, int size, Vector<String> answersString) {
        manualExamView.showAmericanAnswerManualExam(questionText,answersString,size,questionNumber);
    }

    @Override
    public void showManualExamUI(String examString) {
        manualExamView.showManualExamUI(examString);
    }


    @Override
    public void registerListener(viewListener newListener) {
        allViewListeners.add(newListener);
    }

    @Override
    public void showStatusMassage(String result) {
        JOptionPane.showMessageDialog(null,result);

    }

    @Override
    public void showStringAndIDinQuestionView(ArrayList<Integer> idArray, String questionsString) {
        updateQuestionTextView.showSelectAndIdUpdateQuestion(questionsString,idArray);
    }


    @Override
    public void showQuestionsUI() {
        printQuestionsView.printAllQuestionInView();
    }

    @Override
    public void addQuestionToUI() {
        addQuestionView.addQuestionToUI();
    }

    @Override
    public void printAllQuestionsToString(String questionString) {
        printQuestionsView.printAllQuestionstoString(questionString);
    }


}
