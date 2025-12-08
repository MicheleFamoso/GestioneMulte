package com.Michele.GestioneMulte.Service;


import com.Michele.GestioneMulte.Dto.LoginDto;
import com.Michele.GestioneMulte.Exception.NotFoundException;
import com.Michele.GestioneMulte.Model.User;
import com.Michele.GestioneMulte.Repository.UserRepository;
import com.Michele.GestioneMulte.Security.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {
    private final UserRepository userRepository;
    private final JwtTool jwtTool;
    private final PasswordEncoder  passwordEncoder;

    public AuthService(UserRepository userRepository, JwtTool jwtTool, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTool = jwtTool;
        this.passwordEncoder = passwordEncoder;
    }



    public String login(LoginDto loginDto) throws NotFoundException {
        User user = userRepository.findByUsername(loginDto.getUsername()).
                orElseThrow(() -> new NotFoundException("Utente con username/password non trovato"));
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return jwtTool.createToken(user);
        } else {
            throw new NotFoundException("Utente con username/password non trovato");
        }
    }
}
