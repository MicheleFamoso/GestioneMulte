package com.Michele.GestioneMulte.Controller;

import com.Michele.GestioneMulte.Dto.LoginDto;
import com.Michele.GestioneMulte.Dto.UserDto;
import com.Michele.GestioneMulte.Exception.NotFoundException;
import com.Michele.GestioneMulte.Exception.ValidationException;
import com.Michele.GestioneMulte.Model.User;
import com.Michele.GestioneMulte.Service.AuthService;
import com.Michele.GestioneMulte.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/auth/register")
    public User register(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce("",(s,e)->s+e));
        }
        return   userService.saveUser(userDto);
    }


    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult) throws ValidationException, NotFoundException {
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce("",(s,e)->s+e));
        }
        return authService.login(loginDto);
    }
}
