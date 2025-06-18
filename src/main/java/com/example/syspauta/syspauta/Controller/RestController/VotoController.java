package com.example.syspauta.syspauta.Controller.RestController;

import com.example.syspauta.syspauta.Model.Voto;
import com.example.syspauta.syspauta.Repository.SessaoRepository;
import com.example.syspauta.syspauta.Repository.VotoRepository;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/voto")
public class VotoController {

    private final SessaoRepository sessaoRepository;
    private final VotoRepository votoRepository;
    private final Environment env;

    public VotoController(SessaoRepository sessaoRepository, VotoRepository votoRepository, Environment env) {
        this.sessaoRepository = sessaoRepository;
        this.votoRepository = votoRepository;
        this.env = env;
    }

    @PostMapping("/votar/{sessaoId}")
    public ResponseEntity<Voto> votar(@RequestBody Voto voto, @PathVariable Long sessaoId) {

        //Verifica se o associado ja votou naquela pauta e sessão
        if(votoRepository.existsByCpfAssociadoAndSessaoId(voto.getCpfAssociado(), sessaoId)){
            return ResponseEntity.badRequest().build();
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
