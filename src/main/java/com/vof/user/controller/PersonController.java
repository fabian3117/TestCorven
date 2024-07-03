package com.vof.user.controller;

import com.vof.user.controller.dto.ResponseDTOP;
import com.vof.user.enums.Vinculo;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
@Tag(name = "Persona", description = "Controlador Endpoint de persona N1 - N2")
@RequiredArgsConstructor
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @Operation(
            summary = "Obtener todas las personas",
            description = "Obtengo todos las personas guardadas con sus contactos asociados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = General.SuccessOperationString, description = "Operation successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonEntity.class)))),
            @ApiResponse(responseCode = General.ErrorOperationString, description = "Procurament not found or Error")
    })
    @GetMapping("/persona")
    private List<PersonEntity> getAllPerson() {
        try {
            return personRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Operation(
            summary = "Guardar nueva persona",
            description = "Guardar una nueva persona requisito indispensable como minimo 1 familiar"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = General.SuccessOperationString, description = "Operation successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonEntity.class)))),
            @ApiResponse(responseCode = General.ErrorOperationString, description = "Procurament not found or Error")
    })
    @PostMapping("/persona")
    private ResponseDTOP savePerson(@RequestBody PersonEntity person) {

        try {
            return new ResponseDTOP(General.SuccessOperationString, General.SuccessOperationCode, personRepository.SaveModificado(person));
        } catch (Exception e) {
            return new ResponseDTOP(e.getMessage(), General.ErrorOperationCode, null);

        }
    }

    @Operation(
            summary = "Verifica relacion",
            description = "Verifica si las 2 personas son padre-hijo"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = General.SuccessOperationString, description = "Operation successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseDTOP.class)))),
            @ApiResponse(responseCode = General.ErrorOperationString, description = "Error via excepciones")
    })
    //--> TODO NIVEL 3  <---
    @PostMapping("/{id1}/padre/{id2}")
    private ResponseDTOP verificarVinculo(@PathVariable @NonNull String id1, @PathVariable @NonNull String id2) {
        try {
            PersonEntity posiblePadre = personRepository.findById(id1).orElseThrow(() -> new ExpressionException("Persona no existe en db"));
            boolean esPadre = posiblePadre.getContactosReferencia().stream().anyMatch(
                    vinculo -> vinculo.getRelacion().equals(Vinculo.PADRE) && vinculo.getId().equals(id2)
            );
            String mensaje = esPadre ? "VINCULO CORRECTO" : "NO VINCULO";
            long code = esPadre ? General.SuccessOperationCode : General.ErrorOperationCode;
            return new ResponseDTOP(mensaje, code, null);
        } catch (Exception e) {
            return new ResponseDTOP(e.getMessage(), General.ErrorOperationCode, null);
        }
    }
}
