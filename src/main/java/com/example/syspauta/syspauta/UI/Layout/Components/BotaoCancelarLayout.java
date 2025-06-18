package com.example.syspauta.syspauta.UI.Layout.Components;

public class BotaoCancelarLayout {

    private String texto;
    private String url;

    public BotaoCancelarLayout () {}

    public BotaoCancelarLayout(String texto, String url) {
        this.texto = texto;
        this.url = url;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
