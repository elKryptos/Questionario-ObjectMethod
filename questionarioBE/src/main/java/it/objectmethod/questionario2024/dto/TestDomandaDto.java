package it.objectmethod.questionario2024.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDomandaDto {
    private Long testDomandaId;
    private Long domandaId;
    @NotBlank(message = "Il nome del test non può essere vuoto")
    private String nomeTest;
    @Min(value = 0, message = "La durata del test non può essere negativa")
    private Integer durataMinuti;
    @NotBlank(message = "Il testo della domanda non può essere vuoto")
    private String testoDomanda;
    @NotBlank(message = "Il testo della risposta non può essere vuoto")
    private String testoRisposta;
    private Boolean corretta;
}
