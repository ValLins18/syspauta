package com.example.syspauta.syspauta.UI.Layout;

import com.example.syspauta.syspauta.UI.Layout.Components.BotaoCancelarLayout;
import com.example.syspauta.syspauta.UI.Layout.Components.BotaoOkLayout;
import com.example.syspauta.syspauta.UI.Layout.Components.ItemLayout;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TelaLayout {
    private String tipo;
    private String titulo;
    private List<ItemLayout> itens;
    private BotaoOkLayout botaoOk;
    private BotaoCancelarLayout botaoCancelar;

    public TelaLayout() {}

    public TelaLayout(String tipo, String titulo, List<ItemLayout> itens, BotaoOkLayout botaoOk, BotaoCancelarLayout botaoCancelar) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.itens = itens;
        this.botaoOk = botaoOk;
        this.botaoCancelar = botaoCancelar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<ItemLayout> getItens() {
        return itens;
    }

    public void setItens(List<ItemLayout> itens) {
        this.itens = itens;
    }

    public BotaoOkLayout getBotaoOk() {
        return botaoOk;
    }

    public void setBotaoOk(BotaoOkLayout botaoOk) {
        this.botaoOk = botaoOk;
    }

    public BotaoCancelarLayout getBotaoCancelar() {
        return botaoCancelar;
    }

    public void setBotaoCancelar(BotaoCancelarLayout botaoCancelar) {
        this.botaoCancelar = botaoCancelar;
    }
}
