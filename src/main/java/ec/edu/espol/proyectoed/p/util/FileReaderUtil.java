package ec.edu.espol.proyectoed.p.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import ec.edu.espol.proyectoed.p.modelo.BinaryTree;

public class FileReaderUtil {
    public static List<String> preguntas;
    public static Map<String, List<String>> respuestas ;
    public static BinaryTree<String> arbolDecision;
    
    public static void escogerArchivo(String preguntasPath, String respuestasPath){
        preguntas = readFile(preguntasPath);
        respuestas = readAnswers(respuestasPath);
        arbolDecision = new BinaryTree<>(preguntas, respuestas);
    }
    public static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            System.out.println("Leyendo archivo: " + filename);
            String line;
            while ((line = br.readLine()) != null) {
                // Eliminar tildes de la línea
                line = removeAccents(line);
                System.out.println(line);
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return lines;
    }

    public static Map<String, List<String>> readAnswers(String filename) {
        Map<String, List<String>> answers = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Eliminar tildes de la línea
                line = removeAccents(line);
                
                String[] parts = line.split(" ");
                String animal = parts[0];
                List<String> respuestas = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    respuestas.add(parts[i]);
                }
                answers.put(animal, respuestas);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return answers;
    }

    // Método para eliminar tildes
    public static String removeAccents(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    public static List<String> getQuestions() {
        return preguntas;
    }

    public static BinaryTree<String> recorrerArbol(BinaryTree<String> arbol, String respo){
        if(respo.equalsIgnoreCase("si")){
            return arbol.getRoot().getLeft();
        } else {
            return arbol.getRoot().getRight();
        }
    }
}
