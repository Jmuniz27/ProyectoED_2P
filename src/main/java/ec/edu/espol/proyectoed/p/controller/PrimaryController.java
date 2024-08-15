package ec.edu.espol.proyectoed.p.controller;

import java.io.IOException;

import ec.edu.espol.proyectoed.p.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
