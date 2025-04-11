package it.objectmethod.questionario2024.specification;

import it.objectmethod.questionario2024.entity.Domanda;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@Data
@NoArgsConstructor
public class DomandaSpecification {
    private Long domandaId;
    private String testoDomanda;

    public Specification<Domanda> toSpecification() {
        return Specification.<Domanda>where(null)
                .and(equalIdSpecification(domandaId))
                .and(equalTestoDomandaSpecification(testoDomanda));
    }

    private Specification<Domanda> equalIdSpecification(final Long domandaId) {
        if (domandaId == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("domandaId"), domandaId);
    }

    private Specification<Domanda> equalTestoDomandaSpecification(final String testoDomanda) {
        if (testoDomanda == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("testoDomanda"), testoDomanda);
    }
}

