package com.example.syspauta.syspauta.UI.Layout.Components;

public class BotaoOkLayout {

    private String texto;
    private String url;
    private String body;

    public BotaoOkLayout () { }

    public BotaoOkLayout(String texto, String url, String body) {
        this.texto = texto;
        this.url = url;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
