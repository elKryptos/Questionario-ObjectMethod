package it.objectmethod.questionario2024.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RispostaUtenteDto {
    private Long rispostaUtenteId;
    private Long rispostaId;
    private Long domandaId;
    private Long utenteTestId;
    @Min(value = 0, message = "Il punteggio non può  essere negativo")
    private Integer punteggioOttenuto;
}
