package com.example.syspauta.syspauta.UI.Views;

import com.example.syspauta.syspauta.Model.Resultado;
import com.example.syspauta.syspauta.UI.Layout.Components.BotaoCancelarLayout;
import com.example.syspauta.syspauta.UI.Layout.Components.ItemLayout;
import com.example.syspauta.syspauta.UI.Layout.TelaLayout;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultadoView {

    private final Environment env;

    public ResultadoView(Environment env) {
        this.env = env;
    }

    public TelaLayout gerarTelaResultado(Resultado resultado) {
        return new TelaLayout(
                "FORMULARIO",
                "Resultado Final da Pauta "+resultado.getSessao().getPauta().getNome(),
                List.of(
                        new ItemLayout("INPUT_TEXTO","qtdTotalVotos", "Quantidade Total de Votos", resultado.getQtdTotalVotos().toString()),
                        new ItemLayout("INPUT_TEXTO","qtdVotosSim", "Quantidade a Favor", resultado.getQtdVotosSim().toString()),
                        new ItemLayout("INPUT_TEXTO","qtdVotosNao", "Quantidade Contra", resultado.getQtdVotosNao().toString()),
                        new ItemLayout("INPUT_TEXTO","situacaoVotacao", "Situacao da Votacao", resultado.getSituacaoResultado().toString())),
                null,
                new BotaoCancelarLayout("Voltar", env.getProperty("my.forms.url")+"/index"));
    }
}
