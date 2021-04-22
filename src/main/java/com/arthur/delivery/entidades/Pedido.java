package com.arthur.delivery.entidades;

import com.arthur.delivery.entidades.enums.TipoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date horaPedido;
    private Double valorEntrega;
    private TipoPagamento TipoPagamento;

    public Pedido(){
    }

    public Pedido(Long id, Date horaPedido, Double valorEntrega, TipoPagamento tipoPagamento) {
        this.id = id;
        this.horaPedido = horaPedido;
        this.valorEntrega = valorEntrega;
        TipoPagamento = tipoPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(Date horaPedido) {
        this.horaPedido = horaPedido;
    }

    public Double getValorEntrega() {
        return valorEntrega;
    }

    public void setValorEntrega(Double valorEntrega) {
        this.valorEntrega = valorEntrega;
    }

    public TipoPagamento getTipoPagamento() {
        return TipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        TipoPagamento = tipoPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
