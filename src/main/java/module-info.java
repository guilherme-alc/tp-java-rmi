module com.tpemailrmi.tpjavarmi {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.tpemailrmi.tpjavarmi to javafx.fxml;
    opens com.tpemailrmi.tpjavarmi.comum to com.google.gson;
    exports com.tpemailrmi.tpjavarmi;
}