
package it.objectmethod.questionario2024.repository;

import it.objectmethod.questionario2024.entity.RispostaUtente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RispostaUtenteRepository extends JpaRepository<RispostaUtente, Long> {
}
