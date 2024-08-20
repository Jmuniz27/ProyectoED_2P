/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed.p.controller;

import ec.edu.espol.proyectoed.p.App;
import ec.edu.espol.proyectoed.p.modelo.AnimalInfo;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    
    public static List<AnimalInfo> animales; 
    private int startIndex = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ivFondo.setImage(new Image("/imagenes/respuesta.png"));
        if (animales.size() > 3) {
            // Solo mostramos las flechas si hay más de tres animales
            before.setVisible(true);
            next.setVisible(true);
        } else {
            // Ocultamos las flechas si hay tres o menos animales
            before.setVisible(false);
            next.setVisible(false);
        }
        updateArrowsVisibility();
        updateDisplayedAnimals();
    }
    
    //actualizar los vehiculos
    private VBox plantillaAnimal(AnimalInfo ani) {
        try {
            // Cargar la plantilla de celda desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/plantillaAnimal.fxml"));
            VBox animal = loader.load();

            // Actualizar el contenido de la celda
            Text plantTxtAni= (Text) animal.lookup("#txtAni1");
            ImageView plantIvAni1 = (ImageView) animal.lookup("#ivAni1");
            
            plantTxtAni.setText(ani.getAnimalName());
            plantIvAni1.setImage(new Image(ani.getAnimalImg()));
            return animal;
        } catch (IOException e) {
            System.out.println("error1");
            e.printStackTrace();
            return null;
        }
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

    private void updateDisplayedAnimals() {
        hbAnimales.getChildren().clear();
        int animalsToShow = Math.min(3, animales.size() - startIndex);
        for (int i = 0; i < animalsToShow; i++) {
            if (startIndex + i < animales.size()) {
                AnimalInfo animal = animales.get(startIndex + i);
                VBox animalBox = plantillaAnimal(animal);
                animalBox.setCursor(Cursor.HAND);
                animalBox.setOnMouseClicked((MouseEvent event) -> {
                    // Mostrar la ventana de un solo animal
                    AnimalUnicoListaController.aniInfo = animal;
                    try {
                        App.setRoot("animalUnicoLista");
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                hbAnimales.getChildren().add(animalBox);
            }
        }
        updateButtonsVisibility();
    }

    private void updateButtonsVisibility() {
        before.setDisable(startIndex == 0);
        next.setDisable(startIndex + 3 >= animales.size());
    }

    @FXML
    private void clickBefore(MouseEvent event) {
        // Mover el índice hacia atrás
        if (startIndex > 0) {
            startIndex = startIndex - 1;
            updateDisplayedAnimals();
            updateArrowsVisibility();
        }
    }

    @FXML
    private void clickNext(MouseEvent event) {
        if (startIndex + 3 < animales.size()) {
            startIndex = startIndex + 1;
            updateDisplayedAnimals();
            updateArrowsVisibility();
        }
    }
    
     private void updateArrowsVisibility() {
        // Mostrar/ocultar flecha izquierda si estamos en el primer elemento
        before.setVisible(startIndex > 0);

        // Mostrar/ocultar flecha derecha si estamos en el último conjunto de elementos
        next.setVisible(startIndex + 3 < animales.size());
    }

    
}
