package ec.edu.espol.proyectoed.p.modelo;

import java.io.IOException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;

public class AnimalInfo {
    public AnimalInfo(String animalName){
        this.animalName = animalName;
        this.getAnimalInfo(animalName);
    }

    private static final String WIKIPEDIA_API_URL = "https://es.wikipedia.org/api/rest_v1/page/summary/";
    private String animalDecs;
    private String animalImg;
    private String animalName;

    public String getAnimalDecs() {
        return animalDecs;
    }

    public String getAnimalImg() {
        return animalImg;
    }
    
    public String getAnimalName() {
        return animalName;
    }

    public AnimalInfo getAnimalInfo(String animalName){
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(WIKIPEDIA_API_URL + animalName);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                JSONObject json = new JSONObject(jsonResponse);

                // Obtén la descripción
                String description = json.optString("extract", "Descripción no disponible.");
                animalDecs = description;

                // Obtén la URL de la imagen
                String imageUrl = json.has("thumbnail") ?
                        json.getJSONObject("thumbnail").optString("source", "URL de imagen no disponible.")
                        : "URL de imagen no disponible.";
                animalImg = imageUrl;
                return this;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Error al obtener la información del animal.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al obtener la información del animal.");
        }
        return null;
    }
    
}