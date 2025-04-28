module com.example.javaminiproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.dotenv;
    requires org.json;


    opens com.example.javaminiproject to javafx.fxml;
    exports com.example.javaminiproject;
}