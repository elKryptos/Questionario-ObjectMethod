package it.objectmethod.questionario2024.repository;

import it.objectmethod.questionario2024.entity.Risposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RispostaRepository extends JpaRepository<Risposta, Long>, JpaSpecificationExecutor<Risposta> {
    Risposta findByRispostaIdAndCorrettaIsTrue(Long id);
}
