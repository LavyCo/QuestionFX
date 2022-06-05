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

public class UpdateAnswerWordingView {
    private Vector<viewListener> allViewListeners;
    private MenuView menuView;

    public UpdateAnswerWordingView(Vector<viewListener> allViewListeners,MenuView menuView){
        this.menuView=menuView;
        this.allViewListeners=allViewListeners;
    }

    public void showQuestionsAndId(String toString, ArrayList<Integer> idArray) {
        Stage questionAndIdStage=new Stage();
        questionAndIdStage.setTitle("Choose question by ID");
        ScrollPane questionAndIdSc=new ScrollPane(new Label(toString));
        ComboBox<Integer> idComboBox=new ComboBox<>();
        for(int i=0;i< idArray.size();i++){
            idComboBox.getItems().add(idArray.get(i));
        }
        Button selectIdButton=new Button("Select");
        Button returnToMenuButton=new Button("Return to Menu");
        BorderPane questionAndIdBp=new BorderPane();
        questionAndIdBp.setCenter(questionAndIdSc);
        VBox idSelectVbox=new VBox(new Label("Please select a question By Id"),idComboBox,selectIdButton,returnToMenuButton);
        questionAndIdBp.setRight(idSelectVbox);
        idSelectVbox.setSpacing(20);
        idSelectVbox.setAlignment(Pos.CENTER);
        Scene questionAndIdScene=new Scene(questionAndIdBp,640,480);
        questionAndIdStage.setScene(questionAndIdScene);
        questionAndIdStage.show();
        selectIdButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(idComboBox.getValue()!=null){
                    for(viewListener l:allViewListeners){
                        System.out.println("test");
                        l.showAnswerToUpdate(idComboBox.getValue().intValue());
                        questionAndIdStage.hide();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please choose an ID");
                }
            }
        });


    }

    public void showAmericanAnswerView(Vector<String> answerArray, int numOfAmericanAnswers, int id) {
        System.out.println("test");
        Stage selectAmericanAnswerUpdateStage=new Stage();
        selectAmericanAnswerUpdateStage.setTitle("Choose american answer text");
        Vector<RadioButton> answerRadioButton=new Vector<>();
        VBox radioButtonVbox=new VBox();
        radioButtonVbox.setSpacing(10);
        ToggleGroup answersToggleGroup=new ToggleGroup();
        for(int i=0;i<numOfAmericanAnswers;i++){
            RadioButton radioButton=new RadioButton(""+(i+1)+answerArray.get(i));
            answerRadioButton.add(radioButton);
            radioButtonVbox.getChildren().add(radioButton);
            answersToggleGroup.getToggles().add(radioButton);
        }
        Button chooseAnother=new Button("Choose another answer");
        Button submitButton=new Button("Submit");
        Button returnToMenu=new Button("Return to menu");
        VBox buttonsVbox=new VBox(submitButton,chooseAnother,returnToMenu);
        buttonsVbox.setAlignment(Pos.CENTER);
        buttonsVbox.setPadding(new Insets(10));
        buttonsVbox.setSpacing(10);
        TextField answerTextField=new TextField();
        RadioButton trueRb=new RadioButton("True");
        RadioButton falseRb=new RadioButton("False");
        ToggleGroup correctnessToggleGroup=new ToggleGroup();
        correctnessToggleGroup.getToggles().addAll(trueRb,falseRb);
        HBox selectCorrectnessHbox=new HBox(trueRb,falseRb);
        selectCorrectnessHbox.setAlignment(Pos.CENTER);
        selectCorrectnessHbox.setSpacing(10);
        VBox selectQuestionTextAndCorrectnessVBox=new VBox(new Label("Please type new answer text in the box below:"),answerTextField,selectCorrectnessHbox);
        BorderPane showAmericanAnswerBp=new BorderPane();
        selectQuestionTextAndCorrectnessVBox.setAlignment(Pos.CENTER);
        selectQuestionTextAndCorrectnessVBox.setSpacing(20);
        showAmericanAnswerBp.setPadding(new Insets(10));
        showAmericanAnswerBp.setCenter(selectQuestionTextAndCorrectnessVBox);
        showAmericanAnswerBp.setLeft(radioButtonVbox);
        showAmericanAnswerBp.setRight(buttonsVbox);
        Scene showAmericanAnswerScene=new Scene(showAmericanAnswerBp);
        selectAmericanAnswerUpdateStage.setScene(showAmericanAnswerScene);
        selectAmericanAnswerUpdateStage.show();
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(answersToggleGroup.getSelectedToggle()!=null){
                    if(!answerTextField.getText().isBlank()){
                        if(correctnessToggleGroup.getSelectedToggle()!=null){
                            int answerNumber=0;

                            for(int i=0;i<answerRadioButton.size();i++){
                                if(answerRadioButton.get(i)==answersToggleGroup.getSelectedToggle()){
                                     answerNumber=i;
                                }
                            }
                            boolean opt;
                            if(answersToggleGroup.getSelectedToggle()==trueRb){
                                 opt=true;
                            }
                            else{
                                 opt=false;
                            }

                            for(viewListener l:allViewListeners){
                                l.updateAmericanAnswer(answerTextField.getText(),id,answerNumber,opt);
                            }
                            submitButton.setDisable(true);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Must choose at least 1 True/False");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Please enter answer text");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Must choose an answer !");
                }
            }
        });

        chooseAnother.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectAmericanAnswerUpdateStage.hide();
                for(viewListener l:allViewListeners){
                    l.updateAnswerView();
                }
            }
        });

        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectAmericanAnswerUpdateStage.hide();
                menuView.showMainMenu(new Stage());
            }
        });





    }

    public void updateOpenAnswerView(String questionText, String answerText, int id) {
        Stage updateOpenAnswerStage=new Stage();
        updateOpenAnswerStage.setTitle("Open question answer update");
        VBox questionTextAnswerTextVbox=new VBox(new Label("Question is: "+questionText),new Label("Current answer is: "+answerText));
        questionTextAnswerTextVbox.setSpacing(10);
        HBox newAnswerTextHbox=new HBox(new Label("New answer text:"));
        newAnswerTextHbox.setSpacing(10);
        newAnswerTextHbox.setPadding(new Insets(10));
        TextField newTextField=new TextField();
        newAnswerTextHbox.getChildren().add(newTextField);
        newAnswerTextHbox.setSpacing(10);
        Button submitButton = new Button("Submit");
        Button chooseAnother=new Button("Choose another");
        Button returnToMenu=new Button("Return to menu");
        HBox buttonHbox=new HBox(submitButton,chooseAnother,returnToMenu);
        buttonHbox.setSpacing(10);
        VBox containerVbox=new VBox(questionTextAnswerTextVbox,newAnswerTextHbox,buttonHbox);
        containerVbox.setAlignment(Pos.CENTER);
        containerVbox.setPadding(new Insets(20));
        Scene updateOpenAnswerScene=new Scene(containerVbox);
        updateOpenAnswerStage.setScene(updateOpenAnswerScene);
        updateOpenAnswerStage.show();

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!newTextField.getText().isBlank()){
                    for(viewListener l:allViewListeners){
                        l.updateOpenQuestionViewToModel(newTextField.getText(),id);
                    }
                    submitButton.setDisable(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Text cannot be blank");
                }
            }
        });

        chooseAnother.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for(viewListener l:allViewListeners){
                    l.updateAnswerView();
                }
            }
        });

        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateOpenAnswerStage.hide();
                menuView.showMainMenu(new Stage());
            }
        });


    }
}
