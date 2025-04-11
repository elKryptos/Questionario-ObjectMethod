package it.objectmethod.questionario2024.Integration.security;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import it.objectmethod.questionario2024.base.BaseIntegrationtest;
import it.objectmethod.questionario2024.dto.AuthDto;
import it.objectmethod.questionario2024.dto.UtenteDto;
import it.objectmethod.questionario2024.utils.BearerTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static it.objectmethod.questionario2024.Constants.ALREADY_SIGNIN;
import static it.objectmethod.questionario2024.Constants.SIGNIN_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SecurityIntegrationTest extends BaseIntegrationtest {

    @Autowired
    private BearerTokenUtil tokenUtil;
    private final static String RESPONSE = "Registrazione effettuata con successo";

    /*
     * Start signin test
     * */
    @Test
    void shouldUserSignin_whenNotExists() {
        final UtenteDto utente = UtenteDto.builder()
                .nomeUtente("Peppa Pig")
                .email("peppa.pig@gmail.com")
                .password("pippaPappi")
                .indirizzo("via da qui")
                .compleanno(Calendar.getInstance().getTime())
                .build();

        final AuthDto expected = AuthDto.builder().response(RESPONSE).build();
        final AuthDto actual = given()
                .port(this.port)
                .body(utente)
                .contentType(ContentType.JSON)
                .when()
                .post("/auth/signin")
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

    @ParameterizedTest
    @MethodSource("userProvider")
    void shouldUserNotSignin_whenAlreadyExists(UtenteDto utente) {
        final AuthDto expected = AuthDto.builder().response(ALREADY_SIGNIN.getMessage()).build();
        final AuthDto actual = given()
                .port(this.port)
                .body(utente)
                .contentType(ContentType.JSON)
                .when()
                .post("/auth/signin")
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

    // TODO: to implement
    @ParameterizedTest
    @CsvSource({
            ", mario@gamil.com, mario1234, via mario, 2000-12-01",
            "Mario, , mario1234, via mario, 2000-12-01",
            "Mario, mariogamil.com, mario1234, via mario, 2000-12-01",
            "Mario, mario@gamil.com, , via mario, 2000-12-01",
            "Mario, mario@gamil.com, mario, via mario, 2000-12-01",
            "Mario, mario@gamil.com, mario1234, , 2000-12-01"
    })
    void shouldNotSignin_whenCredentialsAreInvalid(final String nomeUtente,
                                                   final String email,
                                                   final String password,
                                                   final String indirizzo,
                                                   final String compleanno) throws ParseException {

        final AuthDto expected = AuthDto.builder().response(SIGNIN_ERROR.getMessage()).build();

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = simpleDateFormat.parse(compleanno);

        final UtenteDto utente = UtenteDto.builder()
                .nomeUtente(nomeUtente)
                .email(email)
                .password(password)
                .indirizzo(indirizzo)
                .compleanno(birthday)
                .build();

        final AuthDto actual = given()
                .port(this.port)
                .body(utente)
                .contentType(ContentType.JSON)
                .when()
                .post("/auth/signin")
                .then()
                .statusCode(404)
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

    /*
     * Start login test
     * */
    // TODO: to implement
    @ParameterizedTest
    @MethodSource("authProvider")
    void shouldLoginUser(final AuthDto utente) {

        final AuthDto token = given()
                .port(this.port)
                .body(utente)
                .contentType(ContentType.JSON)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .prettyPeek()
                .body()
                .as(new TypeRef<>() {
                });


        final String tokenResponse = token.getResponse();
        assertThat(tokenUtil.isTokenValid(tokenResponse))
                .isTrue();

        final String nomeClaim = tokenUtil.getNome(tokenResponse);
        final String emailClaim = tokenUtil.getSubject(tokenResponse);

        assertThat(nomeClaim).isEqualTo(utente.getNome());
        assertThat(emailClaim).isEqualTo(utente.getEmail());
    }

    // TODO: to implement
    @Test
    void shouldNotUserLogin_whenNotExists() {
    }

    @ParameterizedTest
    @CsvSource({
            "Mario, mario@gamil.com, mario1234",
            "Franco, franco@gamil.com, franco1234"
    })
    void shouldLoginUserCsv(String nome, String email, String password) {

        // Crea l'oggetto di input per il test
        final AuthDto utente = AuthDto.builder()
                .nome(nome)
                .email(email)
                .password(password)
                .build();

        // Effettua la richiesta e ottieni il token
        final AuthDto token = given()
                .port(this.port)
                .body(utente)
                .contentType(ContentType.JSON)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .prettyPeek()
                .body()
                .as(new TypeRef<>() {
                });

        // Ottieni la risposta del token
        final String tokenResponse = token.getResponse();
        assertThat(tokenUtil.isTokenValid(tokenResponse))
                .isTrue(); // Il token deve essere valido

        // Estrai i claims e verifica
        final String nomeClaim = tokenUtil.getNome(tokenResponse);
        final String emailClaim = tokenUtil.getSubject(tokenResponse);

        assertThat(nomeClaim).isEqualTo(nome); // Verifica il nome
        assertThat(emailClaim).isEqualTo(email); // Verifica l'email
    }

    /*
     * Start utils for tests
     * */

    private static Stream<AuthDto> authProvider() {
        return Stream.of(
                AuthDto.builder()
                        .nome("Mario")
                        .email("mario@gamil.com")
                        .password("mario1234")
                        .build(),
                AuthDto.builder()
                        .nome("Franco")
                        .email("franco@gamil.com")
                        .password("franco1234")
                        .build()
        );
    }

    private static Stream<UtenteDto> userProvider() {
        return Stream.of(
                UtenteDto.builder()
                        .nomeUtente("Gabriele")
                        .email("gabriele@gmail.it")
                        .password("passGabriele")
                        .compleanno(new Calendar.Builder().setDate(1998, 6, 26).build().getTime())
                        .indirizzo("Via Leonardo Da Vinci")
                        .build(),
                UtenteDto.builder()
                        .nomeUtente("Davide")
                        .email("davide@gmail.it")
                        .password("passDavide")
                        .compleanno(new Calendar.Builder().setDate(2000, 11, 20).build().getTime())
                        .indirizzo("Via Aldo Moro")
                        .build()
        );
    }
}
