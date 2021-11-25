/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.clienteServidor.commons.infra;

import java.util.List;

/**
 * Parametro. Lo usa la clase Protocol
 *
 * @author Julián
 */
public class Parameter {

    private String name;
    private List<String> value;

    public Parameter(String name, List<String> value) {
        this.name = name;
        this.value = value;
    }

    public Parameter() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
    
    


}
