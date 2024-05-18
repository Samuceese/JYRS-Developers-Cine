module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires kotlin.result.jvm;
    requires logging.jvm;
    requires kotlinx.serialization.core;


    requires sqlite.driver;
    requires runtime.jvm;
    requires java.sql;
    requires kotlinx.serialization.json;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo;


}