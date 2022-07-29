module github.josedoce {
    requires javafx.controls;
    requires javafx.fxml;

    opens github.josedoce to javafx.fxml;
    exports github.josedoce;
}
