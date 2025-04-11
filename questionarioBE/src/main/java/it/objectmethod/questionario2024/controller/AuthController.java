package it.objectmethod.questionario2024.controller;

import it.objectmethod.questionario2024.dto.AuthDto;
import it.objectmethod.questionario2024.dto.UtenteDto;
import it.objectmethod.questionario2024.service.AuthService;
import it.objectmethod.questionario2024.utils.ValidationInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    // TODO: Aggiungere validazione ai parametri
    @PostMapping("")
    public AuthDto generateJwtToken(@RequestBody
                                    @Validated(ValidationInterface.SigninCase.class) final UtenteDto dto) {
        return service.generateJwtToken(dto);
    }

    // TODO: trovare un modo per validare il dto
    @PostMapping("/login")
    public AuthDto login(@RequestBody
                         @Validated(ValidationInterface.LoginCase.class) final AuthDto dto) {
        return service.login(dto);
    }

    @PostMapping(value = "/signin")
    public AuthDto signin(
            @Valid @RequestBody final UtenteDto dto) {
        return service.signin(dto);
    }

}
