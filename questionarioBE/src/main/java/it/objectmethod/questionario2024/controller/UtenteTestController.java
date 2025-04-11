package it.objectmethod.questionario2024.controller;

import it.objectmethod.questionario2024.dto.RispostaUtenteDto;
import it.objectmethod.questionario2024.dto.UtenteTestDto;
import it.objectmethod.questionario2024.service.UtenteTestService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/utenteTest")
public class UtenteTestController {
    private final UtenteTestService utenteTestService;

    @PostMapping("")
    public ResponseEntity<UtenteTestDto> saveUtenteTest(final UtenteTestDto utenteTestDto, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(utenteTestService.createUtenteTest(utenteTestDto, request));
    }

    @PutMapping("/{id}/concludi")
    public ResponseEntity<Integer> concludeTest(@PathVariable final Long id, @RequestBody final List<RispostaUtenteDto> risposteUtente) {
        Integer punteggioTotale = utenteTestService.updateUtenteTest(id, risposteUtente);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(punteggioTotale);
    }
}
