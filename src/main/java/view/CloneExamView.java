package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listeners.viewListener;

import java.io.FileNotFoundException;
import java.util.Vector;

public class CloneExamView {
    private Vector<viewListener> allViewListeners;
    private MenuView menuView;

    public CloneExamView(Vector<viewListener> allViewListeners, MenuView menuView) {
        this.menuView = menuView;
        this.allViewListeners = allViewListeners;
    }

    public void showMainMenu() {
        menuView.showMainMenu(new Stage());
    }


    public void cloneExam() {
        Stage case8 = new Stage();
        BorderPane borderPane = new BorderPane();
        Scene newScene = new Scene(borderPane, 400, 60);
        case8.setScene(newScene);
        case8.setResizable(false);
        Label titleLbl = new Label("Choose which exam do you want to clone:");
        borderPane.setTop(titleLbl);
        Button autoExamBtn = new Button("Automatic Exam");
        Button manualExamBtn = new Button("Manual Exam");
        borderPane.setRight(autoExamBtn);
        borderPane.setLeft(manualExamBtn);
        Button selectBt = new Button("Select");
        Button returnToMenu = new Button("Return To Menu");
        HBox manualAutoHbox=new HBox(autoExamBtn,manualExamBtn,returnToMenu);
        titleLbl.setAlignment(Pos.CENTER);
        manualAutoHbox.setAlignment(Pos.CENTER);
        manualAutoHbox.setSpacing(10);
        manualAutoHbox.setPadding(new Insets(10));
        borderPane.setBottom(manualAutoHbox);
        borderPane.setTop(titleLbl);
        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                case8.hide();
                showMainMenu();
            }
        });

        manualExamBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (viewListener l : allViewListeners) {
                    int chose = 1;
                    try {
                        l.cloneLastExam(chose);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        autoExamBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int chose = 2;
                for (viewListener l : allViewListeners) {
                    try {
                        l.cloneLastExam(chose);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });



        case8.show();

    }
}
