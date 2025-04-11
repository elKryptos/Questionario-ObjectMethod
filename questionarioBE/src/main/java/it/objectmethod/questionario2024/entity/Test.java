package it.objectmethod.questionario2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long testId;
    @Column(name = "nome_test")
    private String nomeTest;
    @Column(name = "durata_minuti")
    private Integer durataMinuti;

    // Un test può essere associato a più utenti che svolgono i test
    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UtenteTest> utenteTests;

    // Un test ha una serie di domande
    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestDomanda> testDomande;
}
