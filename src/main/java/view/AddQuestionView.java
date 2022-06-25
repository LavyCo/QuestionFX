package view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import listeners.viewListener;

import java.util.ArrayList;
import java.util.Vector;
import javafx.event.EventHandler;

import javax.swing.*;

public class AddQuestionView {

    private MenuView menuView;
    private Vector<viewListener> allViewListeners=new Vector<>();
    public AddQuestionView(Vector<viewListener> allViewListeners, MenuView menuView){
        this.menuView=menuView;
        this.allViewListeners=allViewListeners;
    }

    public void chooseWhichQuestionTypeView(){
        VBox americanOrOpenVBox=new VBox();
        americanOrOpenVBox.setSpacing(10);
        americanOrOpenVBox.setPadding(new Insets(10));
        Label americanOrOpenLabel=new Label("Please choose which type of question you want to add");
        Button americanQuestionBtn=new Button("American Question");
        Button openQuestionBtn=new Button("Open Question");
        Button returnToMenu=new Button("Return to menu");
        americanOrOpenVBox.getChildren().addAll(americanOrOpenLabel,americanQuestionBtn,openQuestionBtn,returnToMenu);
        VBox submitReturnVbox=new VBox();
        submitReturnVbox.setPadding(new Insets(10));
        submitReturnVbox.setSpacing(10);

        HBox hboxContainer=new HBox(americanOrOpenVBox,submitReturnVbox);
        hboxContainer.setMinSize(1000,400);

        Scene chooseQuestionScene=new Scene(hboxContainer);
        Stage chooseQuestionStage=new Stage();

        chooseQuestionStage.setScene(chooseQuestionScene);
        chooseQuestionStage.show();

        //if user choose AmericanQuestion
        americanQuestionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                americanQuestionBtn.setDisable(true);
                openQuestionBtn.setDisable(true);
                VBox americanQuestionAddVbox=new VBox();
                Label chooseText=new Label("Please enter American Question text");
                TextField americanQuestionText=new TextField();
                Button submitButton=new Button("Proceed");
                Label chooseHowManyAnswer=new Label("Please choose how many answers for the new question");
                americanQuestionAddVbox.setAlignment(Pos.CENTER_LEFT);
                ComboBox<Integer> numberOfAnswersComboBox=new ComboBox<>();
                Vector<Integer> vectorInteger=new Vector<>();
                for(int i=2;i<=10;i++){
                    numberOfAnswersComboBox.getItems().add(i);
                    vectorInteger.add(i);
                }
                americanQuestionAddVbox.getChildren().addAll(chooseText,americanQuestionText,chooseHowManyAnswer,numberOfAnswersComboBox,submitButton);
                americanQuestionAddVbox.setPadding(new Insets(50));
                americanQuestionAddVbox.setSpacing(20);
                hboxContainer.getChildren().add(americanQuestionAddVbox);
                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (numberOfAnswersComboBox.getValue() == null) {
                            JOptionPane.showMessageDialog(null, "Please select number of answers");
                        }
                        else if(americanQuestionText.getText().isBlank()){
                            americanQuestionText.clear();
                            JOptionPane.showMessageDialog(null,"American Question text cannot be blank");
                        }

                        else {
                            submitButton.setDisable(true);
                            americanQuestionText.setDisable(true);
                            numberOfAnswersComboBox.setDisable(true);
                            VBox vBoxOfVboxes = new VBox();
                            Vector<TextField> textFieldVector = new Vector<>();
                            Vector<ToggleGroup> toggleGroupVector=new Vector<>();


                            for (int i = 1; i < numberOfAnswersComboBox.getValue().intValue() + 1; i++) {
                                Label label = new Label("Answer #" + i);
                                ToggleGroup tfTg = new ToggleGroup();
                                RadioButton trueRb = new RadioButton("True");
                                RadioButton falseRb = new RadioButton("False");

                                trueRb.setToggleGroup(tfTg);
                                falseRb.setToggleGroup(tfTg);
                                toggleGroupVector.add(tfTg);

                                HBox trueFalseHbox=new HBox(trueRb,falseRb);
                                trueFalseHbox.setPadding(new Insets(10));
                                trueFalseHbox.setSpacing(10);
                                TextField textField = new TextField();
                                textFieldVector.add(textField);
                                vBoxOfVboxes.getChildren().addAll(label, textField,trueFalseHbox);
                            }

                            ScrollPane vBoxSp=new ScrollPane(vBoxOfVboxes);
                            vBoxOfVboxes.setSpacing(10);
                            vBoxOfVboxes.autosize();
                            vBoxOfVboxes.setMinWidth(100);
                            vBoxSp.setContent(vBoxOfVboxes);
                            vBoxSp.setFitToWidth(true);
                            hboxContainer.getChildren().add(vBoxSp);
                            Button submitButton=new Button("Submit");
                            submitButton.setPadding(new Insets(20));
                            hboxContainer.getChildren().add(submitButton);
                            hboxContainer.setAlignment(Pos.CENTER);

                            submitButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    submitButton.setDisable(true);
                                    for(int i=0;i<numberOfAnswersComboBox.getValue().intValue();i++){
                                        textFieldVector.get(i).setDisable(true);
                                    }
                                    String questionText=americanQuestionText.getText();
                                    int numberOfAnswers=numberOfAnswersComboBox.getValue().intValue();
                                    ArrayList<String> americanAnswersTextVector=new ArrayList<>();
                                    ArrayList<Boolean> correctnessVector=new ArrayList<>();
                                    for(int i=0;i<numberOfAnswersComboBox.getValue();i++){
                                        if(textFieldVector.get(i).getText().isBlank()){
                                            JOptionPane.showMessageDialog(null,"Please fill all text fields");
                                            break;
                                        }
                                        if(toggleGroupVector.get(i).getSelectedToggle()==null){
                                            JOptionPane.showMessageDialog(null,"Please select correctness");
                                            break;
                                        }
                                        else{
                                            americanAnswersTextVector.add(textFieldVector.get(i).getText());

                                            if(toggleGroupVector.get(i).getSelectedToggle().toString().contains("True")){
                                                correctnessVector.add(true);
                                            }
                                            else{
                                                correctnessVector.add(false);
                                            }
                                        }

                                    }
                                    for(viewListener l:allViewListeners){
                                        l.addAmericanQuestionUI(americanQuestionText.getText(),americanAnswersTextVector,correctnessVector);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });

        openQuestionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                openQuestionBtn.setDisable(true);
                americanQuestionBtn.setDisable(true);
                VBox openQuestionVbox=new VBox();
                Label enterQuestionText=new Label("Please enter question text");
                TextField questionTextField=new TextField();
                Label enterAnswerText=new Label("Please enter answer text");
                TextField answerTextField=new TextField();
                Button submitButton=new Button("Submit");
                openQuestionVbox.setSpacing(20);
                openQuestionVbox.setPadding(new Insets(10));
                openQuestionVbox.setAlignment(Pos.CENTER);

                openQuestionVbox.getChildren().addAll(enterQuestionText,questionTextField,enterAnswerText,answerTextField,submitButton);
                hboxContainer.getChildren().add(openQuestionVbox);
                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(questionTextField.getText().isBlank()||answerTextField.getText().isBlank()){
                            JOptionPane.showMessageDialog(null,"Error:Text field cannot be empty");
                        }
                        else {
                            for (viewListener l : allViewListeners) {
                                l.addOpenQuestionUI(questionTextField.getText(), answerTextField.getText());
                            }
                        }

                    }
                });
            }
        });

        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                menuView.showMainMenu(new Stage());
                chooseQuestionStage.hide();
            }
        });


    }



}
