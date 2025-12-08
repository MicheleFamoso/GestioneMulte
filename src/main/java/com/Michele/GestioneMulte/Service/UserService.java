package com.Michele.GestioneMulte.Service;


import com.Michele.GestioneMulte.Dto.UserDto;
import com.Michele.GestioneMulte.Enumeration.Role;
import com.Michele.GestioneMulte.Exception.NotFoundException;
import com.Michele.GestioneMulte.Model.User;
import com.Michele.GestioneMulte.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ComuneService comuneService;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,ComuneService comuneService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.comuneService = comuneService;
    }

    public User saveUser(UserDto userDto){
        User user= new User();
        user.setNome(userDto.getNome());
        user.setCognome(userDto.getCognome());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setComune(comuneService.getComuneById(userDto.getComune_id()));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        return  userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUser(int id) throws NotFoundException {
        return   userRepository.findById(id).orElseThrow(()-> new NotFoundException("Università non trovata"));
    }

    public User updateUser(int id,UserDto userDto) throws NotFoundException {
        User user = getUser(id);
        user.setNome(userDto.getNome());
        user.setCognome(userDto.getCognome());
        user.setEmail(userDto.getEmail());
        if(!passwordEncoder.matches(userDto.getPassword() , user.getPassword())){
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        return userRepository.save(user);
    }

    public void deleteUser(int id) throws NotFoundException {
        User user = getUser(id);
        userRepository.delete(user);
    }
    public User findByUsername(String Username){
        return userRepository.findByUsername(Username).orElseThrow(()-> new NotFoundException("Utente non trovato"));
    }
}
