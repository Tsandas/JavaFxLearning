module org.example.javafxlearning {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.json;

    opens org.example.javafxlearning to javafx.fxml;
    exports org.example.javafxlearning;

}