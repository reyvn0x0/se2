module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires jdk.internal.le;
    requires java.logging;
    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.Factory;
    opens com.example.demo.Factory to javafx.fxml;
    exports com.example.demo.Controller;
    opens com.example.demo.Controller to javafx.fxml;
    exports com.example.demo.Managers;
    opens com.example.demo.Managers to javafx.fxml;
}