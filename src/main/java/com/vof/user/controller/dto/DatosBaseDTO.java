package com.vof.user.controller.dto;

import com.vof.user.enums.TipoSexo;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass
public class DatosBaseDTO {
    @NonNull
    private String telefono;
    @NonNull
    private String dni;
    @NonNull
    private String pais;
    @NonNull
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private TipoSexo sexo;
    @NonNull
    private String nombre;
}
