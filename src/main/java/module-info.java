module com.application.loupmouton {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.application.loupmouton to javafx.fxml;
    exports com.application.loupmouton;
}