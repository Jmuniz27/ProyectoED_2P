package ec.edu.espol.proyectoed.p.controller;
import ec.edu.espol.proyectoed.p.App;
import ec.edu.espol.proyectoed.p.modelo.FilePair;
import ec.edu.espol.proyectoed.p.util.FileReaderUtil;
import ec.edu.espol.proyectoed.p.util.Util;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;

public class ReutilizarController implements Initializable {

    @FXML
    private ImageView ivFondo;
    @FXML
    private Button btnJugar;
    @FXML
    private ComboBox<FilePair> cbArchivos; // Ahora ComboBox<FilePair>

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ivFondo.setImage(new Image("/imagenes/reutilizar.png"));
        cbArchivos.getItems().addAll(loadOptions()); // Carga las opciones en el ComboBox
    }

    @FXML
    private void mouseEnBoton(MouseDragEvent event) {
    }

    @FXML
    private void clickEnJugar(ActionEvent event) {
        // Obtiene la opción seleccionada
        FilePair selectedPair = cbArchivos.getSelectionModel().getSelectedItem();
        if (selectedPair != null) {
            String respuestasPath = selectedPair.getRespuestasPath();
            String preguntasPath = selectedPair.getPreguntasPath();

            // Aquí puedes trabajar con los paths seleccionados
            FileReaderUtil.escogerArchivo(preguntasPath, respuestasPath);
            try {
                App.setRoot("preguntas");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            Util.mostrarAlerta("Selección vacía","No se seleccionó ninguna opción.");
        }
    }

    private List<FilePair> loadOptions() {
        List<FilePair> options = new LinkedList<>();
        File folder = new File("src/main/resources/files");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) {
            System.out.println("No se encontraron archivos en la carpeta especificada.");
            return options;
        }

        Pattern pattern = Pattern.compile("respuestas(\\d+)\\.txt");
        for (File file : listOfFiles) {
            Matcher matcher = pattern.matcher(file.getName());
            if (matcher.matches()) {
                String numero = matcher.group(1);
                String respuestasPath = folder.getPath() + "/respuestas" + numero + ".txt";
                String preguntasPath = folder.getPath() + "/preguntas" + numero + ".txt";

                // Verifica si ambos archivos existen
                File respuestas = new File(respuestasPath);
                File preguntas = new File(preguntasPath);

                if (respuestas.exists() && preguntas.exists()) {
                    options.add(new FilePair(respuestasPath, preguntasPath));
                }
            }
        }
        return options;
    }
}
