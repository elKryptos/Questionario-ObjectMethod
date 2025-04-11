package it.objectmethod.questionario2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "utente_test")
public class UtenteTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utente_test_id")
    private Long utenteTestId;
    @Column(name = "data_inizio")
    private Date dataInizio;
    @Column(name = "data_fine")
    private Date dataFine;
    @Column(name = "punteggio_totale")
    private Integer punteggioTotale;

    @OneToMany(mappedBy = "utenteTest", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RispostaUtente> rispostaUtente;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "test_id")
    private Test test;
}
