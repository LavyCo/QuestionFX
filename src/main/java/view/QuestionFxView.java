package view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import listeners.viewListener;

import javax.swing.JOptionPane;
import java.util.Vector;

public class QuestionFxView implements AbstractQuestionView {
    private ComboBox<String> cmdAllQuestions= new ComboBox<String>();
    private Vector<viewListener> allListeners = new Vector<viewListener>();
public QuestionFxView(Stage theStage){

    theStage.setTitle("Exam generator");
    GridPane gpRoot=new GridPane();
    gpRoot.setPadding(new Insets((10)));
    gpRoot.setHgap(10);
    gpRoot.setHgap(10);
    Button button=new Button();
    button.setText("Select");


    Label welcomelbl =new Label("Welcome to the Exam generator program");
    Label choselbl =new Label("Please choose an option from the menu below");
    ToggleGroup tglOpt=new ToggleGroup();
    RadioButton printrb=new RadioButton("Show all questions and their answers in the in the reservoir");
    RadioButton addQrb=new RadioButton("Add questions");
    RadioButton changewordQrb=new RadioButton("Change the question wording");
    RadioButton updateAnsWordrb=new RadioButton("Update the wording of an answer");
    RadioButton deleteAns=new RadioButton("Delete an answer to a question");
    RadioButton manualExamrb=new RadioButton("Create a test manually");
    RadioButton autoExamrb=new RadioButton("Create a test automatically");
    RadioButton clonerb=new RadioButton("Clone an exam");
    RadioButton saveExitrb=new RadioButton("Save changes in questions reservoir and exit program");
    //make the radio button group
    printrb.setToggleGroup(tglOpt);
    addQrb.setToggleGroup(tglOpt);
    changewordQrb.setToggleGroup(tglOpt);
    updateAnsWordrb.setToggleGroup(tglOpt);
    deleteAns.setToggleGroup(tglOpt);
    manualExamrb.setToggleGroup(tglOpt);
    autoExamrb.setToggleGroup(tglOpt);
    clonerb.setToggleGroup(tglOpt);
    saveExitrb.setToggleGroup(tglOpt);
    //change color text
    printrb.setTextFill(Color.GREEN);
    addQrb.setTextFill(Color.GREEN);
    changewordQrb.setTextFill(Color.GREEN);
    updateAnsWordrb.setTextFill(Color.GREEN);
    deleteAns.setTextFill(Color.GREEN);
    manualExamrb.setTextFill(Color.GREEN);
    autoExamrb.setTextFill(Color.GREEN);
    clonerb.setTextFill(Color.GREEN);
    saveExitrb.setTextFill(Color.GREEN);

    //connect between the radio button to select button
    button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Stage case1=new Stage();
            Stage case2=new Stage();
            Stage case3=new Stage();
            Stage case4=new Stage();
            Stage case5=new Stage();
            Stage case6=new Stage();
            Stage case7=new Stage();
            Stage case8=new Stage();
            Stage case9=new Stage();
            Button buttonreturn=new Button();
            buttonreturn.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent actionEvent) {
                    if(printrb.isSelected()) {
                        case1.hide();
                    }
                    else if (addQrb.isSelected()){
                        case2.hide();
                    }
                    else if (changewordQrb.isSelected()){
                        case3.hide();
                    }
                    else if (updateAnsWordrb.isSelected()){
                        case4.hide();
                    }
                    else if (deleteAns.isSelected()){
                        case5.hide();
                    }
                    else if (manualExamrb.isSelected()){
                        case6.hide();
                    }
                    else if (autoExamrb.isSelected()){
                        case7.hide();
                    }
                    else if (clonerb.isSelected()){
                        case8.hide();
                    }
                    else if (saveExitrb.isSelected()){
                        case9.hide();
                    }



                    theStage.show();
                }

            });
            //case 1
            if(printrb.isSelected()){
                theStage.hide();
                for(viewListener l:allListeners) {
                    String allQuestionsPrint = l.showAllQuestionsInUI();
                    Label printLbl = new Label(allQuestionsPrint);
                    case1.setTitle("All Questions");
                    GridPane gpRootCase1 = new GridPane();
                    gpRootCase1.setPadding(new Insets((10)));
                    gpRootCase1.setHgap(10);
                    gpRootCase1.setHgap(10);
                    buttonreturn.setText("Return To Menu");
                    Label allQuestionLbl = new Label("The questions are:");
                    case1.setScene(new Scene(gpRootCase1, 750, 750));
                    gpRootCase1.add(allQuestionLbl, 0, 0);
                    gpRootCase1.add(printLbl, 0, 1);
                    gpRootCase1.add(buttonreturn, 1, 11);
                    case1.show();

                }



             //   JOptionPane.showMessageDialog(null, " print select");
            }
            else if (addQrb.isSelected()){
                theStage.hide();
                case2.setTitle("Add Question");
                GridPane gpRootCase2=new GridPane();
                gpRootCase2.setPadding(new Insets((10)));
                gpRootCase2.setHgap(10);
                gpRootCase2.setHgap(10);
                buttonreturn.setText("Return To Menu");
                Label choselbl =new Label("Choose which Question do you want ?");
                ToggleGroup tglAmericanOrOpen=new ToggleGroup();
                RadioButton americanrb=new RadioButton("American Question");
                RadioButton openrb=new RadioButton("Open Question");
                Label textQuestion=new Label("type the Question text:" ) ;
                textQuestion.setVisible(false);
                TextField questionTextField=new TextField();
                questionTextField.setVisible(false);


                Button addAmricanQuestionText=new Button("Add Question Text");
                Label countOfQuestionslbl=new Label("Chose How Many Answers Do You Want: ");
                countOfQuestionslbl.setVisible(false);
                ComboBox<Integer> cmdCountOfQuestions=new ComboBox<Integer>();
                for (int i=2;i<=12;i++){
                    cmdCountOfQuestions.getItems().add(i);
                }



                cmdCountOfQuestions.setVisible(false);
                Label AnsTextlbl=new Label("Type a Text Answer:");
                TextField answerTextField=new TextField();
                answerTextField.setVisible(false);
                Button addAmericanAnsbt=new Button("Add Answer");
                addAmericanAnsbt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        cmdCountOfQuestions.setDisable(true);

                    }
                });
                addAmericanAnsbt.setVisible(false);

                AnsTextlbl.setVisible(false);
                addAmricanQuestionText.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        AnsTextlbl.setVisible(true);
                        addAmricanQuestionText.setDisable(true);
                        questionTextField.setDisable(true);
                        cmdCountOfQuestions.setVisible(true);
                        countOfQuestionslbl.setVisible(true);
                        answerTextField.setVisible(true);
                        addAmericanAnsbt.setVisible(true);
                    }
                });
                addAmricanQuestionText.setVisible(false);
                Button addOpenQuestionText= new Button("Add Question Text");
                addOpenQuestionText.setVisible(false);
                Button addOpenAnsbt=new Button("Add Answer");
                addOpenAnsbt.setVisible(false);
                addOpenAnsbt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        answerTextField.setDisable(true);
                        for (viewListener l:allListeners){
                            l.AddOpenQuestion(questionTextField.getText(),answerTextField.getText());

                        }

                    }
                });
                addOpenQuestionText.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        AnsTextlbl.setVisible(true);
                        answerTextField.setVisible(true);
                        questionTextField.setDisable(true);
                        addOpenAnsbt.setVisible(true);

                    }
                });
       openrb.setOnAction(new EventHandler<ActionEvent>() {
          @Override
        public void handle(ActionEvent actionEvent) {
              americanrb.setDisable(true);
              textQuestion.setVisible(true);
              questionTextField.setVisible(true);
              addOpenQuestionText.setVisible(true);


       }
        });
                americanrb.setOnAction(new EventHandler<ActionEvent>() {


                    @Override
                    public void handle(ActionEvent actionEvent) {

                           textQuestion.setVisible(true);
                            questionTextField.setVisible(true);
                           openrb.setDisable(true);
                           addAmricanQuestionText.setVisible(true);






                    }
                });


                        americanrb.setToggleGroup(tglAmericanOrOpen);
                openrb.setToggleGroup(tglAmericanOrOpen);
                case2.setScene(new Scene(gpRootCase2, 450, 350));

                gpRootCase2.add(choselbl,0,0);
                gpRootCase2.add(americanrb,0,2);
                gpRootCase2.add(openrb,0,1);
                gpRootCase2.add(questionTextField,1,3);
                gpRootCase2.add(textQuestion,0,3);
                gpRootCase2.add(addAmricanQuestionText,1,4);
                gpRootCase2.add(addOpenQuestionText,1,4);
                gpRootCase2.add(countOfQuestionslbl,0,5);
                gpRootCase2.add(cmdCountOfQuestions,1,5);
                gpRootCase2.add(AnsTextlbl,0,6);
                gpRootCase2.add(answerTextField,1,6);
                gpRootCase2.add(addAmericanAnsbt,1,7);
                gpRootCase2.add(addOpenAnsbt,1,7);
                gpRootCase2.add(buttonreturn, 1, 11);

                case2.show();

               // JOptionPane.showMessageDialog(null, " add select");
            }
            else if (changewordQrb.isSelected()){
                JOptionPane.showMessageDialog(null, "  change wording select");
            }
            else if (updateAnsWordrb.isSelected()){
                JOptionPane.showMessageDialog(null, "  update answer wording select");
            }
            else if (deleteAns.isSelected()){
                JOptionPane.showMessageDialog(null, "  delete answer select");
            }
            else if (manualExamrb.isSelected()){
                JOptionPane.showMessageDialog(null, "  manual exam select");
            }
            else if (autoExamrb.isSelected()){
                JOptionPane.showMessageDialog(null, "  automatic exam select");
            }
            else if (clonerb.isSelected()){
                JOptionPane.showMessageDialog(null, "  clone wording select");
            }
            else if (saveExitrb.isSelected()){
                JOptionPane.showMessageDialog(null, "  save and exit select");
            }



        }
    });
    //Arrange the buttons on the stage
    gpRoot.add(welcomelbl, 0, 0);
    gpRoot.add(choselbl, 0, 1);
    gpRoot.add(printrb, 0, 2);
    gpRoot.add(addQrb, 0, 3);
    gpRoot.add(changewordQrb, 0, 4);
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
}

