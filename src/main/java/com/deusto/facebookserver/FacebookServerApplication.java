package com.deusto.facebookserver;

import com.deusto.facebookserver.socket.server.FacebookSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacebookServerApplication implements CommandLineRunner {

    @Autowired
    private FacebookSocketServer facebookSocketServer;

    public static void main(String[] args) {
        SpringApplication.run(FacebookServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Thread socketThread = new Thread(facebookSocketServer);
        socketThread.start();
    }
}
