package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import listeners.viewListener;

import java.io.FileNotFoundException;
import java.util.Vector;

public class CloneExamView {
    private Vector<viewListener> allViewListeners;
    private MenuView menuView;
    public CloneExamView(Vector<viewListener> allViewListeners,MenuView menuView){
        this.menuView=menuView;
        this.allViewListeners=allViewListeners;
    }
    public void showMainMenu(){
        menuView.showMainMenu(new Stage());
    }
    public void cloneExam(){
        Stage case8=new Stage();
        BorderPane borderPane = new BorderPane();
        Scene newScene=new Scene(borderPane,500,300);
        case8.setScene(newScene);
        Label titlelbl=new Label("Chose how exam do you want to clone:");
        borderPane.setTop(titlelbl);
        RadioButton autoExamrb=new RadioButton("Automatic Exam");
        RadioButton menualExamrb=new RadioButton("Menual Exam");
        ToggleGroup tglOpt = new ToggleGroup();
        autoExamrb.setToggleGroup(tglOpt);
        menualExamrb.setToggleGroup(tglOpt);
        borderPane.setRight(autoExamrb);
        borderPane.setLeft(menualExamrb);
        Button selectbt=new Button("select");
        borderPane.setCenter(selectbt);
        Button returnToMenu=new Button("Return To Menu");
        borderPane.setBottom(returnToMenu);
        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                case8.hide();
                showMainMenu();
            }
        });

            selectbt.setOnAction(new EventHandler<ActionEvent>() {
                                     @Override
                                     public void handle(ActionEvent actionEvent) {
                                         if (menualExamrb.isSelected()){
                                             int chose=1;
                                             for(viewListener l:allViewListeners){
                                                 try {
                                                     l.cloneLastExam(chose);
                                                 } catch (FileNotFoundException e) {
                                                     e.printStackTrace();
                                                 } catch (CloneNotSupportedException e) {
                                                     e.printStackTrace();
                                                 }
                                             }
                                         }
                                         if(autoExamrb.isSelected()){
                                             int chose=2;
                                             for(viewListener l:allViewListeners){
                                                 try {
                                                     l.cloneLastExam(chose);
                                                 } catch (FileNotFoundException e) {
                                                     e.printStackTrace();
                                                 } catch (CloneNotSupportedException e) {
                                                     e.printStackTrace();
                                                 }
                                             }
                                         }

                                     }
            });

        case8.show();

    }
}
