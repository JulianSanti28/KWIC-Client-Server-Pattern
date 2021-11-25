/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unica.kwic.infraestructura;

import co.unicauca.clienteServidor.commons.infra.JsonError;
import co.unicauca.clienteServidor.commons.infra.Protocol;
import co.unicauca.kwic.domain.Kwic;
import co.unicauca.kwic.services.KwicService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julián
 */
//Cada vez que se reciba una petición se correrá en un hilo propio
public class KwicServerSocket implements Runnable {

    /**
     * Servicio de Kwic
     */
    private final KwicService service;
    /**
     * Server Socket, la orejita
     */
    private static ServerSocket ssock;

    /**
     * Socket por donde se hace la petición/respuesta
     */
    private static Socket socket;
    /**
     * Permite leer un flujo de datos del socket
     */
    private Scanner input;
    /**
     * Permite escribir un flujo de datos del scoket
     */
    private PrintStream output;
    /**
     * Puerto por donde escucha el server socket
     */
    private static final int PORT = 5000;

    /**
     * Constructor
     */
    public KwicServerSocket() {
        service = new KwicService();
    }

    /**
     * Arranca el servidor y hace la estructura completa
     */
    public void start() {

        openPort();

        while (true) {
            waitToClient();
            throwThread();
        }
    }

    /**
     * Lanza el hilo
     */
    private static void throwThread() {
        new Thread(new KwicServerSocket()).start();
    }

    /**
     * Instancia el server socket y abre el puerto respectivo
     */
    private static void openPort() {
        try {
            ssock = new ServerSocket(PORT);
            Logger.getLogger("Server").log(Level.INFO, "Servidor iniciado, escuchando por el puerto {0}", PORT);
        } catch (IOException ex) {
            System.out.println("ERROR");
        }
    }

    /**
     * Espera que el cliente se conecta y le devuelve un socket
     */
    private static void waitToClient() {
        try {
            socket = ssock.accept();
            Logger.getLogger("Socket").log(Level.INFO, "Socket conectado");
        } catch (IOException ex) {
            System.out.println("EROR");
        }
    }

    @Override
    public void run() {

        try {
            createStreams();
            readStream();
            closeStream();

        } catch (IOException ex) {
            System.out.println("ERORR");
        }

    }

    /**
     * Crea los flujos con el socket
     *
     * @throws IOException
     */
    private void createStreams() throws IOException {
        output = new PrintStream(socket.getOutputStream());
        input = new Scanner(socket.getInputStream());
    }

    /**
     * Lee el flujo del socket
     */
    private void readStream() {
        if (input.hasNextLine()) {
            // Extrae el flujo que envió la aplicación cliente
            String request = input.nextLine();
            processRequest(request);

        } else {
            output.flush();
            String errorJson = generateErrorJson();
            output.println(errorJson);
        }
    }

    /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     * @param requestJson petición que proviene del cliente socket
     *
     */
    private void processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();
        Protocol protocolRequest = gson.fromJson(requestJson, Protocol.class);
        System.out.println("Soy Resource" + protocolRequest.getResource());
        switch (protocolRequest.getResource()) {
            case "kwic":
                if (protocolRequest.getAction().equals("get")) {
                    //Generar KWIC
                    processGetKwic(protocolRequest);
                }

                break;
        }

    }

    /**
     * Procesa la solicitud del Kwic
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processGetKwic(Protocol protocolRequest) {
        // Extraer la cedula del primer parámetro
        List<String> words = protocolRequest.getParameters().get(0).getValue();
        System.out.println("Soy la Lista:" + words.toString());
        Kwic kwic = service.generateKwic(words);
        if (kwic == null) {
            String errorJson = generateNotFoundErrorJson();
            output.println(errorJson);
        } else {
            System.out.println("Response in Json" + objectToJSON(kwic));
            output.println(objectToJSON(kwic));
        }

    }

    /**
     * Genera un ErrorJson de cliente no encontrado
     *
     * @return error en formato json
     */
    private String generateNotFoundErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("404");
        error.setError("NOT_FOUND");
        error.setMessage("Cliente no encontradoloadProperty. Cédula no existe");
        errors.add(error);
        Gson gson = new Gson();
        String errorsJson = gson.toJson(errors);
        return errorsJson;
    }

    /**
     * Genera un ErrorJson genérico
     *
     * @return error en formato json
     */
    private String generateErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("400");
        error.setError("BAD_REQUEST");
        error.setMessage("Error en la solicitud");
        errors.add(error);

        Gson gson = new Gson();
        String errorJson = gson.toJson(errors);

        return errorJson;
    }

    /**
     * Cierra los flujos de entrada y salida
     *
     * @throws IOException
     */
    private void closeStream() throws IOException {
        output.close();
        input.close();
        socket.close();
    }

    /**
     * Convierte el objeto Kwic a json para que el servidor lo envie como
     * respuesta por el socket
     *
     * @param Kwic kwic
     * @return kwic en formato json
     */
    private String objectToJSON(Kwic kwic) {
        Gson gson = new Gson();
        String strObject = gson.toJson(kwic);
        return strObject;
    }

}
