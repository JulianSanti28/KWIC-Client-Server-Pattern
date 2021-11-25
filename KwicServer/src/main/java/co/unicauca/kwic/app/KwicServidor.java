/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.kwic.app;

import co.unica.kwic.infraestructura.KwicServerSocket;

/**
 *
 * @author David E
 */
public class KwicServidor {

    public static void main(String[] args) {
        /*Inicia el Servidor*/
        KwicServerSocket s = new KwicServerSocket();
        s.start();
    }

}
