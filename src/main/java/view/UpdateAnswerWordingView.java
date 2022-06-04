package view;

import javafx.stage.Stage;
import listeners.viewListener;

import java.util.Vector;

public class UpdateAnswerWordingView {
    private Vector<viewListener> allViewListeners;
    private MenuView menuView;

    public UpdateAnswerWordingView(Vector<viewListener> allViewListeners,MenuView menuView){
        this.menuView=menuView;
        this.allViewListeners=allViewListeners;
    }
    public void  updateAnswerWording(){
        Stage case4=new Stage();

    }
}
