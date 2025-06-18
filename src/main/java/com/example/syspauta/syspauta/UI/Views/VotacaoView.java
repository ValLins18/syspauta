package com.example.syspauta.syspauta.UI.Views;

import com.example.syspauta.syspauta.Model.Pauta;
import com.example.syspauta.syspauta.Model.Sessao;
import com.example.syspauta.syspauta.UI.Layout.Components.BotaoCancelarLayout;
import com.example.syspauta.syspauta.UI.Layout.Components.BotaoOkLayout;
import com.example.syspauta.syspauta.UI.Layout.Components.ItemLayout;
import com.example.syspauta.syspauta.UI.Layout.TelaLayout;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.List;

/// @author Valdemilson Junior
///
/// Tela de formulario onde vai ser computado o voto
@Component
public class VotacaoView {

    private final Environment env;

    public VotacaoView(Environment env) {
        this.env = env;
    }

    public TelaLayout gerarTelaVotacao(Sessao sessao) {
        return new TelaLayout(
                "FORMULARIO",
                "Vote SIM ou NAO",
                List.of(
                        new ItemLayout("TEXTO","VOTE SIM OU NAO NA SEGUINTE PAUTA: "+sessao.getPauta().getDescricao()),
                        new ItemLayout("INPUT_TEXTO","cpfAssociado","CPF ASSOCIADO", ""),
                        new ItemLayout("INPUT_TEXTO","voto","DIGITE SIM OU NAO", "")
                ),
                new BotaoOkLayout("SIM", env.getProperty("my.api.url")+"/voto/votar/"+sessao.getId(), null),
                new BotaoCancelarLayout("NÃO", env.getProperty("my.form.url")+"/index"));
    }
}
