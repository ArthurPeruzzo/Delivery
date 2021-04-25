package com.arthur.delivery.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_entrega")
public class Entrega implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime horarioSaida;
    private LocalDateTime horarioEntrega;
    private Integer avaliacao;

    @ManyToOne
    @JoinColumn(name = "Entregador_id")//fala qual vai ser o nome da chave estrangeira
    private Entregador entregador;

    @JsonIgnore
    @OneToMany(mappedBy = "entrega")
    private List<Pedido> pedidos = new ArrayList<>();

    public Entrega(){
    }

    public Entrega(Long id, LocalDateTime horarioSaida, LocalDateTime horarioEntrega, Integer avaliacao, Entregador entregador) {
        this.id = id;
        this.horarioSaida = horarioSaida;
        this.horarioEntrega = horarioEntrega;
        this.avaliacao = avaliacao;
        this.entregador = entregador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalDateTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public LocalDateTime getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(LocalDateTime horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrega entrega = (Entrega) o;
        return id.equals(entrega.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
