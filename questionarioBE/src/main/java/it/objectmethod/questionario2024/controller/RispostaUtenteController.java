package it.objectmethod.questionario2024.controller;

import it.objectmethod.questionario2024.dto.RispostaUtenteDto;
import it.objectmethod.questionario2024.service.RispostaUtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/rispostaUtente")
@RequiredArgsConstructor
public class RispostaUtenteController {
    private final RispostaUtenteService rispostaUtenteService;

//    @PostMapping("")
//    public ResponseEntity<Integer> salvaRispostaECalcolaPunteggio(@RequestBody final List<RispostaUtenteDto> risposteUtenteDto) {
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(rispostaUtenteService.salvaRisposte(risposteUtenteDto));
//    }
}
