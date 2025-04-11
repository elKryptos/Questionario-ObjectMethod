package it.objectmethod.questionario2024.service;

import it.objectmethod.questionario2024.dto.AuthDto;
import it.objectmethod.questionario2024.dto.UtenteDto;
import it.objectmethod.questionario2024.utils.BearerTokenUtil;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static it.objectmethod.questionario2024.Constants.ALREADY_SIGNIN;
import static it.objectmethod.questionario2024.Constants.REGISTRAZIONE_EFFETTUATA;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtenteService utenteService;
    private final BearerTokenUtil bearerTokenUtil;
    private final Validator validator;

    /**
     * Genera un token JWT basato su un oggetto AuthDto.
     *
     * @param dto i dettagli dell'autenticazione.
     * @return un oggetto AuthDto contenente il token JWT generato.
     */
    public AuthDto generateJwtToken(UtenteDto dto) {
        return response(bearerTokenUtil.generateToken(dto));
    }

    /**
     * Gestisce il login di un utente, generando un token JWT contenente l'utenteId.
     *
     * @param dto i dettagli dell'autenticazione.
     * @return un oggetto AuthDto contenente il token JWT e informazioni sull'amministrazione.
     */

    public AuthDto login(AuthDto dto) {
        // Verifica se l'utente esiste nel database
        final UtenteDto userSubscribe = utenteService.isUserSubscribe(dto.getEmail(), dto.getPassword());
        if (userSubscribe == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenziali non valide");
        }

        // Genera un token includendo l'utenteId
        String token = bearerTokenUtil.generateToken(userSubscribe);

        // Restituisci il token e informazioni sul ruolo dell'utente
        return AuthDto.builder()
                .response(token)
                .isAdmin(userSubscribe.getIsAdmin())
                .utenteId(bearerTokenUtil.getUtenteId(token))
                .build();
    }


    /**
     * Gestisce la registrazione di un nuovo utente.
     *
     * @param dto i dettagli dell'utente.
     * @return un oggetto AuthDto con un messaggio di conferma o errore.
     */
    public AuthDto signin(final UtenteDto dto) {
        // Verifica se l'utente è già registrato
        final UtenteDto userSubscribe = utenteService.isUserSubscribe(dto.getEmail(), dto.getPassword());
        if (Objects.nonNull(userSubscribe)) {
            return response(ALREADY_SIGNIN.getMessage());
        } else {
            // Imposta il nuovo utente come non amministratore e salva
            dto.setIsAdmin(Boolean.FALSE);
            utenteService.save(dto);
        }
        return response(REGISTRAZIONE_EFFETTUATA.getMessage());
    }

    /**
     * Metodo ausiliario per costruire un oggetto AuthDto con una risposta testuale.
     *
     * @param value il valore da includere nella risposta.
     * @return un oggetto AuthDto contenente la risposta.
     */
    private AuthDto response(final String value) {
        return AuthDto.builder()
                .response(value)
                .build();
    }
}
