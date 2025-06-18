package com.example.syspauta.syspauta.Controller.UIController;

import com.example.syspauta.syspauta.Model.Resultado;
import com.example.syspauta.syspauta.Model.Sessao;
import com.example.syspauta.syspauta.Repository.PautaRepository;
import com.example.syspauta.syspauta.Repository.SessaoRepository;
import com.example.syspauta.syspauta.Repository.VotoRepository;
import com.example.syspauta.syspauta.UI.Layout.TelaLayout;
import com.example.syspauta.syspauta.UI.Views.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/forms")
@Tag(name = "Telas", description = "Endpoints para acessar telas via beckend-driven UI")
public class FormularioController {

    //Layouts
    private final AbrirSessaoView abrirSessaoView;
    private final CadastrarNovaPautaView cadastrarNovaPautaView;
    private final VotacaoView votacaoView;
    private final ConsultarResultadosView consultarResultadosView;
    private final ResultadoView resultadoView;
    private final IndexView indexView;
    private final SessoesAbertasParaVotarView sessoesAbertasParaVotarView;

    //repositories
    private final PautaRepository pautaRepository;
    private final SessaoRepository sessaoRepository;
    private final VotoRepository votoRepository;

    public FormularioController(AbrirSessaoView abrirSessaoView, CadastrarNovaPautaView cadastrarNovaPautaView, VotacaoView votacaoView,
                                ConsultarResultadosView consultarResultadosView, ResultadoView resultadoView, IndexView indexView, SessoesAbertasParaVotarView sessoesAbertasParaVotarView,
                                PautaRepository pautaRepository, SessaoRepository sessaoRepository, VotoRepository votoRepository) {
        //Layouts
        this.abrirSessaoView = abrirSessaoView;
        this.cadastrarNovaPautaView = cadastrarNovaPautaView;
        this.votacaoView = votacaoView;
        this.consultarResultadosView = consultarResultadosView;
        this.resultadoView = resultadoView;
        this.indexView = indexView;
        this.sessoesAbertasParaVotarView = sessoesAbertasParaVotarView;

        //repositories
        this.pautaRepository = pautaRepository;
        this.sessaoRepository = sessaoRepository;
        this.votoRepository = votoRepository;
    }

    @Operation(summary = "Tela Inicial", description = "Gera uma tela de menu inicial")
    @PostMapping("/index")
    public TelaLayout mostrarTelaIndex() {
        return indexView.gerarTelaCriarSelecaoOpcoes();
    }

    @Operation(summary = "Tela de Cadastro de nova Pauta", description = "Gera uma tela de formulario para que seja cadastrada uma nova pauta")
    @PostMapping("/nova-pauta")
    public TelaLayout mostrarTelaCadastroNovaPauta() {
        return cadastrarNovaPautaView.gerarTelaCriarNovaPauta();
    }

    @Operation(summary = "Tela de criar Sessão para uma Pauta", description = "Gera uma tela de seleção de Pautas para criar sessão")
    @PostMapping("/abrir-sessao")
    public TelaLayout mostrarTelaAbrirsessao() {

        var pautas = StreamSupport.stream(pautaRepository.findAll().spliterator(), false).toList();

        return abrirSessaoView.gerarTelaAbrirSessao(pautas);
    }

    @Operation(summary = "Tela de Para escolher sessão para votar", description = "Gera uma tela de seleção para escolher uma sessão para votar")
    @PostMapping("/sessoes")
    public TelaLayout mostrarTelaListaSessao() {
        var sessoes = StreamSupport.stream(sessaoRepository.findAll().spliterator(), false).toList();

        var sessoesAbertas = sessoes.stream().filter(Sessao::isSessaoAberta).toList();

        return sessoesAbertasParaVotarView.gerarTelaListarSessoes(sessoesAbertas);
    }

    @Operation(summary = "Tela para Votar", description = "Gera uma tela de formulario para computar um voto")
    @PostMapping("/votacao/{sessaoId}")
    public TelaLayout mostrarTelaVotacao(@PathVariable Long sessaoId) {
        var sessao = sessaoRepository.findById(sessaoId).orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        return votacaoView.gerarTelaVotacao(sessao);
    }

    @Operation(summary = "Tela de para consultar resultados", description = "Gera uma tela de seleção para escolher uma sessão para visualizar o resultado")
    @PostMapping("/resultados")
    public TelaLayout mostrarTelaListaResultados() {
        var sessoes = StreamSupport.stream(sessaoRepository.findAll().spliterator(), false).toList();
        var sessoesFechadas = sessoes.stream().filter(sessao -> !sessao.isSessaoAberta()).toList();

        return consultarResultadosView.gerarTelaListarResultados(sessoesFechadas);
    }

    @Operation(summary = "Tela de visualização do resultado", description = "Gera uma tela para visualizar o resultado")
    @PostMapping("/resultado/{sessaoId}")
    public TelaLayout mostrarTelaResultado(@PathVariable Long sessaoId) {
        var sessao = sessaoRepository.findById(sessaoId).orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        var totalSim = votoRepository.countBySessaoIdAndVoto(sessaoId, "SIM");
        var totalNao = votoRepository.countBySessaoIdAndVoto(sessaoId, "NAO");

        var resultado = new Resultado(totalSim,totalNao, sessao);
        return resultadoView.gerarTelaResultado(resultado);
    }
}
