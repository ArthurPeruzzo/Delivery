package com.arthur.delivery.entidades;

import com.arthur.delivery.entidades.pk.PedidoItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_pedidoItem")
public class PedidoItem implements Serializable {

    @EmbeddedId
    private PedidoItemPK id = new PedidoItemPK();
    private Integer quantidade;
    private Double preco;

    public PedidoItem(){
    }

    public PedidoItem(Item item, Pedido pedido, Integer quantidade, Double preco) {
        id.setItem(item);
        id.setPedido(pedido);
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Item getItem() {
        return id.getItem();
    }

    public void setItem(Item item) {

        id.setItem(item);
    }

    public Double getSubTotal(){
        return preco * quantidade;
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoItem that = (PedidoItem) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
