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
@Table(name = "test_domanda")
public class TestDomanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_domanda_id")
    private Long testDomandaId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "domanda_id")
    private Domanda domanda;
}