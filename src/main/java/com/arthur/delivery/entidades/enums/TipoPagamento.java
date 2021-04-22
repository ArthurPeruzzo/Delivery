package com.arthur.delivery.entidades.enums;

public enum TipoPagamento {
    DINHEIRO(1),
    CARTAO(2);

    private int codigo; //codigo do tipo enumerado

    TipoPagamento(int codigo) {
        this.codigo = codigo;
    }

    public int getCode() {
        return codigo;
    }

    public static TipoPagamento valor(int codigo) { //metodo que retorna um TipoPagamento a partir do codigo;

        for(TipoPagamento value : TipoPagamento.values()) {//percorre todos os valores
            if(value.getCode() == codigo) {
                return value;
            }
        }
        throw new IllegalArgumentException("invalid orderStatus code!");
    }
}
