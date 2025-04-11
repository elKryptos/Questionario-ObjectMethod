package it.objectmethod.questionario2024.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtenteDto {
    private Long utenteId;
    @NotBlank(message = "Il nome non può essere vuoto")
    private String nomeUtente;
    @NotBlank(message = "L'email non può essere vuota")
    @Email(message = "L'email deve essere conforme allo standard email")
    private String email;
    @NotBlank(message = "La password non può  essere vuota")
    @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date compleanno;
    @NotBlank
    private String indirizzo;
    
    private Boolean isAdmin;
}