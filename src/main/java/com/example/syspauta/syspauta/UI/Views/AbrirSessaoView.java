package com.example.syspauta.syspauta.UI.Views;

import com.example.syspauta.syspauta.Model.Pauta;
import com.example.syspauta.syspauta.UI.Layout.Components.ItemLayout;
import com.example.syspauta.syspauta.UI.Layout.TelaLayout;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AbrirSessaoView {

    private final Environment env;

    public AbrirSessaoView(Environment env) {
        this.env = env;
    }

    public TelaLayout gerarTelaAbrirSessao(List<Pauta> pautas) {

        //ObjectMapper om = new ObjectMapper();

        TelaLayout telaLayout = new TelaLayout(
                "SELECAO",
                "Escolha Uma Pauta Para Abrir a Sessão de Votação",
                null,
                null,
                null);

        List<ItemLayout> itensLayout = pautas.stream().map(pauta -> {

                return new ItemLayout(
                        pauta.getNome(),
                        env.getProperty("my.api.url")+"/abrirsessao",
                        Map.of("pautaId", pauta.getId())
                );
        }).toList();

        telaLayout.setItens(itensLayout);

        return telaLayout;
    }
}
