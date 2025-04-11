package it.objectmethod.questionario2024.service;

import it.objectmethod.questionario2024.entity.TestDomanda;
import it.objectmethod.questionario2024.mapper.TestDomandaMapper;
import it.objectmethod.questionario2024.repository.TestDomandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDomandaService {
    @Autowired
    private TestDomandaRepository testDomandaRepository;
    @Autowired
    private TestDomandaMapper testDomandaMapper;

    public List<TestDomanda> getAllTestDomande() {
        return testDomandaRepository.findAllWithRelations();
    }
}
