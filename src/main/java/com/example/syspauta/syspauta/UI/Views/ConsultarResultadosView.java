package com.example.syspauta.syspauta.UI.Views;

import com.example.syspauta.syspauta.Model.Sessao;
import com.example.syspauta.syspauta.UI.Layout.Components.ItemLayout;
import com.example.syspauta.syspauta.UI.Layout.TelaLayout;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

/// @author Valdemilson Junior
///
/// Quarta opção do menu da tela {@link IndexView}.
/// Nesta Tela é listado os resultados das sessoes que ja foram encerradas
@Component
public class ConsultarResultadosView {

    private final Environment env;

    public ConsultarResultadosView(Environment env) {
        this.env = env;
    }

    public TelaLayout gerarTelaListarResultados(List<Sessao> sessoesFechadas) {

        ObjectMapper om = new ObjectMapper();

        TelaLayout telaLayout = new TelaLayout(
                "SELECAO",
                "Selecione um Resultado para Conferir",
                null,
                null,
                null);

        List<ItemLayout> itensLayout = sessoesFechadas.stream().map(sessaoFechada -> {

                return new ItemLayout(
                        sessaoFechada.getPauta().getNome(),
                        env.getProperty("my.forms.url")+"/resultado/"+sessaoFechada.getId(),
                        null);

        }).toList();

        telaLayout.setItens(itensLayout);

        return telaLayout;
    }
}
