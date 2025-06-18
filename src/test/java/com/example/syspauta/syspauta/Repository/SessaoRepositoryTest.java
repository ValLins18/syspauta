package com.example.syspauta.syspauta.Repository;

import com.example.syspauta.syspauta.Model.Pauta;
import com.example.syspauta.syspauta.Model.Sessao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SessaoRepositoryTest {

    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private PautaRepository pautaRepository;

    @Test
    @DisplayName("DEVE SALVAR E RETORNAR SESSAO")
    void testeSalvaERetorna() {
        Pauta pauta = pautaRepository.save(new Pauta(null, "Pauta", "Descricao", null));
        Sessao sessao = new Sessao(null, LocalDateTime.now(), pauta, new ArrayList<>());
        Sessao sessaoSalva = sessaoRepository.save(sessao);
        assertNotNull(sessaoSalva.getId());

        Optional<Sessao> sessaoRetornada = sessaoRepository.findById(sessaoSalva.getId());
        assertTrue(sessaoRetornada.isPresent());
        assertEquals(pauta.getId(), sessaoRetornada.get().getPauta().getId());
    }

    @Test
    @DisplayName("DEVE ATUALIZAR A SESSAO")
    void testAtualiza() {
        Pauta pauta = pautaRepository.save(new Pauta(null, "Pauta", "Descricao", null));
        Sessao sessao = new Sessao(null, LocalDateTime.now(), pauta, new ArrayList<>());
        Sessao sessaoSalva = sessaoRepository.save(sessao);
        sessaoSalva.setDuracaoMinutos(20);
        sessaoRepository.save(sessaoSalva);
        Optional<Sessao> sessaoRetornada = sessaoRepository.findById(sessaoSalva.getId());
        assertTrue(sessaoRetornada.isPresent());
        assertEquals(20, sessaoRetornada.get().getDuracaoMinutos());
    }

    @Test
    @DisplayName("DEVE DELETAR SESSAO")
    void testeDeletar() {
        Pauta pauta = pautaRepository.save(new Pauta(null, "Pauta", "Descricao", null));
        Sessao sessao = new Sessao(null, LocalDateTime.now(), pauta, new ArrayList<>());
        Sessao sessaoSalva = sessaoRepository.save(sessao);
        Long id = sessaoSalva.getId();
        sessaoRepository.deleteById(id);
        assertFalse(sessaoRepository.findById(id).isPresent());
    }
}