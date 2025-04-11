package it.objectmethod.questionario2024.service;

import it.objectmethod.questionario2024.Constants;
import it.objectmethod.questionario2024.dto.UtenteDto;
import it.objectmethod.questionario2024.entity.Utente;
import it.objectmethod.questionario2024.mapper.UtenteMapper;
import it.objectmethod.questionario2024.repository.UtenteRepository;
import it.objectmethod.questionario2024.specification.UtenteSpecification;
import it.objectmethod.questionario2024.utils.BearerTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private final UtenteRepository repository;
    private final UtenteMapper mapper;
    private final BearerTokenUtil bearerTokenUtil;

    public List<UtenteDto> getAllUtenti (){
        List<Utente> utenti = repository.findAll();
        return mapper.toDtoList(utenti);
    }

    public UtenteDto save(UtenteDto userDto) {
        return mapper.toDto(repository.save(mapper.toEntity(userDto)));
    }

    public UtenteDto read(final Long id) {
//        Optional<Utente> user = repository.findById(id);
//        if (user.isPresent())
//            return mapper.toDto(user.get());
//        else
//            throw new IllegalArgumentException("Impossibile trovare l'elemento desiderato");
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException(Constants.USER_NOT_FOUND.getMessage()));
    }

    public List<UtenteDto> findByName(final UtenteSpecification specification) {
        return mapper.toDtoList(repository.findAll(specification.toSpecification()));
    }

    public List<UtenteDto> read() {
        return mapper.toDtoList(repository.findAll());
    }

    public String delete(final Long id) {
        repository.deleteById(id);
        return Constants.USER_DELETED.getMessage();
    }

    public UtenteDto isUserSubscribe(final String email, final String password) {
        return mapper.toDto(repository.findByEmailAndPassword(email, password));
    }
}
