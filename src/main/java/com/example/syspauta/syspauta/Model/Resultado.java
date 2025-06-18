package com.example.syspauta.syspauta.Model;

import com.example.syspauta.syspauta.Model.Enums.SituacaoResultado;

public class Resultado {

    private Long qtdTotalVotos = 0L;
    private Long qtdVotosSim = 0L;
    private Long qtdVotosNao = 0L;

    private Sessao sessao;

    private SituacaoResultado situacaoResultado;

    public Resultado() {}

    public Resultado(Long qtdVotosSim, Long qtdVotosNao, Sessao sessao) {
        this.qtdTotalVotos = qtdVotosSim + qtdVotosNao;
        this.qtdVotosSim = qtdVotosSim;
        this.qtdVotosNao = qtdVotosNao;
        this.sessao = sessao;
        this.situacaoResultado = setSituacaoResultado(qtdVotosSim, qtdVotosNao);
    }

    private SituacaoResultado setSituacaoResultado(Long qtdVotosSim, Long qtdVotosNao) {
        if(qtdVotosSim > qtdVotosNao) return SituacaoResultado.APROVADA;
        else return SituacaoResultado.REJEITA;
    }

    public Long getQtdTotalVotos() {
        return qtdTotalVotos;
    }

    public void setQtdTotalVotos(Long qtdTotalVotos) {
        this.qtdTotalVotos = qtdTotalVotos;
    }

    public Long getQtdVotosSim() {
        return qtdVotosSim;
    }

    public void setQtdVotosSim(Long qtdVotosSim) {
        this.qtdVotosSim = qtdVotosSim;
    }

    public Long getQtdVotosNao() {
        return qtdVotosNao;
    }

    public void setQtdVotosNao(Long qtdVotosNao) {
        this.qtdVotosNao = qtdVotosNao;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public SituacaoResultado getSituacaoResultado() {
        return situacaoResultado;
    }

    public void setSituacaoResultado(SituacaoResultado situacaoResultado) {
        this.situacaoResultado = situacaoResultado;
    }
}
