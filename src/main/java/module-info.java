module com.example.big_homework {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    opens com.example.big_homework to javafx.fxml;
    exports com.example.big_homework;
}