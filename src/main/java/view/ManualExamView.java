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
        Button clearAllButton = new Button("Clear all");
        selectQuestionsButtons.getChildren().addAll(selectQuestions, clearAllButton, returnButton);
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
                    Vector<Integer> noAnswers=new Vector<>();
                    for (viewListener l : allViewListeners) {
                        l.addQuestionToManualUI(numOfQuestions.get(i).intValue(),numOfQuestions.size(),noAnswers);
                    }
                }

            }

        });

    }


    public void showAmericanAnswerManualExam(String questionText, Vector<String> americanAnswers,int size, int numOfQuestion) {
        VBox americanAnswersSelect=new VBox();
        System.out.println(numOfQuestion);

        BorderPane selectAnswerBorderPane=new BorderPane();
        Scene americanAnswersScene;
        Button selectAnswers=new Button("Select Answers");
        HBox selectAnswersHBox=new HBox();
        selectAnswersHBox.getChildren().add(selectAnswers);
        selectAnswersHBox.setAlignment(Pos.CENTER);
        selectAnswerBorderPane.setRight(selectAnswersHBox);
        Stage americanAnswersStage=new Stage();
        Vector<CheckBox> americanAnswersCb=new Vector<>();
        americanAnswersSelect.getChildren().add(new Label(questionText));
        for(int i=0;i<americanAnswers.size();i++){
            CheckBox checkBox=new CheckBox(americanAnswers.get(i).toString());
            americanAnswersCb.add(checkBox);
            americanAnswersSelect.getChildren().add(americanAnswersCb.get(i));
        }
        selectAnswerBorderPane.setLeft(americanAnswersSelect);
        americanAnswersScene=new Scene(selectAnswerBorderPane);
        americanAnswersStage.setScene(americanAnswersScene);
        americanAnswersStage.show();

        selectAnswers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Vector<Integer> americanAnswersSelected=new Vector<>();
                for(int i=0;i<americanAnswersCb.size();i++){
                    if(americanAnswersCb.get(i).isSelected()){
                        americanAnswersSelected.add(i);
                    }
                }
                for(viewListener l:allViewListeners){
                    l.addQuestionToManualUI(numOfQuestion,size,americanAnswersSelected);
                }
                americanAnswersStage.hide();
            }

        });

    }
}
