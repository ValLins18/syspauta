package com.example.syspauta.syspauta.UI.Views;

import com.example.syspauta.syspauta.Model.Sessao;
import com.example.syspauta.syspauta.UI.Layout.Components.ItemLayout;
import com.example.syspauta.syspauta.UI.Layout.TelaLayout;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/// Terceira opção do menu da tela {@link IndexView}.
/// Nesta Tela é listadas todas as sessões que estão abertas para voto
@Component
public class SessoesAbertasParaVotarView {

    private final Environment env;

    public SessoesAbertasParaVotarView(Environment env) {
        this.env = env;
    }

    public TelaLayout gerarTelaListarSessoes(List<Sessao> sessoes) {

        ObjectMapper om = new ObjectMapper();

        TelaLayout telaLayout = new TelaLayout(
                "SELECAO",
                "Selecione uma Sessão Aberta para Votar",
                null,
                null,
                null);

        List<ItemLayout> itensLayout = sessoes.stream().map(sessao -> {

                return new ItemLayout(
                        sessao.getPauta().getNome(),
                        env.getProperty("my.forms.url")+"/votacao/"+sessao.getId(),
                        null
                );

        }).toList();

        telaLayout.setItens(itensLayout);

        return telaLayout;
    }
}
