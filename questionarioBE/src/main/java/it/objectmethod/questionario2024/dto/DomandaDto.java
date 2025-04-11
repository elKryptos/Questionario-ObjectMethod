package it.objectmethod.questionario2024.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomandaDto {

    private Long domandaId;
    @NotBlank(message = "Il testo della domanda non può essere vuoto")
    @Size(max = 255, message = "Il testo della domanda non può superare i 255 caratteri")
    private String testoDomanda;
    private Integer punteggio;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RispostaDto> risposte;

}
