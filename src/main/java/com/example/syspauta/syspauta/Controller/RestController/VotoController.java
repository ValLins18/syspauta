package com.example.syspauta.syspauta.Controller.RestController;

import com.example.syspauta.syspauta.Model.Voto;
import com.example.syspauta.syspauta.Repository.SessaoRepository;
import com.example.syspauta.syspauta.Repository.VotoRepository;
import com.example.syspauta.syspauta.Service.ValidadorCpfService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("api/v1/voto")
public class VotoController {

    private final SessaoRepository sessaoRepository;
    private final VotoRepository votoRepository;
    private final Environment env;
    private final ValidadorCpfService validadorCpfService;

    public VotoController(SessaoRepository sessaoRepository, VotoRepository votoRepository, Environment env, ValidadorCpfService validadorCpfService) {
        this.sessaoRepository = sessaoRepository;
        this.votoRepository = votoRepository;
        this.env = env;
        this.validadorCpfService = validadorCpfService;
    }

    @PostMapping("/votar/{sessaoId}")
    public ResponseEntity<?> votar(@RequestBody Voto voto, @PathVariable Long sessaoId) {

        //Verifica se o associado ja votou naquela pauta e sessão
        if(votoRepository.existsByCpfAssociadoAndSessaoId(voto.getCpfAssociado(), sessaoId)){
            return ResponseEntity.badRequest().body(Map.of("erro", "O associado com este cpf ja votou nesta pauta!!"));
        }

        //Verifica em uma API externa se o associado está apto para votar
        if(!validadorCpfService.podeVotar(voto.getCpfAssociado())) {
            return ResponseEntity.notFound().build();
        }

        var sessao = sessaoRepository.findById(sessaoId).orElseThrow(() -> new RuntimeException("Sessao não encontrada para fazer o voto"));

        //verifica se a sessão ainda está aberta para votar
        if(!sessao.isSessaoAberta()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            voto.setSessao(sessao);
            voto.setDataVoto(LocalDateTime.now());
            votoRepository.save(voto);
            return ResponseEntity.created(new URI(env.getProperty("my.forms.url")+"/index")).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
