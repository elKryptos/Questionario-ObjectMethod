package it.objectmethod.questionario2024.specification;

import it.objectmethod.questionario2024.entity.Utente;
import it.objectmethod.questionario2024.entity.Utente_;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

@Value
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UtenteSpecification {

    String name;
    String email;
    String password;

    public Specification<Utente> toSpecification() {
        return Specification.<Utente>where(null)
                .and(hasName(name))
                .and(hasEmail(email))
                .and(hasPassword(password));
    }

    private Specification<Utente> hasName(final String name) {
        if (name == null)
            return null;
        return (root, query, builder) -> builder.equal(root.get(Utente_.NOME_UTENTE), name);
    }

    private Specification<Utente> hasEmail(final String email) {
        if (email == null)
            return null;
        return (root, query, builder) ->
                builder.equal(root.get(Utente_.EMAIL), email);
    }

    private Specification<Utente> hasPassword(final String password) {
        if (password == null)
            return null;
        return (root, query, builder) ->
                builder.equal(root.get(Utente_.PASSWORD), password);
    }

}
