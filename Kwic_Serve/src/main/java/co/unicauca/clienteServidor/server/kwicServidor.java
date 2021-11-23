/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.clienteServidor.server;

import co.unicauca.clienteServidor.Logica.RunKwic;
import co.unicauca.clienteServidor.commons.infra.Protocol;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David E
 */
public class kwicServidor {
    
    private static List<String> lista = new ArrayList<>();
            
    public static void main(String[] args) {

        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;

        //puerto de nuestro servidor
        final int PUERTO = 5000;

        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                sc = servidor.accept();

                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                //Leo el mensaje que me envia
                String mensaje = in.readUTF();

                System.out.println(mensaje);

                
                processRequest(mensaje);
                
                //Obj to Json
                
                //Le envio un mensaje
                out.writeUTF(doFindCustomerRequestJson(lista));
                
                //Cierro el socket
                sc.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException ex) {
            Logger.getLogger(kwicServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     * @param requestJson petición que proviene del cliente socket en formato
     * json que viene de esta manera:
     * "{"resource":"customer","action":"get","parameters":[{"name":"id","value":"1"}]}"
     *
     */
    //@Override
    protected static void processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();
        System.out.println(requestJson);
        Protocol protocolRequest = gson.fromJson(requestJson, Protocol.class);

        switch (protocolRequest.getAction()) {
            case "get":
                lista = RunKwic.Kwic(protocolRequest.getListaPalabras());
                break;
            default:
                break;
        }
    }
    
    private static String doFindCustomerRequestJson(List<String> listaResultante) {
        Protocol protocol = new Protocol();
        protocol.setParameters(listaResultante);
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

}
