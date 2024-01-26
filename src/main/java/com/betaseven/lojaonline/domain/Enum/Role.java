package com.betaseven.lojaonline.domain.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("admin"),
    USER("user");

    private final String role;

}
