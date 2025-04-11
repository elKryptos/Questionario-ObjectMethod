package it.objectmethod.questionario2024.controller;

import it.objectmethod.questionario2024.dto.UtenteDto;
import it.objectmethod.questionario2024.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("")
    public ResponseEntity<List<UtenteDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(utenteService.getAllUtenti());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UtenteDto>  findUser(@PathVariable final Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(utenteService.read(id));
    }

    @PostMapping("")
    public ResponseEntity<UtenteDto>  saveUser(final UtenteDto user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(utenteService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable final Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(utenteService.delete(id));
    }

}
