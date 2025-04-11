package it.objectmethod.questionario2024.service;

import it.objectmethod.questionario2024.Constants;
import it.objectmethod.questionario2024.dto.TestDto;
import it.objectmethod.questionario2024.entity.Test;
import it.objectmethod.questionario2024.exception.NotFoundException;
import it.objectmethod.questionario2024.mapper.TestMapper;
import it.objectmethod.questionario2024.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;

    public List<TestDto> getAllTests() {
        List<Test> tests = testRepository.findAll();
        return testMapper.toDtoList(tests);
    }

    public TestDto getTestById(final Long id) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.TEST_NOT_FOUND.getMessage()));
        return testMapper.toDto(test);
    }

    public TestDto createTest(final TestDto testDto) {
        Test test = testMapper.toEntity(testDto);
        Test savedTest = testRepository.save(test);
        return testMapper.toDto(savedTest);
    }

    public TestDto upddateTest(final Long id, final TestDto testDto) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.TEST_NOT_FOUND.getMessage()));
        test.setNomeTest(testDto.getNomeTest());
        test.setDurataMinuti(testDto.getDurataMinuti());
        Test savedTest = testRepository.save(test);
        return testMapper.toDto(savedTest);
    }

    public String deleteTest(final Long id) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.TEST_NOT_FOUND.getMessage()));
        testRepository.delete(test);
        return Constants.TEST_DELETED.getMessage();
    }

    public TestDto getTestByNome(final String nomeTest) {
        Test test = testRepository.findByNomeTest(nomeTest)
                .orElseThrow(() -> new NotFoundException(Constants.TEST_NOT_FOUND.getMessage()));
        return testMapper.toDto(test);
    }
}
