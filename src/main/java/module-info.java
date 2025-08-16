module com.example.diarioculturaljavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires java.desktop;
    requires com.google.gson;

    opens com.example.diarioculturaljavafx to javafx.fxml;
    opens com.example.diarioculturaljavafx.controller to javafx.fxml;
    // <- ADICIONADO

    exports com.example.diarioculturaljavafx;
    exports com.example.diarioculturaljavafx.controller;
    exports com.example.diarioculturaljavafx.model;
    opens com.example.diarioculturaljavafx.model to com.google.gson, javafx.fxml;
}
