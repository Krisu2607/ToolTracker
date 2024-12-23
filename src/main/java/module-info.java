module com.example.tooltracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires org.json;

    opens com.example.tooltracker.view to javafx.fxml;
    opens com.example.tooltracker.controller to javafx.fxml;
    opens com.example.tooltracker.model to javafx.base;
    exports com.example.tooltracker to javafx.graphics;
    exports com.example.tooltracker.view;
    opens com.example.tooltracker.model.tools to javafx.base;
    opens com.example.tooltracker.controller.newtoolcontrollers to javafx.fxml;
}



