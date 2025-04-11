package it.objectmethod.questionario2024.Integration;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import it.objectmethod.questionario2024.base.BaseIntegrationtest;
import it.objectmethod.questionario2024.dto.RispostaDto;
import it.objectmethod.questionario2024.specification.RispostaSpecification;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RispostaIntegrationTest extends BaseIntegrationtest {
    @Test
    void shouldReturnLibro_whenSearchBySpecification() {
        RispostaSpecification rispostaSpecification = new RispostaSpecification();
        rispostaSpecification.setCorretta(true);

        List<RispostaDto> expected = List.of(
                RispostaDto.builder()
                        .rispostaId(1L)
                        .domandaId(1L)
                        .testoRisposta("Roma")
                        .corretta(true)
                        .build(),
                RispostaDto.builder()
                        .rispostaId(6L)
                        .domandaId(2L)
                        .testoRisposta("Parigi")
                        .corretta(true)
                        .build(),
                RispostaDto.builder()
                        .rispostaId(9L)
                        .domandaId(3L)
                        .testoRisposta("Vero")
                        .corretta(true)
                        .build()
        );

        List<RispostaDto> actual = given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .queryParam("corretta", rispostaSpecification.getCorretta())
                .when()
                .get("/api/risposte/spec")
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

    static List<RispostaDto> fetchAllRisposta() {
        return List.of(
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
                        .build(),
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
                        .build(),
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
        );
    }
}
