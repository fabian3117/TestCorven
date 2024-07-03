package com.vof.user.enums;

public enum TipoSexo {
    HOMBRE("HOMBRE"),
    MUJER("MUJER"),
    OTRO("OTRO");
    private final String valor;

    TipoSexo(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
