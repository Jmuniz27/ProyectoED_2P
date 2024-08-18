package ec.edu.espol.proyectoed.p.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Map;
import java.util.LinkedList;

import ec.edu.espol.proyectoed.p.modelo.BinaryTree;
import ec.edu.espol.proyectoed.p.modelo.NodeBinaryTree;
import ec.edu.espol.proyectoed.p.util.FileReaderUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PreguntasController implements Initializable{

    @FXML
    private TextField nPreguntasTF;

    @FXML
    private Button pregunEmpBtn;

    private List<String> preguntas = FileReaderUtil.preguntas;
    private Map<String, List<String>> respuestas = FileReaderUtil.respuestas;
    private BinaryTree<String> tree = FileReaderUtil.arbolDecision; // Tu árbol binario
    //inicializar con contador preguntas del archivo
    private int preguntasArchivo = preguntas.size();

    private int numPreguntas;

    private NodeBinaryTree<String> currentNode;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentNode = tree.getRoot();
        System.out.println(currentNode.getContent());
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
        if (currentNode == null) {
            mostrarAlerta("Fin", "Has llegado a un nodo nulo.");
            return;
        }
        
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            mostrarAlerta("Resultado", "Has llegado al final del árbol: " + currentNode.getContent());
            return;
        }
    
        // Obtener la pregunta del nodo actual
        String preguntaActual = currentNode.getContent();
        // Mostrar la ventana FXML para obtener la respuesta
        String respuesta = llamarVentanaFXML(preguntaActual);
    
        // Avanzar en el árbol basado en la respuesta
        if (respuesta.equalsIgnoreCase("Si")) {
            currentNode = currentNode.getLeft() != null ? currentNode.getLeft().getRoot() : null;
        } else {
            currentNode = currentNode.getRight() != null ? currentNode.getRight().getRoot() : null;
        }
    
        // Recursivamente mostrar la siguiente pregunta
        mostrarVentanasPreguntas();
    }
    private String llamarVentanaFXML(String pregunta) {
    try {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pregunta.fxml"));
        Parent root = loader.load();

        // Obtener el controlador asociado
        PreguntaController controller = loader.getController();
        controller.setPregunta(pregunta);

        // Crear y mostrar la ventana
            Stage stage = new Stage();
            stage.setTitle("Pregunta");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait(); // Esperar hasta que la ventana se cierre

        // Retornar la respuesta obtenida del controlador
        return controller.getRespuesta();
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
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