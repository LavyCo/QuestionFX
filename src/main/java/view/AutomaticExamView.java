package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listeners.viewListener;

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
        Stage case7=new Stage();
        case7.setTitle("Automatic Exam");
        GridPane gpRoot = new GridPane();
        gpRoot.setPadding(new Insets((10)));
        gpRoot.setHgap(10);
        gpRoot.setHgap(10);
        Label titlelbl=new Label("select how much questions do you want in the test :");
        Scene newScene=new Scene(gpRoot,500,300);
        case7.setScene(newScene);
        Button returnToMenu=new Button("Return to menu");
        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                case7.hide();
                showMainMenu();
            }
        });

        Button selectb=new Button("Select");
        selectb.setOnAction(new EventHandler<ActionEvent>() {
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

            }
        });
        gpRoot.add(titlelbl,0,0);
        gpRoot.add(cmdCountOfQuestions,1,1);
        gpRoot.add(selectb,1,2);
        gpRoot.add(returnToMenu,2,2);

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
        Scene newScene=new Scene(scrollPane,300,300);
        Label autoExamText=new Label(autoExam);
        borderPane.setTop( autoExamText);
        autoExamStage.setScene(newScene);
        Button okBt=new Button("OK");
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
