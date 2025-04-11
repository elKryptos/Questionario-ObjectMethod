package it.objectmethod.questionario2024.controller;

import it.objectmethod.questionario2024.dto.DomandaDto;
import it.objectmethod.questionario2024.service.DomandaService;
import it.objectmethod.questionario2024.specification.DomandaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/domande")
@RequiredArgsConstructor
public class DomandaController {

    private final DomandaService domandaService;

    @GetMapping("")
    public List<DomandaDto> getAllDomande() {
        return domandaService.getAllDomande();
    }

    @GetMapping("/{id}")
    public DomandaDto getDomandaById(@PathVariable final Long id) {
        return domandaService.getDomandaById(id);
    }

    @PostMapping("")
    public ResponseEntity<DomandaDto> createDomanda(@RequestBody final DomandaDto domandaDto) {
        return new ResponseEntity<>(domandaService.createDomanda(domandaDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public DomandaDto updateDomanda(@PathVariable final Long id, @RequestBody final DomandaDto domandaDto) {
        return domandaService.updateDomanda(id, domandaDto);

    }

    @DeleteMapping("/{id}")
    public String deleteDomanda(@PathVariable final Long id) {
        return domandaService.deleteDomanda(id);
    }

    @GetMapping("/spec")
    public List<DomandaDto> getDomandeBySpecification(final DomandaSpecification domandaSpecification) {
        return domandaService.getDomandeBySpecification(domandaSpecification);
    }
}
