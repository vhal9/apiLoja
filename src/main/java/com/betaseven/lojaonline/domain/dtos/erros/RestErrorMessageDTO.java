package com.betaseven.lojaonline.domain.dtos.erros;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class RestErrorMessageDTO {
    private HttpStatus status;
    private List<String> messages;
}
