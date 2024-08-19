/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed.p.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 * FXML Controller class
 *
 * @author creditos gonzalez
 */
public class PedirArchivoController implements Initializable {


    private Button btnSubir;
    @FXML
    private Text lblSubido;
    
    private File selectedPreguntasFile;
    private File selectedRespuestasFile;
    @FXML
    private Button btnSubirPreguntas;
    @FXML
    private Button btnSubirRespuestas;
    @FXML
    private Text lblSubidoPreguntas;
    @FXML
    private Text lblSubirRespuestas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void uploadFile(File file){
        // Ruta de la carpeta existente donde se guardará el archivo
        String destinationFolder = "src/main/resources/files/";

        // Verificar si la carpeta existe
        Path destinationPath = Paths.get(destinationFolder);
        if (!Files.exists(destinationPath)) {
            lblSubido.setText("La carpeta de destino no existe.");
            return;
        }

        // Ruta completa donde se guardará el archivo
        Path destinationFilePath = destinationPath.resolve(file.getName());

        // Copiar el archivo al destino
        try {
            Files.copy(file.toPath(), destinationFilePath);
            lblSubido.setText("Archivo guardado en: " + destinationFilePath.toString());
        } catch (IOException e) {
            e.printStackTrace();
            lblSubido.setText("Error al guardar el archivo.");
        }
    }

    @FXML
    private void subirArchivoPreguntas(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo de preguntas");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Archivos de texto", "*.txt"),
            new ExtensionFilter("Archivos CSV", "*.csv"),
            new ExtensionFilter("Todos los archivos", "*.*")
        );

        // Obtén la ventana actual para mostrar el FileChooser
        Window window = btnSubirPreguntas.getScene().getWindow();

        selectedPreguntasFile = fileChooser.showOpenDialog(window);
        if (selectedPreguntasFile != null) {
            // Maneja el archivo seleccionado
            lblSubidoPreguntas.setText("Archivo seleccionado: " + selectedPreguntasFile.getName());
            System.out.println("Archivo de preguntas seleccionado: " + selectedPreguntasFile.getAbsolutePath());
            uploadFile(selectedPreguntasFile);
            // Aquí puedes agregar la lógica para manejar el archivo, como leerlo o cargarlo.
        }else{
            lblSubidoPreguntas.setText("Ningun srchivo seleccionado");
        }
    }

    @FXML
    private void subirArchivoRespuestas(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo de respuestas");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Archivos de texto", "*.txt"),
            new ExtensionFilter("Archivos CSV", "*.csv"),
            new ExtensionFilter("Todos los archivos", "*.*")
        );

        // Obtén la ventana actual para mostrar el FileChooser
        Window window = btnSubirRespuestas.getScene().getWindow();

        selectedRespuestasFile = fileChooser.showOpenDialog(window);
        if (selectedRespuestasFile != null) {
            // Maneja el archivo seleccionado
            lblSubirRespuestas.setText("Archivo seleccionado: " + selectedRespuestasFile.getName());
            System.out.println("Archivo de respuestas seleccionado: " + selectedRespuestasFile.getAbsolutePath());   
            uploadFile(selectedRespuestasFile);
            // Aquí puedes agregar la lógica para manejar el archivo, como leerlo o cargarlo.
        } else{
            lblSubirRespuestas.setText("Ningun srchivo seleccionado");
        }
    }
    
    public String getSelectedPreguntasFile(){
        String preguntasArchivo = selectedPreguntasFile.getName();
        return preguntasArchivo;
    }
    public String getSelectedRespuestasFile(){
        String respuestasArchivo = selectedRespuestasFile.getName();
        return respuestasArchivo;
    }

}
