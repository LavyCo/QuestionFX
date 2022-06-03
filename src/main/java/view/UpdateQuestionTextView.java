package view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import listeners.viewListener;

import java.util.ArrayList;
import java.util.Vector;
import javafx.event.EventHandler;

import javax.swing.*;

public class UpdateQuestionTextView {
    private Vector<viewListener> allViewListeners;
    private MenuView menuView;

    public UpdateQuestionTextView(Vector<viewListener> allViewListeners, MenuView menuView){
        this.menuView=menuView;
        this.allViewListeners=allViewListeners;
    }

//    public void updateQuestionText(){
//        Stage case3=new Stage();
//        case3.setTitle("Change Question wording");
//        GridPane gpRootCase3 = new GridPane();
//        gpRootCase3.setPadding(new Insets((10)));
//        gpRootCase3.setHgap(10);
//        gpRootCase3.setHgap(10);
//        Label chooseFromListlbl = new Label("Choose from the list the Question do you want to Change: ");
//        for (viewListener l : allViewListeners) {
//            l.showStringInUI();
//            gpRootCase3.add(allQustionslbl, 0, 1);
//            ArrayList<Integer> allId = new ArrayList<>();
//            allId = l.GetAllIDfromModel();
//            ComboBox<Integer> cmdId = new ComboBox<>();
//            for (int i = 0; i < allId.size(); i++) {
//                cmdId.getItems().add(allId.get(i));
//            }
//            TextField newWordingtf = new TextField();
//            newWordingtf.setVisible(false);
//            Label newWordinglbl = new Label("Type new Wording for the Question:");
//            newWordinglbl.setVisible(false);
//            Button changeWordingbt = new Button("Change Wording");
//            changeWordingbt.setVisible(false);
//            changeWordingbt.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    String msg = l.ChangeWording(newWordingtf.getText(), cmdId.getValue());
//                    JOptionPane.showMessageDialog(null, msg);
//                }
//            });
//            gpRootCase3.add(cmdId, 1, 1);
//            cmdId.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    if (cmdId.getValue() != null) {
//                        newWordingtf.setVisible(true);
//                        newWordinglbl.setVisible(true);
//                        changeWordingbt.setVisible(true);
//                    }
//                }
//            });
//            gpRootCase3.add(newWordinglbl, 0, 2);
//            gpRootCase3.add(newWordingtf, 1, 2);
//            gpRootCase3.add(changeWordingbt, 1, 3);
//        }
//
//
//        buttonReturn.setText("Return To Menu");
//        case3.setScene(new Scene(gpRootCase3, 850, 350));
//        gpRootCase3.add(chooseFromListlbl, 0, 0);
//
//        gpRootCase3.add(buttonReturn, 1, 11);
//        case3.show();
//
//    }





}
