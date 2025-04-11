package it.objectmethod.questionario2024.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "risposta")
public class Risposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "risposta_id")
    private Long rispostaId;
    @Column(name = "testo_risposta")
    private String testoRisposta;
    @Column(name = "corretta")
    private Boolean corretta;

    // Molte risposte possono essere associate a una domanda
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "domanda_id")
    private Domanda domanda;

    // Una risposta può essere fatta da molti utenti
    @OneToMany(mappedBy = "risposta", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RispostaUtente> risposteUtente;
}