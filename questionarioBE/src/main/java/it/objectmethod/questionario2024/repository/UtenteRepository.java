package it.objectmethod.questionario2024.repository;

import it.objectmethod.questionario2024.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long>, JpaSpecificationExecutor<Utente> {
    Utente findByEmailAndPassword(String email, String password);

}
