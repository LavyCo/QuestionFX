package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import listeners.viewListener;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class PrintQuestionsView    {
    private Vector<viewListener> allViewListeners;
    private MenuView menuView;

    public  PrintQuestionsView(Vector<viewListener> allViewListeners, MenuView menuView){
        this.menuView=menuView;
        this.allViewListeners=allViewListeners;

    }


    public void printAllQuestionInView() {
        for (viewListener l:allViewListeners){
            l.showStringInUI();
        }
    }

    public void showMainMenu(){
        menuView.showMainMenu(new Stage());
    }

    public void printAllQuestionstoString(String allQuestions) {
        BorderPane borderPane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(borderPane);
        Label allQuestionsString=new Label(allQuestions);
        Scene printScene=new Scene(scrollPane,640,480);
        borderPane.setTop( allQuestionsString);
        Button returnToMainMenu=new Button("Return to main menu");
        borderPane.setBottom(returnToMainMenu);
        Stage printStage=new Stage();
        printStage.setScene(printScene);
        printStage.show();

        returnToMainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printStage.hide();
                showMainMenu();
            }
        });


    }

}
