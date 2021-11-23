/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.clienteServidor.kwiccliente;

import co.unicauca.clienteServidor.Logica.RunKwic;
import co.unicauca.clienteServidor.commons.infra.Protocol;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David E
 */

public class Cliente {

    public static void main(String[] args) {

        //Host del servidor
        final String HOST = "127.0.0.1";
        //Puerto del servidor
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTO);

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            
            
            //envio de la lista de palabras
            List<String> listaPalabras = new ArrayList<>();
            
            listaPalabras.add("Primer Parcial");
            listaPalabras.add("Principio de Liskov");
            listaPalabras.add("Inversión de Dependencias");
            
            //Envio un jason al servidor, llamado para crear el obj jason
            out.writeUTF(doFindCustomerRequestJson(listaPalabras));
            
            
            //Recibo el mensaje del servidor
            String mensaje = in.readUTF();
            List<String> lista_final = processRequest(mensaje);
            
            for (String i : lista_final) {
                System.out.println(i);
            }
            

            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private static String doFindCustomerRequestJson(List<String> lista) {

        Protocol protocol = new Protocol();
        protocol.setAction("get");
        protocol.setParameters(lista);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    
    protected static List<String> processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();
        Protocol protocolRequest = gson.fromJson(requestJson, Protocol.class);

        return protocolRequest.getListaPalabras();
        
    }
    
    

}
