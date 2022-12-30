module com.twenties.twenties {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.twenties.twenties to javafx.fxml;
    exports com.twenties.twenties;
}