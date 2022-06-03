package view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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

    public void addQuestionToUI() {
        Stage addQuestionStage=new Stage();
        addQuestionStage.setTitle("Add Question");
        GridPane gpRootCase2 = new GridPane();
        gpRootCase2.setPadding(new Insets((10)));
        gpRootCase2.setHgap(10);
        gpRootCase2.setHgap(10);
        Button buttonReturn=new Button("Return to menu");
        Label choselbl = new Label("Choose which Question do you want ?");
        ToggleGroup tglAmericanOrOpen = new ToggleGroup();
        RadioButton americanrb = new RadioButton("American Question");
        RadioButton openRb = new RadioButton("Open Question");
        Label textQuestion = new Label("type the Question text:");
        textQuestion.setVisible(false);
        TextField questionTextField = new TextField();
        questionTextField.setVisible(false);
        Button addAmricanQuestionText = new Button("Add Question Text");
        Label countOfQuestionslbl = new Label("Choose How Many Answers Do You Want: ");
        countOfQuestionslbl.setVisible(false);
        ComboBox<Integer> cmdCountOfQuestions = new ComboBox<>();
        for (int i = 2; i <= 12; i++) {
            cmdCountOfQuestions.getItems().add(i);
        }
        cmdCountOfQuestions.setVisible(false);
        Label andTextLbl = new Label("Type a Text Answer:");
        TextField answerTextField = new TextField();
        answerTextField.setVisible(false);
        Button addAmericanAnsBt = new Button("Add Answer");
        ArrayList<String> answerArray = new ArrayList<>();
        ArrayList<Boolean> correctnessArray = new ArrayList<>();
        Label chooseTrueOrFalselbl = new Label("Choose true or false: ");
        chooseTrueOrFalselbl.setVisible(false);
        RadioButton trueBt = new RadioButton("True");
        trueBt.setVisible(false);
        RadioButton falseBt = new RadioButton("False");
        falseBt.setVisible(false);
        ToggleGroup tgChooseTOrF = new ToggleGroup();
        trueBt.setToggleGroup(tgChooseTOrF);
        falseBt.setToggleGroup(tgChooseTOrF);
        Button addAmericanQuestionbt = new Button("Add Question");
        addAmericanQuestionbt.setVisible(false);
        addAmericanQuestionbt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (viewListener l : allViewListeners) {
                    l.addAmericanQuestionUI(questionTextField.getText(), answerArray, correctnessArray);
                }
                addAmericanQuestionbt.setDisable(true);
            }
        });


        addAmericanAnsBt.setOnAction(new EventHandler<ActionEvent>() {
            int count = 0;

            @Override

            public void handle(ActionEvent actionEvent) {
                if (!answerTextField.getText().isEmpty()) {
                    answerArray.add(answerTextField.getText());
                    if (trueBt.isSelected()) {
                        correctnessArray.add(trueBt.isSelected());
                    } else if (falseBt.isSelected()) {
                        correctnessArray.add(!falseBt.isSelected());
                    }
                    cmdCountOfQuestions.setDisable(true);
                    count++;
                    if (count == cmdCountOfQuestions.getValue()) {
                        trueBt.setDisable(true);
                        falseBt.setDisable(true);
                        answerTextField.setDisable(true);
                        addAmericanQuestionbt.setVisible(true);
                        addAmericanAnsBt.setVisible(false);

                    }

                    answerTextField.clear();

                } else {
                    JOptionPane.showMessageDialog(null, "Error:answer text cannot be empty");
                }

            }
        });
        addAmericanAnsBt.setVisible(false);

        andTextLbl.setVisible(false);

        addAmricanQuestionText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!questionTextField.getText().isEmpty()) {
                    andTextLbl.setVisible(true);
                    addAmricanQuestionText.setDisable(true);
                    questionTextField.setDisable(true);
                    cmdCountOfQuestions.setVisible(true);
                    countOfQuestionslbl.setVisible(true);
                    answerTextField.setVisible(true);
                    addAmericanAnsBt.setVisible(true);
                    falseBt.setVisible(true);
                    trueBt.setVisible(true);
                    chooseTrueOrFalselbl.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error:question text cannot be empty\"");
                }
            }
        });
        addAmricanQuestionText.setVisible(false);
        Button addOpenQuestionText = new Button("Add Question Text");
        addOpenQuestionText.setVisible(false);
        Button addOpenAnsBt = new Button("Add Answer");
        addOpenAnsBt.setVisible(false);

        //user selected open question button
        openRb.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                americanrb.setDisable(true);
                textQuestion.setVisible(true);
                questionTextField.setVisible(true);
                addOpenQuestionText.setVisible(true);
            }
        });

        //user entered add open question text button
        addOpenQuestionText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!questionTextField.getText().isBlank()) {
                    andTextLbl.setVisible(true);
                    answerTextField.setVisible(true);
                    questionTextField.setDisable(true);
                    addOpenAnsBt.setVisible(true);
                    addOpenQuestionText.setDisable(true);
                } else {
                    String questionTextEmptyString = new String("Error:question text cannot be empty");
                    JOptionPane.showMessageDialog(null, questionTextEmptyString);
                    questionTextField.clear();
                }
            }
        });


        addOpenAnsBt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!answerTextField.getText().isBlank()) {
                    addOpenAnsBt.setDisable(true);
                    answerTextField.setDisable(true);
                    for (viewListener l : allViewListeners) {
                        l.addOpenQuestionUI(questionTextField.getText(), answerTextField.getText());
                    }
                } else {
                    String questionTextFieldEmptyString = new String("Error:answer text cannot be empty");
                    JOptionPane.showMessageDialog(null, questionTextFieldEmptyString);
                    answerTextField.clear();
                }
            }
        });

        americanrb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                textQuestion.setVisible(true);
                questionTextField.setVisible(true);
                openRb.setDisable(true);
                addAmricanQuestionText.setVisible(true);
            }
        });
        americanrb.setToggleGroup(tglAmericanOrOpen);
        openRb.setToggleGroup(tglAmericanOrOpen);
        addQuestionStage.setScene(new Scene(gpRootCase2, 650, 350));
        gpRootCase2.add(choselbl, 0, 0);
        gpRootCase2.add(americanrb, 0, 2);
        gpRootCase2.add(openRb, 0, 1);
        gpRootCase2.add(questionTextField, 1, 3);
        gpRootCase2.add(textQuestion, 0, 3);
        gpRootCase2.add(addAmricanQuestionText, 1, 4);
        gpRootCase2.add(addOpenQuestionText, 1, 4);
        gpRootCase2.add(countOfQuestionslbl, 0, 5);
        gpRootCase2.add(cmdCountOfQuestions, 1, 5);
        gpRootCase2.add(andTextLbl, 0, 6);
        gpRootCase2.add(answerTextField, 1, 6);
        gpRootCase2.add(addOpenAnsBt, 1, 7);
        gpRootCase2.add(chooseTrueOrFalselbl, 0, 8);
        gpRootCase2.add(trueBt, 1, 8);
        gpRootCase2.add(falseBt, 2, 8);
        gpRootCase2.add(addAmericanAnsBt, 1, 9);
        gpRootCase2.add(addAmericanQuestionbt, 1, 9);
        gpRootCase2.add(buttonReturn, 1, 11);
        addQuestionStage.show();
        buttonReturn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addQuestionStage.hide();
                menuView.showMainMenu(new Stage());
            }
        });
    }


}
