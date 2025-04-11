package it.objectmethod.questionario2024.repository;

import it.objectmethod.questionario2024.entity.Domanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DomandaRepository extends JpaRepository<Domanda, Long>, JpaSpecificationExecutor<Domanda> {
}
