package com.vof.user.model;

import com.vof.user.controller.dto.DatContactDTO;
import com.vof.user.controller.dto.DatosBaseDTO;
import com.vof.user.enums.TypeDoc;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class PersonEntity extends DatosBaseDTO {
    @Id
    private String id;

    @NonNull
    @Min(18)    //--> Edad minima para ingreso  <--
    private long edad;
    @Enumerated(EnumType.STRING)
    private TypeDoc documento;
    @NonNull
    @ElementCollection
    @CollectionTable(name = "dat_contact", joinColumns = @JoinColumn(name = "dat_contact_id"))
    @Schema(description = "Contactos referencias")
    private List<DatContactDTO> contactosReferencia;

}
