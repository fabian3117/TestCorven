package com.vof.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatosDTO {
    private long cantidadMujeres;
    private long cantidadHombres;
    private long porcentajeArgentinos;
}
