package com.deusto.facebookserver.socket.server;

import com.deusto.facebookserver.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
@Component
public class FacebookSocketServer implements Runnable {

    private static final int PORT = 9091;

    @Autowired
    private FacebookService facebookService;

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server listening to PORT: " + PORT);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                } catch (IOException e) {
                    System.err.println("Error manejando el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error iniciando el servidor: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String action = in.readLine();
            String email = in.readLine();
            String password = in.readLine();

            if ("REGISTER".equalsIgnoreCase(action)) {
                boolean isRegistered = facebookService.register(email, password);
                out.println(isRegistered ? "REGISTER_SUCCESS" : "REGISTER_FAILURE");
            } else if ("AUTHENTICATE".equalsIgnoreCase(action)) {
                boolean isAuthenticated = facebookService.authenticate(email, password);
                out.println(isAuthenticated ? "AUTH_SUCCESS" : "AUTH_FAILURE");
            } else {
                out.println("UNKNOWN_ACTION");
            }

        } catch (IOException e) {
            System.err.println("Error manejando cliente: " + e.getMessage());
        }
    }
}

