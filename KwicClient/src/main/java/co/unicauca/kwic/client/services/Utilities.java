/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.kwic.client.services;

import co.unicauca.kwic.client.access.KwicAccessImplSockets;
import co.unicauca.kwic.domain.Kwic;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilidades para el correcto envío de información del lado del Cliente
 *
 * @author Julián
 */
public class Utilities {

    /**
     *
     * @return List de frases con base a la información que le ingresa por el
     * Gui
     */
    private List<String> generarListaGui(String inputGui) {
        //Lista para retornar
        List<String> phrases = new ArrayList<>();
        String[] aux = inputGui.split(",");
        for (String i : aux) {
            phrases.add(i);
        }
        return phrases;
    }

    /**
     *
     * @param inputGui
     * @return Kwic Objeto que trae la información de respuesta
     * @throws Exception
     */
    public Kwic sendRequest(String inputGui) throws Exception {
        KwicAccessImplSockets request = new KwicAccessImplSockets();
        /*Objeto que trae la respuesta*/
        Kwic response = request.generateKwic(generarListaGui(inputGui));
        return response;
    }

}
