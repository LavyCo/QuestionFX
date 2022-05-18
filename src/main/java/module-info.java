module com.example.questionfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens Application to javafx.fxml;
    exports Application;
}