package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listeners.viewListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class UpdateQuestionTextView {
    private Vector<viewListener> allViewListeners;
    private MenuView menuView;


    public  UpdateQuestionTextView(Vector<viewListener> allViewListeners, MenuView menuView){
        this.menuView=menuView;
        this.allViewListeners=allViewListeners;

    }


    public void showSelectAndIdUpdateQuestion(String questionsString, ArrayList<Integer> idArray) {
        Stage questionToUpdateStage=new Stage();
        BorderPane selectQuestionBp=new BorderPane();
        Scene questionToUpdateScene=new Scene(selectQuestionBp);
        VBox questionStringVbox=new VBox(new Label(questionsString));
        ScrollPane questionsStringSp=new ScrollPane(questionStringVbox);
        selectQuestionBp.setLeft(questionsStringSp);
        VBox selectQuestionVbox=new VBox();
        ComboBox<Integer> questionIdCombo=new ComboBox<>();

        for(int i=0;i<idArray.size();i++){
            questionIdCombo.getItems().add(idArray.get(i));
        }

        selectQuestionVbox.setAlignment(Pos.CENTER);
        selectQuestionVbox.setSpacing(30);
        Label choseIdLabel=new Label("Please choose question by ID");
        Button selectId=new Button("Select");
        Button returnToMenu=new Button("Return to menu");
        selectQuestionVbox.getChildren().addAll(choseIdLabel,questionIdCombo,selectId,returnToMenu);
        selectQuestionBp.setRight(selectQuestionVbox);
        questionToUpdateStage.setScene(questionToUpdateScene);
        questionToUpdateStage.show();

        selectId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(questionIdCombo.getValue()!=null){
                for(viewListener l:allViewListeners){
                    l.showChosenQuestionToUpdate(questionIdCombo.getValue().intValue());
                    questionToUpdateStage.hide();

                }}
                else{
                    JOptionPane.showMessageDialog(null,"Please select an ID before proceeding");
                }
            }
        });

        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                menuView.showMainMenu(new Stage());
                questionToUpdateStage.hide();
            }
        });


    }

    public void changeQuestionTextView(String questionText, int id) {
        Label questionTextLbl=new Label("Choosen question is:"+questionText);
        Button submitButton=new Button("Submit");
        Button chooseAnother=new Button("Choose another question");
        Button returnToMenu=new Button("Return to menu");
        Label enterText=new Label("Enter new question text:");
        TextField questionTextField=new TextField();
        VBox questionTextVbox=new VBox(questionTextLbl,enterText,questionTextField);
        HBox buttonsHbox=new HBox(submitButton,chooseAnother,returnToMenu);
        BorderPane updateTextBp=new BorderPane();
        updateTextBp.setCenter(questionTextVbox);
        updateTextBp.setBottom(buttonsHbox);
        updateTextBp.setPadding(new Insets(10));
        buttonsHbox.setSpacing(10);
        buttonsHbox.setPadding(new Insets(10));
        questionTextVbox.setSpacing(10);

        Scene updateQuestionScene=new Scene(updateTextBp);
        Stage updateQuestionStage=new Stage();
        updateQuestionStage.setScene(updateQuestionScene);
        updateQuestionStage.show();
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!questionTextField.getText().isBlank())
                for(viewListener l:allViewListeners){
                    l.changeQuestionText(questionTextField.getText(),id);
                    submitButton.setDisable(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Text field cannot be empty");
                    questionTextField.clear();
                }
            }
        });
        chooseAnother.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateQuestionStage.hide();
                for(viewListener l:allViewListeners){
                    l.showQuestionsAndIdToUpdate();
                }
            }
        });
        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateQuestionStage.hide();
                menuView.showMainMenu(new Stage());
            }
        });


    }
}

