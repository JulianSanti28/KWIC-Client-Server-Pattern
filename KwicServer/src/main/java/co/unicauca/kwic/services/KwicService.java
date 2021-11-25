/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.kwic.services;

import co.unicauca.kwic.domain.Kwic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author 57322
 */
public class KwicService {

    private List<String> LstResultados;

    public KwicService() {
        this.LstResultados = new ArrayList<String>();
    }

    public Kwic generateKwic(List<String> words) {
        System.out.println("Hiii Soy Service");
        Kwic objKwic = new Kwic();
        objKwic.setResponse(Kwic(words));
        return objKwic;
    }

    private List<String> Kwic(List<String> listaPalabras) {
        for (String i : listaPalabras) {
            rotarString(i);
        }

        Collections.sort(LstResultados, String.CASE_INSENSITIVE_ORDER);
        return LstResultados;
    }

    private void rotarString(String frase) {
        String aux = "";
        int contador = 1;
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
