package it.objectmethod.questionario2024.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtenteTestDto {
    private Long utenteTestId;
    private Long testId;
    private Long utenteId;
    private Date dataInizio;
    private Date dataFine;
    @Min(value = 0, message = "Il punteggio totale non può essere negativo")
    private Integer punteggioTotale;
}
