
/*!
\file   KwicService.java
\date   2021-10-25
\author Julián Martinez <juliansmartinez@unicauca.edu.co> 104618021321
\brief  Lógica para el desarrollo del problema Key Word In Context.
\par Copyright
Information contained herein is proprietary to and constitutes valuable
confidential trade secrets of unicauca, and
is subject to restrictions on use and disclosure.
\par
Copyright (c) unicauca 2021. All rights reserved.
\par
The copyright notices above do not evidence any actual or
intended publication of this material.
*******************************************************************************/
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
