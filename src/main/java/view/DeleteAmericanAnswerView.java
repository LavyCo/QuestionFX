package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import listeners.viewListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Vector;


public class DeleteAmericanAnswerView {
    private MenuView menuView;
    private Vector<viewListener> allViewListeners = new Vector<>();
    private Stage deleteAnswerStage = new Stage();
    private ComboBox<Integer> idComboBox = new ComboBox<>();
    private Label americanQuestionsString;
    private Scene deleteAnswerScene;
    private Button returnToMenu = new Button("Return to menu");
    private Button selectButton = new Button("Select");


    public DeleteAmericanAnswerView(MenuView menuView, Vector<viewListener> allViewListeners, Vector<Integer> idComboBox, String americanQuestionsString) {
        this.menuView = menuView;
        this.allViewListeners = allViewListeners;
        this.idComboBox.getItems().addAll(idComboBox);
        this.americanQuestionsString = new Label(americanQuestionsString);
    }

    public DeleteAmericanAnswerView(Vector<viewListener> allViewListeners, MenuView menuView) {
        this.menuView = menuView;
        this.allViewListeners = allViewListeners;
    }

    public void showAmericanQuestionsData() {
        ScrollPane americanQuestionScrollPane = new ScrollPane();
        HBox americanQuestionsHbox = new HBox(americanQuestionsString);
        americanQuestionScrollPane.setContent(americanQuestionsHbox);
        VBox americanQuestionsButtonsVbox = new VBox(idComboBox, selectButton, returnToMenu);
        americanQuestionsButtonsVbox.setAlignment(Pos.CENTER);
        americanQuestionsButtonsVbox.setSpacing(20);
        BorderPane americanQuestionBorderPane = new BorderPane();
        americanQuestionBorderPane.setCenter(americanQuestionScrollPane);
        americanQuestionBorderPane.setRight(americanQuestionsButtonsVbox);
        americanQuestionBorderPane.setPadding(new Insets(10));
        deleteAnswerScene = new Scene(americanQuestionBorderPane);
        deleteAnswerStage.setScene(deleteAnswerScene);
        deleteAnswerStage.show();

        selectButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                if (idComboBox.getValue()!=null) {
                    for (viewListener l : allViewListeners) {
                        l.getAmericanQuestionToDelete(idComboBox.getValue().intValue());
                    }
                    deleteAnswerStage.hide();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please select an ID");
                }
            }
        });

        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                menuView.showMainMenu(new Stage());
                deleteAnswerStage.hide();
            }
        });
    }


    public void showChosenAmericanQuestionDelete(String americanQuestionString, Vector<String> americanAnswerString1, int id) {
        BorderPane deleteAnswerBorderPane = new BorderPane();
        VBox americanQuestionToDeleteVbox = new VBox();
        Label americanQuestionLabel = new Label(americanQuestionString);
        Vector<RadioButton> americanAnswersRb = new Vector<>();
        ToggleGroup americanAnswersTg = new ToggleGroup();
        Button chooseOtherButton = new Button("Choose other Question");
        VBox selectAnswerToDeleteVBox = new VBox(selectButton, chooseOtherButton, returnToMenu);
        selectAnswerToDeleteVBox.setSpacing(10);
        selectAnswerToDeleteVBox.setAlignment(Pos.CENTER);
        System.out.println(americanAnswerString1.size());
        americanQuestionToDeleteVbox.getChildren().add(americanQuestionLabel);
        deleteAnswerBorderPane.setPadding(new Insets(10));

        for (int i = 0; i < americanAnswerString1.size(); i++) {
            RadioButton radioButton = new RadioButton(americanAnswerString1.get(i));
            americanAnswersRb.add(radioButton);
            americanAnswersRb.get(i).setToggleGroup(americanAnswersTg);
            americanQuestionToDeleteVbox.getChildren().add(americanAnswersRb.get(i));
        }
        deleteAnswerBorderPane.setCenter(americanQuestionToDeleteVbox);
        deleteAnswerBorderPane.setRight(selectAnswerToDeleteVBox);
        deleteAnswerBorderPane.setPadding(new Insets(10));
        deleteAnswerScene = new Scene(deleteAnswerBorderPane, 300, 200);
        deleteAnswerStage.setScene(deleteAnswerScene);
        deleteAnswerStage.show();

        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (americanAnswersTg.getSelectedToggle() != null) {
                    int counter = 0;
                    int numberOfAnswerToDelete = 0;

                    for (RadioButton r : americanAnswersRb) {
                        if (r == americanAnswersTg.getSelectedToggle()) {
                            numberOfAnswerToDelete = counter;
                            r.setVisible(false);
                        }
                        counter++;
                    }
                    for (viewListener l : allViewListeners) {
                        l.deleteAmericanAnswerFromModel(id, numberOfAnswerToDelete);
                    }
                    americanAnswersTg.getSelectedToggle().setSelected(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Please choose an answer");
                }
            }
        });
        returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                deleteAnswerStage.hide();
                menuView.showMainMenu(new Stage());
            }
        });
        chooseOtherButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (viewListener l : allViewListeners) {
                    l.deleteAmericanAnswer();
                }
                deleteAnswerStage.hide();
            }
        });


    }
}
