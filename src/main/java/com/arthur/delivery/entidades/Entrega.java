package com.arthur.delivery.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_entrega")
public class Entrega implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date horarioSaida;
    private Date horarioEntrega;
    private Integer avaliacao;

    @ManyToOne
    @JoinColumn(name = "Entregador_id")//fala qual vai ser o nome da chave estrangeira
    private Entregador entregador;

    public Entrega(){
    }

    public Entrega(Long id, Date horarioSaida, Date horarioEntrega, Integer avaliacao, Entregador entregador) {
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

    public Date getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(Date horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public Date getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(Date horarioEntrega) {
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
