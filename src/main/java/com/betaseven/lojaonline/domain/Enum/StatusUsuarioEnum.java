package com.betaseven.lojaonline.domain.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusUsuarioEnum {

    DESABILITADO("Desabilitado"),
    HABILITADO("Habilitado");

    private final String status;

}
