package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listeners.viewListener;

import java.util.ArrayList;
import java.util.Vector;

public class ManualExamView {

    private Vector<String> allQuestionsString;
    private Vector<viewListener> allViewListeners;
    private Stage manualExamStage = new Stage();
    private MenuView menuView;
    private Stage americanAnswerStage =new Stage();
    private Scene americanAnswerScene;

    public ManualExamView(Vector<viewListener> allViewListeners, MenuView menuView, Vector<String> allQuestionString) {
        this.allViewListeners = allViewListeners;
        this.menuView = menuView;
        this.allQuestionsString = allQuestionString;
    }


    public ManualExamView(Vector<viewListener> allViewListeners, MenuView menuView) {
        this.allViewListeners = allViewListeners;
        this.menuView = menuView;
    }



    public void showQuestionsForManualExam() {

        manualExamStage.setTitle("Manual exam creator");
        VBox selectQuestionsVbox = new VBox();
        VBox selectQuestionsButtons = new VBox();
        ScrollPane selectQuestionScrollPane = new ScrollPane(selectQuestionsVbox);
        ArrayList<CheckBox> questionCb = new ArrayList<>();
        Button selectQuestions = new Button("Select");
        Button returnButton = new Button("Return to menu");
        selectQuestionsButtons.getChildren().addAll(selectQuestions, returnButton);
        selectQuestionsButtons.setAlignment(Pos.CENTER);
        selectQuestionsButtons.setSpacing(20);
        BorderPane selectQuestionBorderPane = new BorderPane();
        selectQuestionBorderPane.setPadding(new Insets(10));
        selectQuestionBorderPane.setLeft(selectQuestionScrollPane);
        selectQuestionBorderPane.setRight(selectQuestionsButtons);
        for (int i = 0; i < allQuestionsString.size(); i++) {
            CheckBox checkBox = new CheckBox(allQuestionsString.get(i));
            questionCb.add(checkBox);
            selectQuestionsVbox.getChildren().add(questionCb.get(i));
        }
        Scene showQuestionsScene = new Scene(selectQuestionBorderPane);
        manualExamStage.setScene(showQuestionsScene);
        manualExamStage.show();
        selectQuestions.setDisable(true);
        for (int i = 0; i < questionCb.size(); i++) {

            questionCb.get(i).setOnAction(new EventHandler<ActionEvent>() {
                int counter = 0;

                @Override
                public void handle(ActionEvent actionEvent) {
                    int selectCounter = 0;
                    for (int j = 0; j < questionCb.size(); j++) {
                        if (questionCb.get(j).isSelected()) {
                            selectCounter++;
                        }
                    }
                    if (selectCounter == 0) {
                        selectQuestions.setDisable(true);
                    } else {
                        selectQuestions.setDisable(false);
                    }
                }
            });
        }

        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                menuView.showMainMenu(new Stage());
                manualExamStage.hide();
            }
        });


        selectQuestions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectQuestions.setDisable(true);
                for(int i=0;i< questionCb.size();i++){
                    questionCb.get(i).setDisable(true);
                }

                ArrayList<Integer> numOfQuestions = new ArrayList<>();
                for (int i = 0; i < questionCb.size(); i++) {
                    if (questionCb.get(i).isSelected()) {
                        numOfQuestions.add(i);
                    }

                }
                for (int i = 0; i < numOfQuestions.size(); i++) {
                    for (viewListener l : allViewListeners) {
                        l.addQuestionToManualUI(numOfQuestions.get(i).intValue(),numOfQuestions.size());
                    }
                }
                manualExamStage.hide();


            }



        });


    }



    public void showAmericanAnswerManualExam(String questionText, Vector<String> answerString, int size, int questionNumber) {
        VBox americanQuestionVbox=new VBox();
        Vector<CheckBox> answersCb=new Vector<>();
        Button selectButton=new Button("Select answers");
        HBox americanQuestionHbox=new HBox();
        BorderPane americanQuestionBp=new BorderPane();
        americanQuestionHbox.getChildren().add(selectButton);
        americanQuestionVbox.getChildren().add(new Label(questionText));
        americanQuestionBp.setLeft(americanQuestionVbox);
        americanQuestionBp.setRight(americanQuestionHbox);
        for(int i=0;i<answerString.size();i++){
            System.out.println(answerString.get(i));
            CheckBox checkBox=new CheckBox(answerString.get(i));
            answersCb.add(checkBox);
            americanQuestionVbox.getChildren().add(answersCb.get(i));
        }
        americanAnswerScene=new Scene(americanQuestionBp);
        americanAnswerStage.setScene(americanAnswerScene);
        selectButton.setDisable(true);



        for (int i = 0; i < answersCb.size(); i++) {

            answersCb.get(i).setOnAction(new EventHandler<ActionEvent>() {
                int counter = 0;

                @Override
                public void handle(ActionEvent actionEvent) {
                    int selectCounter = 0;
                    for (int j = 0; j < answersCb.size(); j++) {
                        if (answersCb.get(j).isSelected()) {
                            selectCounter++;
                        }
                    }
                    if (selectCounter < 4) {
                        selectButton.setDisable(true);
                    } else {
                        selectButton.setDisable(false);
                    }
                }
            });
        }


        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Vector<Integer> chosenAmericanAnswers=new Vector<>();
                for(int i=0;i< answersCb.size();i++){
                    if(answersCb.get(i).isSelected()){
                        chosenAmericanAnswers.add(i);
                    }
                }
                for(int i=0;i<answersCb.size();i++){
                    answersCb.get(i).setDisable(true);
                }
                for(viewListener l:allViewListeners){
                    l.addAmericanQuestionManualExamUI(questionNumber,chosenAmericanAnswers,size);
                    selectButton.setDisable(true);
                    americanAnswerStage.close();
                }
            }
        });
        americanAnswerStage.showAndWait();

    }


    public void showManualExamUI(String examString) {
        ScrollPane examScrollPane=new ScrollPane();
        Button returnToMenu=new Button("Return to menu");
        Stage showExamStage=new Stage();
        VBox examVbox=new VBox(new Label(examString));
        examVbox.setPadding(new Insets(20));
        BorderPane examBorderPane=new BorderPane();
        examBorderPane.setPadding(new Insets(10));
        examScrollPane.setContent(examVbox);
        HBox buttonsHbox=new HBox();
        buttonsHbox.getChildren().add(returnToMenu);
        buttonsHbox.setAlignment(Pos.CENTER);
        examBorderPane.setLeft(examScrollPane);
        examBorderPane.setRight(buttonsHbox);
        Scene examScene=new Scene(examBorderPane);
        showExamStage.setScene(examScene);
        showExamStage.show();
        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                menuView.showMainMenu(new Stage());
                showExamStage.close();
            }
        });
    }
}
