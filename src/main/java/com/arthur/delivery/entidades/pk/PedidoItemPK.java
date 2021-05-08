package com.arthur.delivery.entidades.pk;

import com.arthur.delivery.entidades.Item;
import com.arthur.delivery.entidades.Pedido;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable //possibilita que seja feita uma chave composta entre item e pedido
public class PedidoItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoItemPK that = (PedidoItemPK) o;
        return pedido.equals(that.pedido) && item.equals(that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, item);
    }
}
