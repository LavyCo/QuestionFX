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
//		String[] aa1 = new String[] { "Muffin", "Choclate cake", "Ice cream", "Brownies", "Salad", "Burger", "Shawrma",
//				"Pizza" };
//		boolean[] tof1 = new boolean[] { false, false, false, false, true, true, true, true };
//		qr1.addAmericanQuestion(aq1, aa1, tof1);
//		// american question #2
//		String aq2 = "E Which one of these next Programming languages is Low-Level programming language?";
//		String[] aa2 = new String[] { "Java", "C++", "Assembly", "C", "C#", "Python", "JavaScript", "Swift",
//				"Machine Code" };
//		boolean[] tof2 = new boolean[] { false, false, true, false, false, false, false, false, true };
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