module com.example.crudjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens controller to javafx.fxml;
    exports controller;

    opens model to javafx.base;
    exports model;
}