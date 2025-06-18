package com.example.syspauta.syspauta.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataVoto;
    private String voto;
    private String cpfAssociado;

    @ManyToOne
    private Sessao sessao;

    public Voto() {}

    public Voto(Long id, LocalDateTime dataVoto, String voto, String cpfAssociado, Sessao sessao) {
        this.id = id;
        this.dataVoto = dataVoto;
        this.voto = voto;
        this.cpfAssociado = cpfAssociado;
        this.sessao = sessao;
    }

    public String getCpfAssociado() {
        return cpfAssociado;
    }

    public void setCpfAssociado(String cpfAssociado) {
        this.cpfAssociado = cpfAssociado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataVoto() {
        return dataVoto;
    }

    public void setDataVoto(LocalDateTime dataVoto) {
        this.dataVoto = dataVoto;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
}
