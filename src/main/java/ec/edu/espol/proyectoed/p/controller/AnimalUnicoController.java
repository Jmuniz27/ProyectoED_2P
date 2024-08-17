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
    private Button btnVolver;
    @FXML
    private ImageView ivFondo;
    @FXML
    private ImageView ivFotoAnimal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnimalInfo aniInfo = new AnimalInfo("perro");
        ivFondo.setImage(new Image("/imagenes/respuesta.png"));
        ivFotoAnimal.setFitWidth(175); 
        ivFotoAnimal.setFitHeight(198);
        ivFotoAnimal.setSmooth(true);  // Suaviza la imagen al redimensionarla
        ivFotoAnimal.setCache(true); 
        ivFotoAnimal.setPreserveRatio(false);
        ivFotoAnimal.setImage(new Image(aniInfo.getAnimalImg()));
        txtNombreAnimal.setText(aniInfo.getAnimalName());
        txtDescripcion.setText(aniInfo.getAnimalDecs());
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
    
}
