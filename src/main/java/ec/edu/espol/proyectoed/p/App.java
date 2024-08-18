package ec.edu.espol.proyectoed.p;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import ec.edu.espol.proyectoed.p.modelo.AnimalInfo;
import ec.edu.espol.proyectoed.p.modelo.BinaryTree;
import ec.edu.espol.proyectoed.p.util.FileReaderUtil;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static MediaPlayer mediaPlayer;
    public static FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws IOException {
        List<String> preguntas = FileReaderUtil.preguntas;
        Map<String, List<String>> respuestas = FileReaderUtil.respuestas;
        System.out.println(respuestas);
        BinaryTree<String> arbol = new BinaryTree<>();
        arbol.crearArbol(preguntas, respuestas);

        // Especifica la ruta completa del archivo FXML con su extensión
        scene = new Scene(loadFXML("fxml/inicio.fxml"), 1000, 560);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        // Especifica la ruta completa del archivo FXML con su extensión
        scene.setRoot(loadFXML("fxml/" + fxml + ".fxml"));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        // Utiliza el ClassLoader para cargar el archivo FXML desde el directorio de recursos
        URL fxmlLocation = App.class.getClassLoader().getResource(fxml);
        System.out.println(fxmlLocation);
        if (fxmlLocation == null) {
            throw new IOException("FXML file not found: " + fxml);
        }
        fxmlLoader = new FXMLLoader(fxmlLocation);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void playMusic(String resourcePath) {
        try {
            String musicFile = App.class.getClassLoader().getResource(resourcePath).toString();
            Media media = new Media(musicFile);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play(); // Inicia la reproducción automáticamente
        } catch (NullPointerException e) {
            System.out.println("El archivo de música no se encontró: " + resourcePath);
        }
    }

}
