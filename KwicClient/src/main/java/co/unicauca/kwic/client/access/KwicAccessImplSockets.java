/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.kwic.client.access;

import co.unicauca.clienteServidor.commons.infra.JsonError;
import co.unicauca.clienteServidor.commons.infra.Protocol;
import co.unicauca.kwic.client.infra.KwicSocket;
import co.unicauca.kwic.domain.Kwic;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 57322
 */
public class KwicAccessImplSockets {

    /**
     * El servicio utiliza un socket para comunicarse con la aplicación server
     */
    private KwicSocket mySocket;

    public KwicAccessImplSockets() {
        mySocket = new KwicSocket();
    }

    /**
     * Genera las rotaciones necesarias para una lista de palabras
     *
     * @param words Lista de palabras
     * @return Objeto Kwic
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    public Kwic generateKwic(List<String> words) throws Exception {
        String jsonResponse = null;
        //Recibo la petición en formato json
        String requestJson = kwicRequestJson(words);
        try {
            //Conecto el socket cliente
            mySocket.connect();
            //recibo la respuesta
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(KwicAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(KwicAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Hizo la lógica bien
                Kwic kwic = jsonToKwic(jsonResponse);
                return kwic;
            }
        }

    }

    /**
     * Extra los mensajes de la lista de errores
     *
     * @param jsonResponse lista de mensajes json
     * @return Mensajes de error
     */
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }

    /**
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

    /**
     * Crea una solicitud json para ser enviada por el socket
     *
     * @param words Lista de palabras
     * @return Las combinaciones del Kwic de la siguiente manera:
     * {"resource":"kwic","action":"get","parameters":[{"name":"id","value":{[Primer
     * Parcial], [Principio de Liskov],[Inyeccion de Dependencias]}}]}
     */
    private String kwicRequestJson(List<String> words) {
        Protocol protocol = new Protocol();
        protocol.setResource("kwic");
        protocol.setAction("get");
        protocol.addParameter("words", words);
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Convierte jsonKwic, proveniente del server socket, de json a un objeto
     * Kwic
     *
     * @param jsonKwic objeto Kwic en formato json
     */
    private Kwic jsonToKwic(String jsonKwic) {

        try {
            Gson gson = new Gson();
            Kwic kwic = gson.fromJson(jsonKwic, Kwic.class);
            return kwic;
        } catch (Exception e) {
            System.out.println("Hubo un error, sorry");
        }

        return null;
    }

}
