package com.example.syspauta.syspauta.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataAberturaSessao;
    private LocalDateTime dataFechamentoSessao;
    private Integer duracaoMinutos = 1;

    @OneToOne
    private Pauta pauta;

    @OneToMany
    private List<Voto> votos;

    public Sessao () {}

    public Sessao(Long id, LocalDateTime dataAberturaSessao, Pauta pauta, List<Voto> votos) {
        this.id = id;
        this.dataAberturaSessao = dataAberturaSessao;
        this.dataFechamentoSessao = dataAberturaSessao.plusMinutes(getDuracaoMinutos());
        this.pauta = pauta;
        this.votos = votos;
    }

    public Sessao(Long id, LocalDateTime dataAberturaSessao, Integer duracaoMinutos, Pauta pauta, List<Voto> votos) {
        this.id = id;
        this.dataAberturaSessao = dataAberturaSessao;
        this.duracaoMinutos = duracaoMinutos;
        this.pauta = pauta;
        this.votos = votos;
    }

    public Sessao(Long id, LocalDateTime dataAberturaSessao, LocalDateTime dataFechamentoSessao, Integer duracaoMinutos, Pauta pauta, List<Voto> votos) {
        this.id = id;
        this.dataAberturaSessao = dataAberturaSessao;
        this.dataFechamentoSessao = dataFechamentoSessao;
        this.duracaoMinutos = duracaoMinutos;
        this.pauta = pauta;
        this.votos = votos;
    }

    /**
     * Verifica se a sessão está aberta no momento atual
     * @return true se a sessão estiver aberta, false caso contrário
     */
    public boolean isSessaoAberta() {
        return isSessaoAberta(LocalDateTime.now());
    }

    /**
     * Verifica se a sessão estava aberta em um momento específico
     * @param momento o momento a ser verificado
     * @return true se a sessão estava aberta no momento especificado
     */
    public boolean isSessaoAberta(LocalDateTime momento) {
        if (dataAberturaSessao == null) {
            return false; // Sessão nunca foi aberta
        }

        LocalDateTime fechamentoCalculado = dataAberturaSessao.plusMinutes(duracaoMinutos);
        return !momento.isBefore(dataAberturaSessao) && !momento.isAfter(fechamentoCalculado);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataAberturaSessao() {
        return dataAberturaSessao;
    }

    public void setDataAberturaSessao(LocalDateTime dataAberturaSessao) {
        this.dataAberturaSessao = dataAberturaSessao;
    }

    public LocalDateTime getDataFechamentoSessao() {
        return dataFechamentoSessao;
    }

    public void setDataFechamentoSessao(LocalDateTime dataFechamentoSessao) {
        this.dataFechamentoSessao = dataFechamentoSessao;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }
}
