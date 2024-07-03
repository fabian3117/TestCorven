package com.vof.user.repository;

import com.vof.user.model.PersonEntity;
import com.vof.user.utls.General;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String> {
    default PersonEntity SaveModificado(PersonEntity personEntity) {
        if (personEntity.getContactosReferencia() == null || personEntity.getContactosReferencia().isEmpty()) {
            throw new IllegalArgumentException("Debe tener al menos un contacto de referencia");
        }
        if (existsById(General.codificacion(personEntity))) {
            //--> Este metodo es solo para guardar no actualizar    <--
            throw new IllegalArgumentException("El registro ya existe");
        }
        personEntity.setId(General.codificacion(personEntity));
        personEntity.getContactosReferencia().forEach(contactoReferenciaEntity -> {
            //-> A fines de demostracion esta desglozado de esta manera <--
            String bufs = General.codificacion(personEntity.getDocumento().getValor()
                    , contactoReferenciaEntity.getDni()
                    , contactoReferenciaEntity.getPais()
                    , contactoReferenciaEntity.getSexo().toString());
            contactoReferenciaEntity.setId(bufs);
        });
        return save(personEntity);
    }

    boolean existsById(String id);

    long countBySexo(String sexo);

    long countAllByPais(String pais);


}
