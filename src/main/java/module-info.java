module edu.martin.javafx.login.mfl_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens edu.martin.javafx.login.mfl_project to javafx.fxml;
    exports edu.martin.javafx.login.mfl_project;
}