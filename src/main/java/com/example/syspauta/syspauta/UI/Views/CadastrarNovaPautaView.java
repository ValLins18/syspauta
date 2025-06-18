package com.example.syspauta.syspauta.UI.Views;

import com.example.syspauta.syspauta.UI.Layout.Components.BotaoCancelarLayout;
import com.example.syspauta.syspauta.UI.Layout.Components.BotaoOkLayout;
import com.example.syspauta.syspauta.UI.Layout.Components.ItemLayout;
import com.example.syspauta.syspauta.UI.Layout.TelaLayout;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CadastrarNovaPautaView {

    private final Environment env;

    public CadastrarNovaPautaView(Environment env) {
        this.env = env;
    }

    public TelaLayout gerarTelaCriarNovaPauta() {
        return new TelaLayout(
                "FORMULARIO",
                "Cadastre Uma Nova Pauta",
                List.of(
                        new ItemLayout("INPUT_TEXTO","nomePauta", "Nome da Pauta", ""),
                        new ItemLayout("INPUT_TEXTO","descricaoPauta", "Descrição da Pauta", "")),
                new BotaoOkLayout(
                        "Cadastrar Pauta",
                        env.getProperty("my.api.url")+"v1/pauta/criar",
                        null),
                new BotaoCancelarLayout("Voltar", env.getProperty("my.forms.url")+"/index"));
    }
}
