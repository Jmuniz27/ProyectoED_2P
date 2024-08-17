package ec.edu.espol.proyectoed.p.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Map;
import java.util.LinkedList;

import ec.edu.espol.proyectoed.p.util.FileReaderUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PreguntasController implements Initializable{

    @FXML
    private TextField nPreguntasTF;

    @FXML
    private Button pregunEmpBtn;

    private List<String> preguntas = FileReaderUtil.preguntas;
    private Map<String, List<String>> respuestas = FileReaderUtil.respuestas;
    //inicializar con contador preguntas del archivo
    private int preguntasArchivo = preguntas.size();

    private int numPreguntas;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setNumericTextField(nPreguntasTF);
    }

    @FXML
    void btnEmpezar(ActionEvent event) {
        String entrada = nPreguntasTF.getText();
        if(entrada.isEmpty()){
            mostrarAlerta("Error", "No hay contenido en el cuadro de texto");
        }else{
            numPreguntas = Integer.parseInt(entrada);
            //hay que verificar si esta entre el rango de preguntas que permite el arbol
            if(preguntasDentroRango(numPreguntas,preguntasArchivo)){
                mostrarVentanasPreguntas();
            }else{
                mostrarAlerta("Error", "El número de preguntas no está en el rango permitido");
            }
        }
    }
    private boolean preguntasDentroRango(int numPreguntas,int rangoMax){
        return numPreguntas > 0 && numPreguntas <= rangoMax;
    }
    private void mostrarVentanasPreguntas() {
        List<String> respuestasList = new LinkedList<>();
        for (int i = 0; i < numPreguntas; i++) {
            // Llamar a la ventana FXML y obtener la respuesta
            String respuesta = llamarVentanaFXML();
            respuestasList.add(respuesta);
        }
        
        // Hacer algo con la lista de respuestas
    }
    
    private String llamarVentanaFXML() {
        return null;
    }

    //metodo para que no admita letras el textfield
    private void setNumericTextField(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}