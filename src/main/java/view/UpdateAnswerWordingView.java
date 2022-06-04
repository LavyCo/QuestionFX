package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
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

    public void showAmericanAnswerView(String toString, int numOfAmericanAnswers, int id) {
        System.out.println("test");
        Stage selectAmericanAnswerUpdateStage=new Stage();
        Vector<RadioButton> answerRadioButton=new Vector<>();
        VBox radioButtonVbox=new VBox();
        for(int i=0;i<numOfAmericanAnswers;i++){
            RadioButton radioButton=new RadioButton(""+(i+1));
            answerRadioButton.add(radioButton);
            radioButtonVbox.getChildren().add(radioButton);
        }
        VBox answerStringVbox=new VBox(new Label(toString));
        VBox buttonsVbox=new VBox(new Button("Submit"),new Button("Return to menu"));
        BorderPane showAmericanAnswerBp=new BorderPane();
        showAmericanAnswerBp.setLeft(radioButtonVbox);
        showAmericanAnswerBp.setCenter(answerStringVbox);
        showAmericanAnswerBp.setRight(buttonsVbox);
        Scene showAmericanAnswerScene=new Scene(showAmericanAnswerBp);
        selectAmericanAnswerUpdateStage.setScene(showAmericanAnswerScene);
        selectAmericanAnswerUpdateStage.show();




    }
}
