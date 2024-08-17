/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed.p.controller;

import ec.edu.espol.proyectoed.p.modelo.AnimalInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author isabella
 */
public class ListaAnimalesController implements Initializable {

    @FXML
    private ImageView ivFondo;
    @FXML
    private Button btnVolver;
    @FXML
    private VBox before;
    @FXML
    private HBox hbAnimales;
    @FXML
    private VBox vbAni1;
    @FXML
    private Text txtAni1;
    @FXML
    private ImageView ivAni1;
    @FXML
    private VBox vbAni2;
    @FXML
    private Text txtAni2;
    @FXML
    private ImageView ivAni2;
    @FXML
    private VBox vbAni3;
    @FXML
    private Text txtAni3;
    @FXML
    private ImageView ivAni3;
    @FXML
    private VBox next;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    //actualizar los vehiculos
    private VBox plantillaAnimal(AnimalInfo ani) {
        try {
            // Cargar la plantilla de celda desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/inicio.fxml"));
            VBox animal = loader.load();

            // Actualizar el contenido de la celda
            Text plantTxtAni= (Text) animal.lookup("#txtAni1");
            ImageView plantIvAni1 = (ImageView) animal.lookup("#ivAni1");
            
            plantTxtAni.setText(ani.getAnimalName());
            plantIvAni1.setImage(new Image(ani.getAnimalImg()));
            return animal;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void volverJugar(ActionEvent event) {
    }

    @FXML
    private void mouseEnBoton(MouseDragEvent event) {
    }
    
}
