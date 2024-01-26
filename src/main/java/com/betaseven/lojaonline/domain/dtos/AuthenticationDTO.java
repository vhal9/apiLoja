package com.betaseven.lojaonline.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {

    @NotEmpty
    @Size(min =5, max = 150)
    private String login;

    @NotEmpty
    @Size(min =5, max = 15)
    private String password;
}
