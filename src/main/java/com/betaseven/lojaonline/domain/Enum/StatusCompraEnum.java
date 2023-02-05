package com.betaseven.lojaonline.domain.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCompraEnum {
    PENDENTE("Pendente"),
    EMPROCESSAMENTO("EmProcessamento"),
    REALIZADA("Realizada"),
    NEGADA("Negada"),
    CANCELADA("Cancelada");

    private final String status;
}
