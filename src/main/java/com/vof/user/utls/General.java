package com.vof.user.utls;

import com.vof.user.model.PersonEntity;

public class General {
    public static final String SuccessOperationString = "Operación exitosa";
    public static final int SuccessOperationCode = 200;
    public static final String ErrorOperationString = "Fallo";
    public static final int ErrorOperationCode = 500;


    public static String codificacion(String tipoDoc, String dni, String pais, String sexo) {
        return tipoDoc
                .concat(dni)
                .concat(pais)
                .concat(sexo);
    }

    public static String codificacion(PersonEntity personEntity) {
        return personEntity.getDocumento().getValor()
                .concat(personEntity.getDni())
                .concat(personEntity.getPais())
                .concat(personEntity.getSexo().toString());
    }

}
