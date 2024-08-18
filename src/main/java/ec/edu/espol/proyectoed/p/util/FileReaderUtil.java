package ec.edu.espol.proyectoed.p.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ec.edu.espol.proyectoed.p.modelo.BinaryTree;

public class FileReaderUtil {
    public static List<String> preguntas = readFile("src/main/resources/files/preguntas.txt");
    public static Map<String, List<String>> respuestas = readAnswers("src/main/resources/files/respuestas.txt");
    public static BinaryTree<String> arbolDecision = new BinaryTree<>(preguntas, respuestas);
    public static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
        return lines;
    }
    public static Map<String, List<String>> readAnswers(String filename) {
        Map<String, List<String>> answers = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String animal = parts[0];
                List<String> respuestas = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    respuestas.add(parts[i]);
                }
                answers.put(animal, respuestas);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
        return answers;
    }
    public static List<String> getQuestions() {
        return preguntas;
    }
    public static List<String> recorrerOpciones(BinaryTree<String> arbol){
        List<String> respuestasUser = new LinkedList<>();
        String respuestaAnimal;
        while(!arbol.isLeaf()){
            String respuestaIng = "algo";
            respuestasUser.add(respuestaIng);
            if(respuestaIng.equalsIgnoreCase("si")){
                recorrerOpciones(arbol.getRoot().getRight());
            }else{
                recorrerOpciones(arbol.getRoot().getLeft());
            }
            respuestaAnimal = arbol.getRoot().getContent();
        }
        return respuestasUser;
    }
}
