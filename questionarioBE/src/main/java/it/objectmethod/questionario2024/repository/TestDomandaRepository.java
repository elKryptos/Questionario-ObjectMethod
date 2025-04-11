package it.objectmethod.questionario2024.repository;

import it.objectmethod.questionario2024.entity.TestDomanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDomandaRepository extends JpaRepository<TestDomanda, Long>, JpaSpecificationExecutor<TestDomanda> {
    @Query("""
                SELECT td
                FROM TestDomanda td
                JOIN FETCH td.test t
                JOIN FETCH td.domanda d
                JOIN FETCH d.risposte r
            """)
    List<TestDomanda> findAllWithRelations();
}
