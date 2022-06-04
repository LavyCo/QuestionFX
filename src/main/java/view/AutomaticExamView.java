package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import listeners.viewListener;

import java.util.Vector;

public class AutomaticExamView {
    private Vector<viewListener> allViewListeners;
    private MenuView menuView;

    public AutomaticExamView(Vector<viewListener> allViewListeners,MenuView menuView){
        this.menuView=menuView;
        this.allViewListeners=allViewListeners;
    }
    public void auotomaticExam(){
        Stage case7=new Stage();
        case7.setTitle("Automatic Exam");
        GridPane gpRoot = new GridPane();
        gpRoot.setPadding(new Insets((10)));
        gpRoot.setHgap(10);
        gpRoot.setHgap(10);
        Label titlelbl=new Label("select how much questions do yoy want in the test :");
        ComboBox<Integer> cmdCountOfQuestions = new ComboBox<>();
        for(viewListener l:allViewListeners){
             l.getNumOf
        }
        for (int i=0;)


        Scene newScene=new Scene(gpRoot,500,300);
        case7.setScene(newScene);
        gpRoot.add(titlelbl,0,0);
        case7.show();

    }
}
