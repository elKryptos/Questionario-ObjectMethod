package it.objectmethod.questionario2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "risposta_utente")
public class RispostaUtente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "risposta_utente_id")
    private Long rispostaUtenteId;
    @Column(name = "punteggio_ottenuto")
    private Integer punteggioOttenuto;

    // Molte risposte dell'utente possono essere associate ad un test
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_test_id")
    private UtenteTest utenteTest;

    // Molte risposte dell'utente possono essere associate ad una domanda
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domanda_id")
    private Domanda domanda;

    // Molte risposte dell'utente possono essere associate ad una risposta
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "risposta_id")
    private Risposta risposta;
}