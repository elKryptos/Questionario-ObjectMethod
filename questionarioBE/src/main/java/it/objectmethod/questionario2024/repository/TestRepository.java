package it.objectmethod.questionario2024.repository;

import it.objectmethod.questionario2024.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>, JpaSpecificationExecutor<Test> {
    Optional<Test> findByNomeTest(String nomeTest);
}
