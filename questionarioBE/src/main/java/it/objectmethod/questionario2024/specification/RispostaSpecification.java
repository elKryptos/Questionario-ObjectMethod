package it.objectmethod.questionario2024.specification;

import it.objectmethod.questionario2024.entity.Risposta;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;


@Data
@NoArgsConstructor
public class RispostaSpecification {
    private Long rispostaId;
    private String testoRisposta;
    private Boolean corretta;
    private Long domandaId;


    public Specification<Risposta> toSpecification() {
        return Specification.<Risposta>where(null)
                .and(equalIdSpecification(rispostaId))
                .and(equalTestoRispostaSpecification(testoRisposta))
                .and(equalCorrettaSpecification(corretta))
                .and(equalDomandaIdSpecification(domandaId));
    }

    private Specification<Risposta> equalIdSpecification(final Long rispostaId) {
        if (rispostaId == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("rispostaId"), rispostaId);
    }

    private Specification<Risposta> equalTestoRispostaSpecification(final String testoRisposta) {
        if (testoRisposta == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("testoRisposta"), testoRisposta);
    }

    private Specification<Risposta> equalCorrettaSpecification(final Boolean corretta) {
        if (corretta == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("corretta"), corretta);
    }

    private Specification<Risposta> equalDomandaIdSpecification(final Long domandaId) {
        if (domandaId == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("domanda").get("domandaId"), domandaId);
    }
}
