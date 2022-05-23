package view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import listeners.viewListener;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Vector;

public class QuestionFxView implements AbstractQuestionView {
    private ComboBox<String> cmdAllQuestions = new ComboBox<String>();
    private Vector<viewListener> allListeners = new Vector<viewListener>();

    public QuestionFxView(Stage theStage) {

        theStage.setTitle("Exam generator");
        GridPane gpRoot = new GridPane();
        gpRoot.setPadding(new Insets((10)));
        gpRoot.setHgap(10);
        gpRoot.setHgap(10);
        Button button = new Button();
        button.setText("Select");

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
        printrb.setTextFill(Color.RED);
        addQrb.setTextFill(Color.RED);
        changeWordQrb.setTextFill(Color.RED);
        updateAnsWordrb.setTextFill(Color.RED);
        deleteAns.setTextFill(Color.RED);
        manualExamrb.setTextFill(Color.RED);
        autoExamrb.setTextFill(Color.RED);
        clonerb.setTextFill(Color.RED);
        saveExitrb.setTextFill(Color.RED);

        //connect between the radio button to select button
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage case1 = new Stage();
                Stage case2 = new Stage();
                Stage case3 = new Stage();
                Stage case4 = new Stage();
                Stage case5 = new Stage();
                Stage case6 = new Stage();
                Stage case7 = new Stage();
                Stage case8 = new Stage();
                Stage case9 = new Stage();
                Button buttonReturn = new Button("Return to menu");
                buttonReturn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (printrb.isSelected()) {
                            case1.hide();
                        } else if (addQrb.isSelected()) {
                            case2.hide();
                        } else if (changeWordQrb.isSelected()) {
                            case3.hide();
                        } else if (updateAnsWordrb.isSelected()) {
                            case4.hide();
                        } else if (deleteAns.isSelected()) {
                            case5.hide();
                        } else if (manualExamrb.isSelected()) {
                            case6.hide();
                        } else if (autoExamrb.isSelected()) {
                            case7.hide();
                        } else if (clonerb.isSelected()) {
                            case8.hide();
                        } else if (saveExitrb.isSelected()) {
                            case9.hide();
                        }


                        theStage.show();
                    }

                });
                //case 1
                if (printrb.isSelected()) {
                    theStage.hide();
                    for (viewListener l : allListeners) {
                        BorderPane borderPane = new BorderPane();
                        ScrollPane scrollPane = new ScrollPane();
                        String allQuestionsPrint = l.showAllQuestionsInUI();
                        Label printLbl = new Label(allQuestionsPrint);
                        case1.setTitle("All Questions");
                        borderPane.setCenter(printLbl);
                        scrollPane.setContent(borderPane);
                        case1.setScene(new Scene(scrollPane, 750, 750));
                        Label allQuestionLbl = new Label("The questions are:");
                        borderPane.setTop(allQuestionLbl);
                        VBox vBoxReturnButton = new VBox(buttonReturn);
                        vBoxReturnButton.setAlignment(Pos.CENTER);
                        buttonReturn.setText("Return to menu");
                        borderPane.setBottom(vBoxReturnButton);
                        case1.show();

                    }


                    //   JOptionPane.showMessageDialog(null, " print select");
                } else if (addQrb.isSelected()) {
                    theStage.hide();
                    case2.setTitle("Add Question");
                    GridPane gpRootCase2 = new GridPane();
                    gpRootCase2.setPadding(new Insets((10)));
                    gpRootCase2.setHgap(10);
                    gpRootCase2.setHgap(10);
                    buttonReturn.setText("Return To Menu");
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

                            for (viewListener l : allListeners) {
                                String americanMassege = l.addAmericanQuestion(questionTextField.getText(), answerArray, correctnessArray);
                                JOptionPane.showMessageDialog(null, americanMassege);
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
                            if (!questionTextField.getText().isEmpty()) {
                                andTextLbl.setVisible(true);
                                answerTextField.setVisible(true);
                                questionTextField.setDisable(true);
                                addOpenAnsBt.setVisible(true);
                            } else {
                                String questionTextEmptyString = new String("Error:question text cannot be empty");
                                JOptionPane.showMessageDialog(null, questionTextEmptyString);
                            }
                        }
                    });


                    addOpenAnsBt.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (!answerTextField.getText().isEmpty()) {
                                answerTextField.setDisable(true);
                                for (viewListener l : allListeners) {
                                    String addOpenUpdate = l.addOpenQuestion(questionTextField.getText(), answerTextField.getText());
                                    JOptionPane.showMessageDialog(null, addOpenUpdate);
                                }
                            } else {
                                String questionTextFieldEmptyString = new String("Error:answer text cannot be empty");
                                JOptionPane.showMessageDialog(null, questionTextFieldEmptyString);
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
                    case2.setScene(new Scene(gpRootCase2, 650, 350));

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

                    case2.show();

                    // JOptionPane.showMessageDialog(null, " add select");
                } else if (changeWordQrb.isSelected()) {
                    theStage.hide();
                    case3.setTitle("Change Question wording");
                    GridPane gpRootCase3 = new GridPane();
                    gpRootCase3.setPadding(new Insets((10)));
                    gpRootCase3.setHgap(10);
                    gpRootCase3.setHgap(10);
                    Label chooseFromListlbl = new Label("Choose from the list the Question do you want to Change: ");
                    for (viewListener l : allListeners) {
                        String allQuestions = l.PrintAllQuestions();
                        Label allQustionslbl = new Label(allQuestions);
                        gpRootCase3.add(allQustionslbl, 0, 1);
                        ArrayList<Integer> allId = new ArrayList<>();
                        allId = l.GetAllIDfromModel();
                        ComboBox<Integer> cmdId = new ComboBox<>();
                        for (int i = 0; i < allId.size(); i++) {
                            cmdId.getItems().add(allId.get(i));
                        }
                        TextField newWordingtf = new TextField();
                        newWordingtf.setVisible(false);
                        Label newWordinglbl = new Label("Type new Wording for the Question:");
                        newWordinglbl.setVisible(false);
                        Button changeWordingbt = new Button("Change Wording");
                        changeWordingbt.setVisible(false);
                        changeWordingbt.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                String msg = l.ChangeWording(newWordingtf.getText(), cmdId.getValue());
                                JOptionPane.showMessageDialog(null, msg);
                            }
                        });
                        gpRootCase3.add(cmdId, 1, 1);
                        cmdId.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (cmdId.getValue() != null) {
                                    newWordingtf.setVisible(true);
                                    newWordinglbl.setVisible(true);
                                    changeWordingbt.setVisible(true);
                                }
                            }
                        });
                        gpRootCase3.add(newWordinglbl, 0, 2);
                        gpRootCase3.add(newWordingtf, 1, 2);
                        gpRootCase3.add(changeWordingbt, 1, 3);
                    }


                    buttonReturn.setText("Return To Menu");
                    case3.setScene(new Scene(gpRootCase3, 850, 350));
                    gpRootCase3.add(chooseFromListlbl, 0, 0);

                    gpRootCase3.add(buttonReturn, 1, 11);
                    case3.show();


                    //JOptionPane.showMessageDialog(null, "  change wording select");
                } else if (updateAnsWordrb.isSelected()) {
                    theStage.hide();
                    //print all questions and their answers and allow user to choose a question by radio button
                    BorderPane bpCase4 = new BorderPane();
                    ScrollPane spCase4=new ScrollPane(bpCase4);
                    spCase4.setContent(bpCase4);
                    HBox hbCase4 = new HBox();
                    bpCase4.setBottom(hbCase4);
                    System.out.println("sss");
                    Label questionComboBoxLbl = new Label("Please choose a question by ID");
                    for (viewListener l : allListeners) {
                        String allQuestions = l.showAllQuestionsInUI();
                        Label allQustionslbl = new Label(allQuestions);
                        bpCase4.setCenter(allQustionslbl);
                        ArrayList<Integer> allId;
                        allId = l.GetAllIDfromModel();
                        ComboBox<Integer> cmdId = new ComboBox<>();
                        for (int i = 0; i < allId.size(); i++) {
                            cmdId.getItems().add(allId.get(i));
                        }
                        Button chooseIdBtn = new Button();
                        chooseIdBtn.setText("choose");
                        hbCase4.getChildren().addAll(questionComboBoxLbl, cmdId, chooseIdBtn);
                        hbCase4.setSpacing(5);

                        hbCase4.setPadding(new Insets(20));
                        case4.setTitle("Change answer wording");
                        case4.setScene(new Scene(spCase4,700,700));
                        case4.show();

                        chooseIdBtn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                case4.hide();
                                Label printSelectedQuestion = new Label(l.showChosenQuestion(cmdId.getValue()));
                                GridPane selectAnswerGp = new GridPane();
                                //checks if answer is open
                                if (l.addAmericanAnswersSizeToUI(cmdId.getValue()) == 0) {
                                    TextField openAnswerTextField = new TextField();
                                    Label openAnswerTextFieldLbl = new Label("Please enter a new answer");
                                    selectAnswerGp.add(printSelectedQuestion, 0, 0);
                                    selectAnswerGp.add(openAnswerTextField, 0, 2);
                                    selectAnswerGp.add(openAnswerTextFieldLbl, 0, 1);
                                    selectAnswerGp.setPadding(new Insets(20));
                                    selectAnswerGp.setVgap(10);
                                    Button openAnswerSubmitBtn = new Button("Submit new text");
                                    selectAnswerGp.add(openAnswerSubmitBtn, 0, 4);
                                    Scene selectAnswerScene = new Scene(selectAnswerGp);
                                    Stage case4SelectedQuestion = new Stage();
                                    case4SelectedQuestion.setScene(selectAnswerScene);
                                    case4SelectedQuestion.setTitle("Select answer");
                                    case4SelectedQuestion.show();
                                    openAnswerSubmitBtn.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent actionEvent) {
                                            if (openAnswerTextField.getText().isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Error:answer text cannot be empty");
                                            } else {
                                                String result = new String(l.changeOpenQuestionAnswerUI(openAnswerTextField.getText(), cmdId.getValue()));
                                                JOptionPane.showMessageDialog(null, result);
                                                case4SelectedQuestion.hide();
                                                theStage.show();

                                            }
                                        }
                                    });
                                    //else-question is american
                                } else {
                                    BorderPane mainSelectAnswerFrame = new BorderPane();
                                    TextField americanAnswerTextField = new TextField();
                                    americanAnswerTextField.setDisable(true);
                                    Label americanAnswerTextFieldLbl = new Label("Please enter a new answer");
                                    mainSelectAnswerFrame.setLeft(printSelectedQuestion);
                                    mainSelectAnswerFrame.setPadding(new Insets(10));
                                    int numOfAnswers = l.getNumOfAnswersToUI(cmdId.getValue());
                                    ComboBox<Integer> answerIndexCmb = new ComboBox<>();
                                    for (int i = 0; i < numOfAnswers; i++) {
                                        answerIndexCmb.getItems().add(i + 1);
                                    }
                                    selectAnswerGp.setPadding(new Insets(10));
                                    selectAnswerGp.setHgap(10);

                                    Label chooseAnswerIndexLbl = new Label("Please choose answer index");
                                    Button answerSubmitBtn = new Button("Submit answer");
                                    VBox answerIndexSelectionVbox = new VBox(chooseAnswerIndexLbl, answerIndexCmb);
                                    answerIndexSelectionVbox.setAlignment(Pos.CENTER);
                                    answerIndexSelectionVbox.setPadding(new Insets(10));
                                    mainSelectAnswerFrame.setCenter(answerIndexSelectionVbox);

                                    RadioButton trueRb = new RadioButton("True");
                                    RadioButton falseRb = new RadioButton("False");
                                    ToggleGroup TFrb = new ToggleGroup();
                                    trueRb.setToggleGroup(TFrb);
                                    falseRb.setToggleGroup(TFrb);
                                    Label correctnessLabel = new Label("Please choose if question is true or false");
                                    HBox answerTextAndCorrectnessHbox = new HBox(americanAnswerTextFieldLbl, americanAnswerTextField, trueRb, falseRb, answerSubmitBtn, buttonReturn);
                                    answerTextAndCorrectnessHbox.setPadding(new Insets(10));
                                    answerTextAndCorrectnessHbox.setSpacing(10);
                                    trueRb.setDisable(true);
                                    falseRb.setDisable(true);
                                    answerSubmitBtn.setDisable(true);

                                    mainSelectAnswerFrame.setBottom(answerTextAndCorrectnessHbox);
                                    Scene selectAnswerScene = new Scene(mainSelectAnswerFrame);
                                    Stage case4SelectedQuestion = new Stage();
                                    case4SelectedQuestion.setScene(selectAnswerScene);
                                    case4SelectedQuestion.setTitle("Select answer");
                                    case4SelectedQuestion.show();
                                    buttonReturn.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent actionEvent) {
                                            case4SelectedQuestion.hide();
                                            theStage.show();

                                        }
                                    });

                                    answerIndexCmb.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent actionEvent) {
                                            answerSubmitBtn.setDisable(false);
                                            trueRb.setDisable(false);
                                            falseRb.setDisable(false);
                                            americanAnswerTextField.setDisable(false);
                                            answerSubmitBtn.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent actionEvent) {
                                                    if (!americanAnswerTextField.getText().isEmpty() && (trueRb.isSelected() || falseRb.isSelected())) {
                                                        if (trueRb.isSelected()) {
                                                            JOptionPane.showMessageDialog(null, l.changeAmericanAnswerUI(cmdId.getValue(), americanAnswerTextField.getText(), true, answerIndexCmb.getValue()));
                                                            answerSubmitBtn.setDisable(true);
                                                            trueRb.setDisable(true);
                                                            falseRb.setDisable(true);
                                                            americanAnswerTextField.setDisable(true);
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, l.changeAmericanAnswerUI(cmdId.getValue(), americanAnswerTextField.getText(), false, answerIndexCmb.getValue()));
                                                            answerSubmitBtn.setDisable(true);
                                                            trueRb.setDisable(true);
                                                            falseRb.setDisable(true);
                                                            americanAnswerTextField.setDisable(true);
                                                        }

                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Error:Text Field or Correctness missing");
                                                    }
                                                }
                                            });

                                        }
                                    });
                                }
                            }
                        });
                    }


                } else if (deleteAns.isSelected()) {
                    BorderPane bpCase5=new BorderPane();
                    ScrollPane spCase5=new ScrollPane(bpCase5);
                    Scene case5Scene=new Scene(spCase5,700,700);
                    case5.setScene(case5Scene);
                    for(viewListener l:allListeners){
                        Label americanQuestionsLbl=new Label(l.showAmericanQuestionUI());
                        bpCase5.setCenter(americanQuestionsLbl);
                        ComboBox<Integer> americanQuestionIdCmb=new ComboBox<>();
                        ArrayList<Integer> americanQuestionIDArrayList=l.getAmericanQuestionIDArrayListUI();
                        for(int i=0;i<americanQuestionIDArrayList.size();i++){
                            americanQuestionIdCmb.getItems().add(americanQuestionIDArrayList.get(i));
                        }
                        bpCase5.setBottom(americanQuestionIdCmb);
                    }
                    case5.setTitle("Delete answer of an american question");
                    case5.show();
                } else if (manualExamrb.isSelected()) {
                    theStage.hide();
                    case6.show();
                    case6.setTitle("Create Exam");
                    GridPane gpRootCase6 = new GridPane();
                    gpRootCase6.setPadding(new Insets((10)));
                    gpRootCase6.setHgap(10);
                    gpRootCase6.setHgap(10);

                    JOptionPane.showMessageDialog(null, "  manual exam select");

                } else if (autoExamrb.isSelected()) {
                    JOptionPane.showMessageDialog(null, "  automatic exam select");
                } else if (clonerb.isSelected()) {
                    JOptionPane.showMessageDialog(null, "  clone wording select");
                } else if (saveExitrb.isSelected()) {
                    JOptionPane.showMessageDialog(null, "  save and exit select");
                }


            }
        });
        //Arrange the buttons on the stage
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
        gpRoot.add(button, 1, 11);


//size of the stage
        theStage.setScene(new Scene(gpRoot, 450, 350));
        //show stage
        theStage.show();



    }


    @Override
    public void registerListener(viewListener newListener) {
        allListeners.add(newListener);
    }

    @Override
    public String addOpenQuestionToUI(String updateUserMessage) {
        return updateUserMessage;
    }


}

