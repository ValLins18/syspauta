package com.example.syspauta.syspauta.Controller.RestController;

import com.example.syspauta.syspauta.Model.Pauta;
import com.example.syspauta.syspauta.Repository.PautaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/pauta")
@Tag(name = "Pauta", description = "Endpoints para gerenciamento de pautas")
public class PautaController {

    private final PautaRepository pautaRepository;
    private final Environment env;

    public PautaController(PautaRepository pautaRepository, Environment env) {
        this.pautaRepository = pautaRepository;
        this.env = env;
    }

    @Operation(summary = "Cria uma nova pauta", description = "Cadastra uma nova pauta no sistema")
    @PostMapping("/criar")
    public ResponseEntity<Pauta> criarPauta(@RequestBody Pauta pauta){
        try {
            pautaRepository.save(pauta);
            return ResponseEntity.created(new URI(env.getProperty("my.form.url")+"/index")).build();
        } catch ( Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/teste")
    public String teste() {
        return "teste";
    }
}
