package com.betaseven.lojaonline.domain.dtos;

import com.betaseven.lojaonline.domain.Enum.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotEmpty
    @Size(min =5, max = 150)
    private String login;

    @NotEmpty
    @Size(min =5, max = 15)
    private String password;

    private Role role;
}
