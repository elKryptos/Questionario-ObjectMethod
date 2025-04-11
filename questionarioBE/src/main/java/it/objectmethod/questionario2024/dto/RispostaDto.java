package it.objectmethod.questionario2024.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RispostaDto {

    private Long rispostaId;
    @NotBlank(message = "Il testo della risposta non pu  essere vuota")
    private String testoRisposta;
    private Boolean corretta;
    private Long domandaId;

}
