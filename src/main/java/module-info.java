module ec.edu.espol.proyectoed.p {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.proyectoed.p to javafx.fxml;
    exports ec.edu.espol.proyectoed.p;
}
