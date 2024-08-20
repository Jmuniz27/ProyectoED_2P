/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed.p.controller;

import ec.edu.espol.proyectoed.p.App;
import ec.edu.espol.proyectoed.p.modelo.AnimalInfo;
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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class AnimalUnicoController implements Initializable {

    @FXML
    private Text txtNombreAnimal;
    @FXML
    private Text txtDescripcion;
    @FXML
    private ImageView ivFondo;
    @FXML
    private ImageView ivFotoAnimal;
    public static AnimalInfo aniInfo;
    @FXML
    private HBox hboxBtns;
    private Button btnVolver11;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnVolver;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ivFondo.setImage(new Image("/imagenes/respuesta.png"));
        ivFotoAnimal.setFitWidth(170); 
        ivFotoAnimal.setFitHeight(170);
        ivFotoAnimal.setSmooth(true);
        ivFotoAnimal.setCache(true); 
        ivFotoAnimal.setPreserveRatio(false);
        ivFotoAnimal.setImage(new Image(aniInfo.getAnimalImg()));
        txtNombreAnimal.setText(aniInfo.getAnimalName());
        txtDescripcion.setText(aniInfo.getAnimalDecs());
    }
    
    public Button getBtnRegresar() {
        return btnRegresar;
    }

    public void setBtnRegresar(Button btnRegresar) {
        this.btnRegresar = btnRegresar;
    }

    @FXML
    private void volverJugar(ActionEvent event) {
        try {
            App.setRoot("inicio");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void mouseEnBoton(MouseDragEvent event) {
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
    }
    
}
