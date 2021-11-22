/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.clienteServidor.Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author David E
 */
public class RunKwic {

    private static List<String> LstResultados = new ArrayList<String>();

    public static List<String> Kwic(List<String> listaPalabras) {
        for (String i : listaPalabras) {
            rotarString(i);
        }

        Collections.sort(LstResultados, String.CASE_INSENSITIVE_ORDER);
        return LstResultados;
    }

    public static void rotarString(String frase) {
        String aux = "";
        int contador = 1;
        //frase = frase.toLowerCase();
        String[] parts = frase.split(" ");

        while (contador <= parts.length) {

            String rotar_primera_palabra = parts[parts.length - 1];
            aux = aux + rotar_primera_palabra;

            for (int i = 0; i < parts.length - 1; i++) {
                aux = aux + " " + parts[i];
            }

            LstResultados.add(aux);
            parts = aux.split(" ");
            aux = "";
            contador++;

        }
    }

}
