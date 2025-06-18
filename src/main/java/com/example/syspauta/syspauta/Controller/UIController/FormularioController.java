package com.example.syspauta.syspauta.Controller.UIController;

import com.example.syspauta.syspauta.Model.Resultado;
import com.example.syspauta.syspauta.Model.Sessao;
import com.example.syspauta.syspauta.Repository.PautaRepository;
import com.example.syspauta.syspauta.Repository.SessaoRepository;
import com.example.syspauta.syspauta.Repository.VotoRepository;
import com.example.syspauta.syspauta.UI.Layout.TelaLayout;
import com.example.syspauta.syspauta.UI.Views.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/forms")
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
    @PostMapping("/index")
    public TelaLayout mostrarTelaIndex() {
        return indexView.gerarTelaCriarSelecaoOpcoes();
    }

    @PostMapping("/nova-pauta")
    public TelaLayout mostrarTelaCadastroNovaPauta() {
        return cadastrarNovaPautaView.gerarTelaCriarNovaPauta();
    }

    @PostMapping("/abrir-sessao")
    public TelaLayout mostrarTelaAbrirsessao() {

        var pautas = StreamSupport.stream(pautaRepository.findAll().spliterator(), false).toList();

        return abrirSessaoView.gerarTelaAbrirSessao(pautas);
    }

    @PostMapping("/sessoes")
    public TelaLayout mostrarTelaListaSessao() {
        var sessoes = StreamSupport.stream(sessaoRepository.findAll().spliterator(), false).toList();

        var sessoesAbertas = sessoes.stream().filter(Sessao::isSessaoAberta).toList();

        return sessoesAbertasParaVotarView.gerarTelaListarSessoes(sessoesAbertas);
    }

    @PostMapping("/votacao/{sessaoId}")
    public TelaLayout mostrarTelaVotacao(@PathVariable Long sessaoId) {
        var sessao = sessaoRepository.findById(sessaoId).orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        return votacaoView.gerarTelaVotacao(sessao);
    }

    @PostMapping("/resultados")
    public TelaLayout mostrarTelaListaResultados() {
        var sessoes = StreamSupport.stream(sessaoRepository.findAll().spliterator(), false).toList();
        var sessoesFechadas = sessoes.stream().filter(sessao -> !sessao.isSessaoAberta()).toList();

        return consultarResultadosView.gerarTelaListarResultados(sessoesFechadas);
    }

    @PostMapping("/resultado/{sessaoId}")
    public TelaLayout mostrarTelaResultado(@PathVariable Long sessaoId) {
        var sessao = sessaoRepository.findById(sessaoId).orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        var totalSim = votoRepository.countBySessaoIdAndVoto(sessaoId, "SIM");
        var totalNao = votoRepository.countBySessaoIdAndVoto(sessaoId, "NAO");

        var resultado = new Resultado(totalSim,totalNao, sessao);
        return resultadoView.gerarTelaResultado(resultado);
    }
}
