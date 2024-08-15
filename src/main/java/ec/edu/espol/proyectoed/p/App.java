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

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) throws IOException {
        // Especifica la ruta completa del archivo FXML con su extensión
        scene = new Scene(loadFXML("inicio.fxml"), 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        // Especifica la ruta completa del archivo FXML con su extensión
        scene.setRoot(loadFXML(fxml + ".fxml"));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        // Utiliza el ClassLoader para cargar el archivo FXML desde el directorio de recursos
        URL fxmlLocation = App.class.getClassLoader().getResource(fxml);
        if (fxmlLocation == null) {
            throw new IOException("FXML file not found: " + fxml);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
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
