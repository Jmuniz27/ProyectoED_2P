package ec.edu.espol.proyectoed.p.controller;

import ec.edu.espol.proyectoed.p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;

public class MenuController implements Initializable{

    @FXML
    private ImageView ivFondo;
    @FXML
    private Button btnArchivo;
    @FXML
    private Button btnJugar;

    private void clickEnEmpieza(ActionEvent event) {
        try {
            App.setRoot("preguntas");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void mouseEnBoton(MouseDragEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ivFondo.setImage(new Image("/imagenes/inicio.png"));
    }

    @FXML
    private void clickSubArchivo(ActionEvent event) {
        try {
            App.setRoot("cargarArchivos");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clickJugar(ActionEvent event) {
        try {
            App.setRoot("preguntas");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
