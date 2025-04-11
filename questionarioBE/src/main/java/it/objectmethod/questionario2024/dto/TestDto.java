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
public class TestDto {
    private Long testId;
    private String nomeTest;
    @Min(value = 0, message = "La durata non può  essere negativa")
    private Integer durataMinuti;
}
