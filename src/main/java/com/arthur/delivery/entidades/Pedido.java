package com.arthur.delivery.entidades;

import com.arthur.delivery.entidades.enums.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "Entrega_id")//fala qual vai ser o nome da chave estrangeira
    private Entrega entregas;

    @ManyToOne
    @JoinColumn(name = "cliente_id")//fala qual vai ser o nome da chave estrangeira
    private Cliente cliente;

    @OneToMany(mappedBy = "id.pedido")
    private List<PedidoItem> itens = new ArrayList<>();

    public Pedido(){
    }

    public Pedido(Long id, Date horaPedido, Double valorEntrega, TipoPagamento tipoPagamento, Entrega entregas, Cliente cliente) {
        this.id = id;
        this.horaPedido = horaPedido;
        this.valorEntrega = valorEntrega;
        TipoPagamento = tipoPagamento;
        this.entregas = entregas;
        this.cliente = cliente;
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

    public Entrega getEntregas() {
        return entregas;
    }

    public void setEntregas(Entrega entregas) {
        this.entregas = entregas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public Double getTotal(){ //tem que colocar o get para aparecer o metodo
        double soma =0.0;
        for(PedidoItem x : itens){
            soma += x.getSubTotal();
        }
        return soma;
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
