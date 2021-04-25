package com.arthur.delivery.entidades;

import com.arthur.delivery.entidades.enums.TipoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime horaPedido;
    private Double valorEntrega;
    private TipoPagamento TipoPagamento;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")//fala qual vai ser o nome da chave estrangeira
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "cliente_id")//fala qual vai ser o nome da chave estrangeira
    private Cliente cliente;

    @OneToMany(mappedBy = "id.pedido")
    private List<PedidoItem> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "Entrega_id")//fala qual vai ser o nome da chave estrangeira
    private Entrega entrega;

    public Pedido(){
    }

    public Pedido(Long id, LocalDateTime horaPedido, Double valorEntrega, TipoPagamento tipoPagamento, Entrega entrega, Cliente cliente, Restaurante restaurante) {
        this.id = id;
        this.horaPedido = horaPedido;
        this.valorEntrega = valorEntrega;
        TipoPagamento = tipoPagamento;
        this.entrega = entrega;
        this.cliente = cliente;
        this.restaurante = restaurante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(LocalDateTime horaPedido) {
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
        return entrega;
    }

    public void setEntregas(Entrega entregas) {
        this.entrega = entregas;
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

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Double getTotal(){ //tem que colocar o get para aparecer o metodo
        double soma =0.0;
        for(PedidoItem x : itens){
            soma += x.getSubTotal();
        }
        return soma + valorEntrega;
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
