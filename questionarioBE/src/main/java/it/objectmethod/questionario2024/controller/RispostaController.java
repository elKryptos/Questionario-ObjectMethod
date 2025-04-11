package it.objectmethod.questionario2024.controller;

import it.objectmethod.questionario2024.dto.RispostaDto;
import it.objectmethod.questionario2024.service.RispostaService;
import it.objectmethod.questionario2024.specification.RispostaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/risposte")
@RequiredArgsConstructor
public class RispostaController {

    private final RispostaService rispostaService;



    @GetMapping("/spec")
    public List<RispostaDto> getRisposteBySpec(final RispostaSpecification rispostaSpecification) {
        return rispostaService.findWithSpecification(rispostaSpecification);
    }
}
