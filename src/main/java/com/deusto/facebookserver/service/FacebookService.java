package com.deusto.facebookserver.service;

import com.deusto.facebookserver.entity.User;
import com.deusto.facebookserver.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {

    private final UserRepository userRepository;

    public FacebookService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para registrar un usuario
    public boolean register(String email, String password) {
        System.out.println("Registration requested: email = " + email);
        if (userRepository.existsById(email)) {
            return false; // El usuario ya existe
        }
        userRepository.save(new User(email, password));
        return true;
    }

    // Método para autenticar un usuario
    public boolean authenticate(String email, String password) {
        System.out.println("Authentication Requested: email = " + email);
        User user = userRepository.findByEmailAndPassword(email, password);
        return user != null;
    }
}
