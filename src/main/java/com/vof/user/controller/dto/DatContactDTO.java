package com.vof.user.controller.dto;

import com.vof.user.enums.Vinculo;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Setter
@Getter
public class DatContactDTO extends DatosBaseDTO {
    private String id;
    private Vinculo relacion;
}
