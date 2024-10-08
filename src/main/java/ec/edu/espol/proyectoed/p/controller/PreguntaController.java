/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed.p.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import ec.edu.espol.proyectoed.p.util.FileReaderUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author creditos gonzalez
 */
public class PreguntaController implements Initializable {
    @FXML
    private Label lblPregunta;

    private String respuesta;
    @FXML
    public static HBox botonHBox;
    @FXML
    private Button siBtn;
    @FXML
    private Button noBtn;
    @FXML
    private ImageView ivFondo;

    public void setPregunta(String pregunta) {
        lblPregunta.setText(pregunta);
    }

    public String getRespuesta() {
        return respuesta;
    }

    @FXML
    private void guardarRespuestaSi(ActionEvent event) {
        respuesta = "Si";
        closeWindow();
    }

    @FXML
    private void guardarRespuestaNo(ActionEvent event) {
        respuesta = "No";
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) lblPregunta.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ivFondo.setImage(new Image("imagenes/indiPre.png"));
    } 

    // @FXML
    // private Text lblPregunta;
    // @FXML
    // private HBox botonHBox;
    // @FXML
    // private Button siBtn;
    // @FXML
    // private Button noBtn;

    // int indicPregunta = 0;
    // List<String> respuestasList = new LinkedList<>();
    // private List<String> preguntas = FileReaderUtil.preguntas;
    // private Map<String, List<String>> respuestas = FileReaderUtil.respuestas;
    // //inicializar con contador preguntas del archivo
    // private int numPreguntas=preguntas.size();
    // /**
    //  * Initializes the controller class.
    //  */
    // @Override
    // public void initialize(URL url, ResourceBundle rb) {
    //     siBtn.setOnAction((event) -> {
    //         guardarRespuestaSi("si");
    //     });
    //     noBtn.setOnAction((event) -> {
    //         guardarRespuestaNo("no");
    //     });
    // }    
    
    // private void mostrarVentanasPreguntas() {
    //     if (indicPregunta < numPreguntas) {
    //         lblPregunta.setText(preguntas.get(indicPregunta));
    //         botonHBox.setVisible(true);
    //         String pregunta = preguntas.get(indicPregunta);
    //         lblPregunta.setText(pregunta);
    //     } else {
    //         botonHBox.setVisible(false);
    //         lblPregunta.setText("no mas preguntas");
    //     }
    // }

    // @FXML
    // private void guardarRespuestaSi(String si) {
    //     respuestasList.add(si);
    //     System.out.println("Respuesta: "+si);
    //     avanzaPregunta();

    // }

    // @FXML
    // private void guardarRespuestaNo(String no) {
    //     respuestasList.add(no);
    //     System.out.println("Respuesta: "+no);
    //     avanzaPregunta();
    // }

    // private void avanzaPregunta(){
    //     indicPregunta++;
    //     if(indicPregunta<numPreguntas){
    //         mostrarVentanasPreguntas();
    //     } else{
    //         System.out.println("No hay mas preguntas");
    //     }

    // }
}
