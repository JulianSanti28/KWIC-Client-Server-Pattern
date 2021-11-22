/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.clienteServidor.commons.infra;

import java.util.ArrayList;
import java.util.List;

/**
 * Protocolo de comunicación entre la aplicación cliente y el servidor
 *
 * @author Libardo, Julio
 */
public class Protocol {

    private String action;
    private List<String> listaPalabras;

    public Protocol() {
        this.listaPalabras = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Protocol{" + "action=" + action + ", listaPalabras=" + listaPalabras + '}';
    }
    
    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<String> getListaPalabras() {
        return listaPalabras;
    }

    public void setParameters(List<String> lista) {
        this.listaPalabras = lista;
    }

    public void addParameter(String frase) {
        listaPalabras.add(frase);
    }

}
