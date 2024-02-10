module it.ispw.unibook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.opencsv;
    requires org.apache.httpcomponents.httpcore;
    requires java.json;
    requires org.apache.httpcomponents.httpclient;
    requires org.jetbrains.annotations;

    opens it.ispw.unibook.controller.graphics.gui to javafx.fxml;
    opens it.ispw.unibook.controller.graphics.gui.student to javafx.fxml;
    opens it.ispw.unibook.controller.graphics.gui.professor to javafx.fxml;
    exports it.ispw.unibook;
}