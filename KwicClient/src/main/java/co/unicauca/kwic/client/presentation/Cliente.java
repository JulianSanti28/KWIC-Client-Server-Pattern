/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.kwic.client.presentation;

import co.unicauca.kwic.client.access.KwicAccessImplSockets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David E
 */
public class Cliente {

    public static void main(String[] args) throws Exception {

        KwicAccessImplSockets request = new KwicAccessImplSockets();

        List<String> listaPalabras = new ArrayList<>();
        listaPalabras.add("Primer Parcial");
        listaPalabras.add("Principio de Liskov");
        listaPalabras.add("Inversión de Dependencias");
        System.out.println(request.generateKwic(listaPalabras).getResponse().toString());

    }

}
