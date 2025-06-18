package com.example.syspauta.syspauta.UI.Views;

import com.example.syspauta.syspauta.UI.Layout.Components.ItemLayout;
import com.example.syspauta.syspauta.UI.Layout.TelaLayout;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexView {

    private final Environment env;

    public IndexView(Environment env) {
        this.env = env;
    }

    public TelaLayout gerarTelaCriarSelecaoOpcoes() {
        return new TelaLayout(
                "SELECAO",
                "SELECIONE UMA OPÇÃO",
                List.of(
                        new ItemLayout("Cadastrar Nova Pauta", env.getProperty("my.forms.url")+"/nova-pauta", null),
                        new ItemLayout("Abrir Sessão de Votação", env.getProperty("my.forms.url")+"/abrir-sessao", null),
                        new ItemLayout("Votar", env.getProperty("my.forms.url")+"/sessoes", null),
                        new ItemLayout("Consultar Resultados", env.getProperty("my.forms.url")+"/resultados", null)),
                null,
                null);
    }
}
