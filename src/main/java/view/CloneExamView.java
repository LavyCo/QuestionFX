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
        Scene newScene=new Scene(borderPane,400,200);
        case8.setScene(newScene);
        Label titleLbl=new Label("Choose which exam do you want to clone:");
        borderPane.setTop(titleLbl);
        RadioButton autoExamRb=new RadioButton("Automatic Exam");
        RadioButton manualExamRb=new RadioButton("Manual Exam");
        ToggleGroup tglOpt = new ToggleGroup();
        autoExamRb.setToggleGroup(tglOpt);
        manualExamRb.setToggleGroup(tglOpt);
        borderPane.setRight(autoExamRb);
        borderPane.setLeft(manualExamRb);
        Button selectBt=new Button("Select");
        borderPane.setCenter(selectBt);
        Button returnToMenu=new Button("Return To Menu");
        borderPane.setBottom(returnToMenu);
        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                case8.hide();
                showMainMenu();
            }
        });

            selectBt.setOnAction(new EventHandler<ActionEvent>() {
                                     @Override
                                     public void handle(ActionEvent actionEvent) {
                                         if (manualExamRb.isSelected()){
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
                                         if(autoExamRb.isSelected()){
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
