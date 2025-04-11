package it.objectmethod.questionario2024.controller;

import it.objectmethod.questionario2024.dto.TestDto;
import it.objectmethod.questionario2024.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("")
    public List<TestDto> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/{id}")
    public TestDto getTestById(@PathVariable final Long id) {
        return testService.getTestById(id);
    }

    @PostMapping("")
    public ResponseEntity<TestDto> createTest(@RequestBody final TestDto testDto) {
        return new ResponseEntity<>(testService.createTest(testDto), HttpStatus.CREATED);
    }

    @GetMapping("/nome/{nomeTest}")
    public TestDto getTestByNome(@PathVariable final String nomeTest) {
        return testService.getTestByNome(nomeTest);
    }
}
