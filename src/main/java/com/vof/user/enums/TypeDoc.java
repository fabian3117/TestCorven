package com.vof.user.enums;

public enum TypeDoc {
    DNI("DNI"),
    PASSPORT("PASAPORTE");
    private final String valor;

    TypeDoc(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
