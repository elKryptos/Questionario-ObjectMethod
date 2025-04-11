package it.objectmethod.questionario2024.Integration;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import it.objectmethod.questionario2024.base.BaseIntegrationtest;
import it.objectmethod.questionario2024.dto.DomandaDto;
import it.objectmethod.questionario2024.dto.RispostaDto;
import it.objectmethod.questionario2024.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DomandaIntegrationTest extends BaseIntegrationtest {
    @Test
    void shouldReturnAllDomanda() {
        List<DomandaDto> allDomanda = fetchAllDomanda();
        ApiResponse<List<DomandaDto>> expected = ApiResponse.<List<DomandaDto>>builder()
                .message("Lista domande: ")
                .data(allDomanda)
                .statusCode(HttpStatus.OK)
                .build();

        ApiResponse<List<DomandaDto>> actual = given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/domande")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .prettyPeek()
                .body()
                .as(new TypeRef<>() {
                });
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);

    }

    static List<DomandaDto> fetchAllDomanda() {
        return List.of(
                DomandaDto.builder()
                        .domandaId(1L)
                        .testoDomanda("Qual è la capitale d'Italia?")
                        .punteggio(1)
                        .risposte(List.of(
                                RispostaDto.builder()
                                        .rispostaId(1L)
                                        .domandaId(1L)
                                        .testoRisposta("Roma")
                                        .corretta(true)
                                        .build(),
                                RispostaDto.builder()
                                        .rispostaId(2L)
                                        .domandaId(1L)
                                        .testoRisposta("Milano")
                                        .corretta(false)
                                        .build(),
                                RispostaDto.builder()
                                        .rispostaId(3L)
                                        .domandaId(1L)
                                        .testoRisposta("Napoli")
                                        .corretta(false)
                                        .build(),
                                RispostaDto.builder()
                                        .rispostaId(4L)
                                        .domandaId(1L)
                                        .testoRisposta("Firenze")
                                        .corretta(false)
                                        .build()
                        ))
                        .build(),
                DomandaDto.builder()
                        .domandaId(2L)
                        .testoDomanda("Qual è la capitale della Francia?")
                        .punteggio(1)
                        .risposte(List.of(
                                RispostaDto.builder()
                                        .rispostaId(5L)
                                        .domandaId(2L)
                                        .testoRisposta("Marsiglia")
                                        .corretta(false)
                                        .build(),
                                RispostaDto.builder()
                                        .rispostaId(6L)
                                        .domandaId(2L)
                                        .testoRisposta("Parigi")
                                        .corretta(true)
                                        .build(),
                                RispostaDto.builder()
                                        .rispostaId(7L)
                                        .domandaId(2L)
                                        .testoRisposta("Lione")
                                        .corretta(false)
                                        .build(),
                                RispostaDto.builder()
                                        .rispostaId(8L)
                                        .domandaId(2L)
                                        .testoRisposta("Bordeaux")
                                        .corretta(false)
                                        .build()
                        ))
                        .build(),
                DomandaDto.builder()
                        .domandaId(3L)
                        .testoDomanda("Babbo Natale esiste?")
                        .punteggio(1)
                        .risposte(List.of(
                                RispostaDto.builder()
                                        .rispostaId(9L)
                                        .domandaId(3L)
                                        .testoRisposta("Vero")
                                        .corretta(true)
                                        .build(),
                                RispostaDto.builder()
                                        .rispostaId(10L)
                                        .domandaId(3L)
                                        .testoRisposta("Falso")
                                        .corretta(false)
                                        .build()
                        ))
                        .build()
        );
    }
}
