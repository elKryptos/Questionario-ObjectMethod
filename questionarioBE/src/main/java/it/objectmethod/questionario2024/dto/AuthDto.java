package it.objectmethod.questionario2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.objectmethod.questionario2024.utils.ValidationInterface;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class AuthDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(groups = ValidationInterface.SigninCase.class)
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(groups = ValidationInterface.LoginCase.class)
    @Email(message = "L'email deve essere conforme allo standard email")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(groups = ValidationInterface.LoginCase.class)
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Nullable
    private String response;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Nullable
    private Boolean isAdmin;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Nullable
    private Long utenteId;


}
