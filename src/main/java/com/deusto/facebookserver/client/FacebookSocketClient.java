package com.deusto.facebookserver.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FacebookSocketClient {

    public static void main(String[] args) {
        String serverAddress = "localhost"; // Dirección del servidor
        int serverPort = 9091;             // Puerto del servidor

        try (Socket socket = new Socket(serverAddress, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Enviar acción para registrar un usuario
            out.println("REGISTER");
            out.println("eeguskiza");
            out.println("0000");

            // Leer respuesta del servidor
            String response = in.readLine();
            System.out.println("Respuesta del servidor: " + response);

        } catch (IOException e) {
            System.err.println("Error conectando al servidor: " + e.getMessage());
        }
    }
}

