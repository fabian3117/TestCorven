package com.vof.user.controller;

import com.vof.user.controller.dto.DatosDTO;
import com.vof.user.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadisticas")
@RequiredArgsConstructor
public class EstadisticasController {
    @Autowired
    private PersonRepository personRepository;
    private static final String SexoH = "H";
    private static final String SexoM = "M";

    @GetMapping("/")
    private ResponseEntity<DatosDTO> getEstadisticas() {
        try {
            long total = personRepository.count();
            long cantidadMujeres = personRepository.countBySexo(SexoM);
            long cantidadHombres = personRepository.countBySexo(SexoH);
            long cantidadArgentinos = personRepository.countAllByPais("Argentina");
            DatosDTO salida = DatosDTO.builder()
                    .cantidadMujeres(cantidadMujeres)
                    .cantidadHombres(cantidadHombres)
                    .porcentajeArgentinos(total / cantidadArgentinos).build();
            return ResponseEntity.ok(salida);
        } catch (Exception e) {
            DatosDTO respuesta = DatosDTO.builder()
                    .cantidadMujeres(0)
                    .cantidadHombres(0)
                    .porcentajeArgentinos(0).build();
            return ResponseEntity.badRequest().build();
        }

    }
}
