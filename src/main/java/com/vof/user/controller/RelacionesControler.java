package com.vof.user.controller;

import com.vof.user.controller.dto.DatContactDTO;
import com.vof.user.controller.dto.ResponseDTOP;
import com.vof.user.model.PersonEntity;
import com.vof.user.repository.PersonRepository;
import com.vof.user.utls.General;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/relaciones")
@RequiredArgsConstructor
@Tag(name = "Nivel 3", description = "Verificacion de relaciones")
public class RelacionesControler {
    @Autowired
    private PersonRepository personRepository;

    @Operation(
            summary = "Verificacion de vinculo entre 2 personas",
            description = "Verifica vinculo entre dos personas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = General.SuccessOperationString, description = "Operation successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseDTOP.class)))),
            @ApiResponse(responseCode = General.SuccessOperationString, description = "En caso de fallos responde por excepciones")
    })

    @GetMapping("/{id1}/{id2}")
    private ResponseDTOP VinculoVerificacion(@PathVariable @NonNull String id1, @PathVariable @NonNull String id2) {
        try {
            PersonEntity primera = personRepository.findById(id1).orElseThrow(() -> new ExpressionException("No se encontro persona primera"));
            Optional<DatContactDTO> relacion = primera.getContactosReferencia().stream().filter(pariente ->
                    pariente.getId().equals(id2)
            ).findFirst();
            String mensaje = relacion.map(datContactDTO -> "RELACION ENCONTRADA " + datContactDTO.getRelacion().toString()).orElse("NO RELACION");
            return new ResponseDTOP(mensaje, General.SuccessOperationCode, null);
        } catch (Exception e) {
            return new ResponseDTOP("ERROR", General.ErrorOperationCode, null);
        }
    }

}
