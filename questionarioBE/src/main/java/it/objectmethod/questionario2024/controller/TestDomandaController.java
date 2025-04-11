package it.objectmethod.questionario2024.controller;

import it.objectmethod.questionario2024.entity.TestDomanda;
import it.objectmethod.questionario2024.service.TestDomandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/testdomande")
public class TestDomandaController {
    @Autowired
    private TestDomandaService testDomandaService;

    @GetMapping("")
    public List<TestDomanda> getALlTestDomande() {
        return testDomandaService.getAllTestDomande();
    }
}
