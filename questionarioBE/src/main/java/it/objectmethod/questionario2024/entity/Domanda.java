package it.objectmethod.questionario2024.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Classe che rappresenta una singola domanda.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "domanda")
public class Domanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domanda_id")
    private Long domandaId;
    @Column(name = "testo_domanda")
    private String testoDomanda;
    @Column(name = "punteggio")
    private Integer punteggio = 1;

    /*
    Una domanda appartiene a più test diversi.
     */
    @OneToMany(mappedBy = "domanda", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestDomanda> testDomanda;

    /*
    A una domanda sono associate più risposte
     */
    @OneToMany(mappedBy = "domanda", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Risposta> risposte;

    /*
    Una domanda può essere fatta a diversi utenti
     */
    @OneToMany(mappedBy = "domanda", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RispostaUtente> risposteUtente;
}