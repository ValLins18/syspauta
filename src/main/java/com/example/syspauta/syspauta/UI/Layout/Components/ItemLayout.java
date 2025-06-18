package com.example.syspauta.syspauta.UI.Layout.Components;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemLayout {

    private String tipo;
    private String texto;
    private String id;
    private String titulo;
    private String valor;
    private String url;
    private Object body;

    public ItemLayout() {}

    public ItemLayout(String tipo, String texto, String id, String titulo, String valor) {
        this.tipo = tipo;
        this.texto = texto;
        this.id = id;
        this.titulo = titulo;
        this.valor = valor;
    }

    public ItemLayout(String texto, String url, Object body) {
        this.texto = texto;
        this.url = url;
        this.body = body;
    }

    public ItemLayout(String tipo, String texto) {
        this.tipo = tipo;
        this.texto = texto;
    }

    public ItemLayout(String tipo, String id, String titulo, String valor) {
        this.tipo = tipo;
        this.id = id;
        this.titulo = titulo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
