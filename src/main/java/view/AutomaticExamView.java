package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listeners.viewListener;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Vector;

public class AutomaticExamView {
    private Vector<viewListener> allViewListeners;
    private MenuView menuView;

    public AutomaticExamView(Vector<viewListener> allViewListeners,MenuView menuView){
        this.menuView=menuView;
        this.allViewListeners=allViewListeners;
    }
    public void showMainMenu(){
        menuView.showMainMenu(new Stage());
    }

    public void showNumbersOfQuestions(int numberOfQuestions) {

        ComboBox<Integer> cmdCountOfQuestions = new ComboBox<>();
        for (int i=0;i<numberOfQuestions;i++){
            cmdCountOfQuestions.getItems().add(i+1);
        }

        HBox selectHowManyHbox=new HBox();

        Stage case7=new Stage();
        case7.setTitle("Automatic Exam");

        GridPane gpRoot = new GridPane();
        gpRoot.setPadding(new Insets((10)));
        gpRoot.setHgap(10);
        gpRoot.setHgap(10);
        selectHowManyHbox.setSpacing(10);

        Label titleLbl=new Label("select how much questions do you want in the test :");
        selectHowManyHbox.getChildren().addAll(titleLbl,cmdCountOfQuestions);
        HBox selectButtonReturnHbox=new HBox();
        selectButtonReturnHbox.setSpacing(10);

        VBox vBoxContainer=new VBox(selectHowManyHbox,selectButtonReturnHbox);
        vBoxContainer.setPadding(new Insets(10));
        Scene newScene=new Scene(vBoxContainer);
        case7.setScene(newScene);
        Button selectBtn=new Button("Select");

        Button returnToMenu=new Button("Return to menu");
        selectButtonReturnHbox.getChildren().addAll(selectBtn,returnToMenu);

        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                case7.hide();
                showMainMenu();
            }
        });

        selectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(cmdCountOfQuestions.getValue()!=null){
                    for(viewListener l:allViewListeners){
                        try {
                            l.createAutoExam(cmdCountOfQuestions.getValue().intValue());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    case7.hide();

                }
                else{
                    JOptionPane.showMessageDialog(null,"Please choose number of questions");
                }

            }
        });
        gpRoot.setPadding(new Insets(10));

        case7.show();

    }


    public void automaticExam(){
        for(viewListener l:allViewListeners){
            l.getNumOfQuestions();
        }
    }

    public void showExam(String autoExam) {
        Stage autoExamStage=new Stage();
        autoExamStage.setTitle("The new Automatic Exam");
        BorderPane borderPane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(borderPane);
        Scene newScene=new Scene(scrollPane,500,500);
        Label autoExamText=new Label(autoExam);
        borderPane.setTop( autoExamText);
        autoExamStage.setScene(newScene);
        Button okBt=new Button("Return to menu");
        okBt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                autoExamStage.close();
                showMainMenu();
            }
        });
        borderPane.setBottom(okBt);
        autoExamStage.show();


    }
}
