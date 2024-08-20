/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class OpcionController implements Initializable {

    @FXML
    private ImageView ivFondo;
    @FXML
    private Button btnCarga;
    @FXML
    private Button btnReutiliza;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ivFondo.setImage(new Image("/imagenes/opcion.png"));
    }    


    @FXML
    private void mouseEnBoton(MouseDragEvent event) {
    }

    @FXML
    private void clickEnCarga(ActionEvent event) {
        try {
            App.setRoot("cargarArchivos");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clickEnReutiliza(ActionEvent event) {
        try {
            App.setRoot("reutilizar");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
