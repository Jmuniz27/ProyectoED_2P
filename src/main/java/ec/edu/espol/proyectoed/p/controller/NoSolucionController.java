/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed.p.controller;

import ec.edu.espol.proyectoed.p.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author zahid
 */
public class NoSolucionController implements Initializable {

    @FXML
    private Button btnRegresarInicio;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresarInicio(ActionEvent event) {
        try{
            App.setRoot("inicio");
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
}
