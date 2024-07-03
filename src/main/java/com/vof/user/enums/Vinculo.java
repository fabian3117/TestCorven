package com.vof.user.enums;

public enum Vinculo {
    PADRE("PADRE"),
    HIJO("HIJO"),
    PRIMOS("PRIMOS"),
    HERMANOS("HERMANOS"),
    MATRIMONIO("MATRIMONIO"),
    OTROS("OTROS");
    private final String valor;

    Vinculo(String valor) {
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
