module edu.martin.javafx.login.mfl_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens edu.martin.javafx.login.mfl_project to javafx.fxml;
    exports edu.martin.javafx.login.mfl_project;

    opens edu.martin.javafx.login.mfl_project.controller to javafx.fxml;
    exports edu.martin.javafx.login.mfl_project.controller;

    opens edu.martin.javafx.login.mfl_project.model;
    exports edu.martin.javafx.login.mfl_project.model;

    opens edu.martin.javafx.login.mfl_project.db;
    exports edu.martin.javafx.login.mfl_project.db;


    opens edu.martin.javafx.login.mfl_project.dao;
    exports edu.martin.javafx.login.mfl_project.dao;
}
