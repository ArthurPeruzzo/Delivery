package com.arthur.delivery.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
    @Entity
    @Table(name = "tb_endereco")
    public class Endereco implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String rua;
        private String bairro;
        private String cidade;
        private String estado;


        public Endereco(){
        }

        public Endereco(Long id, String rua, String bairro, String cidade, String estado) {
            this.id = id;
            this.rua = rua;
            this.bairro = bairro;
            this.cidade = cidade;
            this.estado = estado;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getRua() {
            return rua;
        }

        public void setRua(String rua) {
            this.rua = rua;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Endereco endereco = (Endereco) o;
            return id.equals(endereco.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
}
