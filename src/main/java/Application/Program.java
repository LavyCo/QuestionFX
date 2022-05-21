package Application;

import controller.Controller;
import id206214280_id316650399.QuestionReservoir;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.AbstractQuestionView;
import view.QuestionFxView;

import java.io.IOException;
import java.util.ArrayList;

public class Program extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AbstractQuestionView theView1 = new QuestionFxView(stage);
//		QuestionReservoir qr1=new QuestionReservoir();
//		// open question #1
//		qr1.addOpenQuestion("A Who was Yitzhak Rabin?", "Israeli prime minister");
//		// open question #2
//		qr1.addOpenQuestion("B Who solved the Engima machine during WWII?", "Alan Turing");
//		// open question #3
//		qr1.addOpenQuestion("C What is the difference between Choclate cake and a Salad?",
//				"Salad wont give you Diabetes");
//		// american question #1
//		String aq1 = ("D Which one of these is not dessert?");
//		ArrayList <String> aa1 = new ArrayList<>();
//        aa1.add(0,"Muffin");
//        aa1.add(1,"Choclate cake");
//        aa1.add(2,"Ice cream");
//        aa1.add(3,"Brownies");
//        aa1.add(4,"Salad");
//        aa1.add(5,"Burger");
//        aa1.add(6,"Shawrma");
//        aa1.add(7,"Pizza");
//
//
//		ArrayList <Boolean> tof1 = new ArrayList<>();
//        tof1.add(0,false);
//        tof1.add(1,false);
//        tof1.add(2,false);
//        tof1.add(3,false);
//        tof1.add(4,true);
//        tof1.add(5,true);
//        tof1.add(6,true);
//        tof1.add(7,true);
//		qr1.addAmericanQuestion(aq1, aa1, tof1);
//		// american question #2
//		String aq2 = "E Which one of these next Programming languages is Low-Level programming language?";
//		 ArrayList <String> aa2 = new ArrayList<>();
//        aa2.add(0,"Java");
//        aa2.add(1,"C++");
//        aa2.add(2,"Assembly");
//        aa2.add(3,"C");
//        aa2.add(4,"C#");
//        aa2.add(5,"Python");
//        aa2.add(6,"JavaScript");
//        aa2.add(7,"Swift");
//        aa2.add(8,"Machine Code");
//
//		 ArrayList <Boolean> tof2 = new ArrayList<>();
//        tof2.add(0,false);
//        tof2.add(1,false);
//        tof2.add(2,true);
//        tof2.add(3,false);
//        tof2.add(4,false);
//        tof2.add(5,false);
//        tof2.add(6,false);
//        tof2.add(7,false);
//        tof2.add(8,true);
//		qr1.addAmericanQuestion(aq2, aa2, tof2);
//		System.out.println(qr1.getQuestionArray().size());
//		qr1.saveBin();
		QuestionReservoir qr1 = new QuestionReservoir();
		qr1.readBin();

        Controller controller=new Controller(qr1,theView1);
    }

    public static void main(String[] args) {
        launch();
    }
}