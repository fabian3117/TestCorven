package com.vof.user.controller.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTOP {
    private String mensaje;
    private long code;
    private Object dato;
}
