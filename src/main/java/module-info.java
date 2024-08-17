module ec.edu.espol.proyectoed.p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires java.base;

    opens ec.edu.espol.proyectoed.p.controller to javafx.fxml;
    
    exports ec.edu.espol.proyectoed.p.controller;    
    exports ec.edu.espol.proyectoed.p;
}
