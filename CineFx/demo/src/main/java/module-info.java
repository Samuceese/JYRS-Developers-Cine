module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires kotlin.result.jvm;
    requires logging.jvm;
    requires kotlinx.serialization.core;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
}