/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed.p.controller;

import ec.edu.espol.proyectoed.p.App;
import ec.edu.espol.proyectoed.p.util.FileReaderUtil;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jmuni
 */
public class CargarArchivosController implements Initializable {

    @FXML
    private Button btnCargarPreguntas;

    @FXML
    private Button btnCargarRespuestas;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Label lblResultado;

    private static final String CARPETA_RESOURCES = "src/main/resources/files/";

    private File archivoPreguntas;
    private File archivoRespuestas;
    private String nombrePreguntas;
    private String nombreRespuestas;
    @FXML
    private ImageView idImagen;
    @FXML
    private Button btnVolver;

    @FXML
    private void handleCargarPreguntas() {
        archivoPreguntas = cargarArchivo("Seleccionar Archivo de Preguntas");
        if (archivoPreguntas != null) {
            nombrePreguntas = generarNombreArchivo("preguntas");
            lblResultado.setText("Archivo de Preguntas: " + archivoPreguntas.getName() + " será guardado como: " + nombrePreguntas);
        }
    }

    @FXML
    private void handleCargarRespuestas() {
        archivoRespuestas = cargarArchivo("Seleccionar Archivo de Respuestas");
        if (archivoRespuestas != null && verificarFormatoRespuestas(archivoRespuestas)) {
            nombreRespuestas = generarNombreArchivo("respuestas");
            lblResultado.setText(lblResultado.getText() + "\nArchivo de Respuestas: " + archivoRespuestas.getName() + " será guardado como: " + nombreRespuestas);
        }
    }

    @FXML
    private void handleConfirmar() {
        if (archivoPreguntas != null && archivoRespuestas != null) {
            String pathPre = guardarArchivoEnResources(archivoPreguntas, nombrePreguntas);
            String pathRes = guardarArchivoEnResources(archivoRespuestas, nombreRespuestas);
            lblResultado.setText("Archivos guardados correctamente:\n" +
                                 "Preguntas: " + nombrePreguntas + "\n" +
                                 "Respuestas: " + nombreRespuestas);
            FileReaderUtil.escogerArchivo(pathPre, pathRes);
            try {
                App.setRoot("preguntas");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            lblResultado.setText("Debe cargar ambos archivos antes de confirmar.");
        }
        
        
    }

    private File cargarArchivo(String titulo) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(titulo);
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Archivos de Texto", "*.txt")
        );
        return fileChooser.showOpenDialog(new Stage());
    }

    private boolean verificarFormatoRespuestas(File archivo) {
        try {
            List<String> lineas = Files.readAllLines(archivo.toPath());
            for (String linea : lineas) {
                String[] partes = linea.split(" ");
                if (partes.length != 21) { // El nombre del animal + 20 respuestas
                    return false;
                }
                for (int i = 1; i < partes.length; i++) {
                    String respuesta = partes[i];
                    if (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no")) {
                        return false;
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String generarNombreArchivo(String tipoArchivo) {
        int maxNumero = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of(CARPETA_RESOURCES), tipoArchivo + "*.txt")) {
            Pattern pattern = Pattern.compile(tipoArchivo + "(\\d+)\\.txt");
            for (Path path : stream) {
                Matcher matcher = pattern.matcher(path.getFileName().toString());
                if (matcher.matches()) {
                    int numero = Integer.parseInt(matcher.group(1));
                    if (numero > maxNumero) {
                        maxNumero = numero;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tipoArchivo + (maxNumero + 1) + ".txt";
    }

    private String guardarArchivoEnResources(File archivo, String nombreDestino) {
        Path destino = null;
        try {
            destino = Path.of(CARPETA_RESOURCES + nombreDestino);
            Files.copy(archivo.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destino.toString();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idImagen.setImage(new Image("/imagenes/cargarArchivo.png"));
    }

    @FXML
    private void clickEnVolver(ActionEvent event) {
        try {
            App.setRoot("opcion");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
