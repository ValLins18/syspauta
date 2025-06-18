package com.example.syspauta.syspauta.Controller.RestController;

import com.example.syspauta.syspauta.Model.DTO.CriarSessaoDto;
import com.example.syspauta.syspauta.Model.Pauta;
import com.example.syspauta.syspauta.Model.Sessao;
import com.example.syspauta.syspauta.Repository.PautaRepository;
import com.example.syspauta.syspauta.Repository.SessaoRepository;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/sessao")
public class SessaoController {

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;
    private final Environment env;

    public SessaoController(SessaoRepository sessaoRepository, PautaRepository pautaRepository, Environment env) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.env = env;
    }

    @PostMapping("/criar-sessao")
    public ResponseEntity criarSessao(@RequestBody CriarSessaoDto criarSessaoDto) {
        try {
            var pauta = pautaRepository.findById(criarSessaoDto.pautaId());

            if (pauta.isPresent()) {
                var sessao = new Sessao(null, LocalDateTime.now(), pauta.get(), null);
                sessaoRepository.save(sessao);
                return ResponseEntity.created(new URI(env.getProperty("my.forms.url")+"/index")).build();
            }
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Não foi possivel abrir uma nova sessão para esta pauta, " +
                    "provavelmente ja foi aberta uma sessão para a mesma");
        }
    }
}

