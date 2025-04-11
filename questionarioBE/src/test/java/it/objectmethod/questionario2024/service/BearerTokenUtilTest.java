package it.objectmethod.questionario2024.service;

import it.objectmethod.questionario2024.dto.AuthDto;
import it.objectmethod.questionario2024.utils.BearerTokenUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;

@MockitoSettings
class BearerTokenUtilTest {

    @InjectMocks
    private BearerTokenUtil bearerTokenUtil;
    private final static AuthDto request = AuthDto.builder()
            .nome("Jacopo")
            .email("jacopo-email")
            .build();

    @Test
    void shouldGenerateToken_whenAuthRequestIsValid() {
        // ARRANGE
        final String subjectExpected = "jacopo-email";
        final String nomeExpected = "Jacopo";

        // ACT
        final String tokenResponse = bearerTokenUtil.generateToken(request);
        final String fullToken = "Bearer " + tokenResponse;
        final String subject = bearerTokenUtil.getSubject(fullToken);
        final String nome = bearerTokenUtil.getNome(fullToken);
        //ASSERT
        assertThat(subjectExpected)
                .isEqualTo(subject);
        assertThat(nomeExpected)
                .isEqualTo(nome);
    }
}