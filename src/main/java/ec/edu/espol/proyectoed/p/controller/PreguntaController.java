/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed.p.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author creditos gonzalez
 */
public class PreguntaController implements Initializable {


    @FXML
    private Text lblPregunta;
    @FXML
    private HBox botonHBox;
    @FXML
    private Button siBtn;
    @FXML
    private Button noBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void guardarRespuestaSi(ActionEvent event) {
    }

    @FXML
    private void guardarRespuestaNo(ActionEvent event) {
    }

}
