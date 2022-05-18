package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class QuestionFxView implements AbstractQuestionView{
    private ComboBox<String> cmdAllQuestions= new ComboBox<String>();
public QuestionFxView(Stage theStage){
    theStage.setTitle("make Exam");
    GridPane gpRoot=new GridPane();
    gpRoot.setPadding(new Insets((10)));
    gpRoot.setHgap(10);
    gpRoot.setHgap(10);
    Button button=new Button();
    button.setText("select");


    Label wellcomelbl =new Label("Welcome to the Exam generator program");
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
            if(printrb.isSelected()){
                JOptionPane.showMessageDialog(null, " print select");
            }
            else if (addQrb.isSelected()){
                JOptionPane.showMessageDialog(null, " add select");
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
    gpRoot.add(wellcomelbl, 0, 0);
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
    }

