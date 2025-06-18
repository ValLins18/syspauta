package com.example.syspauta.syspauta.Model;

import com.example.syspauta.syspauta.Model.Enums.StatusPauta;
import jakarta.persistence.*;

@Entity
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private StatusPauta statusPauta;

    public Pauta (){}

    public Pauta(Long id, String nome, String descricao, StatusPauta statusPauta) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.statusPauta = statusPauta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusPauta getStatusPauta() {
        return statusPauta;
    }

    public void setStatusPauta(StatusPauta statusPauta) {
        this.statusPauta = statusPauta;
    }

}
