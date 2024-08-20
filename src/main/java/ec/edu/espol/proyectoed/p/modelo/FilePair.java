/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed.p.modelo;

/**
 *
 * @author isabella
 */
public class FilePair {
    private final String respuestasPath;
    private final String preguntasPath;

    public FilePair(String respuestasPath, String preguntasPath) {
        this.respuestasPath = respuestasPath;
        this.preguntasPath = preguntasPath;
    }

    public String getRespuestasPath() {
        return respuestasPath;
    }

    public String getPreguntasPath() {
        return preguntasPath;
    }

    @Override
    public String toString() {
        // Esto define cómo se mostrará la opción en el ComboBox
        return "Opción " + respuestasPath.replaceAll("\\D", ""); // Extrae el número de la opción
    }
}
