package com.example.syspauta.syspauta.Repository;

import com.example.syspauta.syspauta.Model.Pauta;
import com.example.syspauta.syspauta.Model.Sessao;
import com.example.syspauta.syspauta.Model.Voto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VotoRepositoryTest {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private PautaRepository pautaRepository;

    private Sessao criarEsalvarSessao() {
        Pauta pauta = pautaRepository.save(new Pauta(null, "Pauta", "Descricao", null));
        Sessao sessao = new Sessao(null, LocalDateTime.now(), pauta, new ArrayList<>());
        return sessaoRepository.save(sessao);
    }

    @Test
    @DisplayName("DEVE SALVAR E RETORNAR VOTO")
    void testSaveAndFindById() {
        Sessao sessao = criarEsalvarSessao();
        Voto voto = new Voto(null, LocalDateTime.now(), "SIM", "12345678900", sessao);
        Voto votoSalvo = votoRepository.save(voto);
        assertNotNull(votoSalvo.getId());

        Optional<Voto> votoRetornado = votoRepository.findById(votoSalvo.getId());
        assertTrue(votoRetornado.isPresent());
        assertEquals("SIM", votoRetornado.get().getVoto());
    }

    @Test
    @DisplayName("DEVE ATUALIZAR VOTO")
    void testeAtualizar() {
        Sessao sessao = criarEsalvarSessao();
        Voto voto = new Voto(null, LocalDateTime.now(), "SIM", "12345678900", sessao);
        Voto votoSalvo = votoRepository.save(voto);
        votoSalvo.setVoto("NAO");
        votoRepository.save(votoSalvo);
        Optional<Voto> votoRetornado = votoRepository.findById(votoSalvo.getId());
        assertTrue(votoRetornado.isPresent());
        assertEquals("NAO", votoRetornado.get().getVoto());
    }

    @Test
    @DisplayName("DEVE DELETAR VOTO")
    void testDelete() {
        Sessao sessao = criarEsalvarSessao();
        Voto voto = new Voto(null, LocalDateTime.now(), "SIM", "12345678900", sessao);
        Voto saved = votoRepository.save(voto);
        Long id = saved.getId();
        votoRepository.deleteById(id);
        assertFalse(votoRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("DEVE CONTAR VOTOS POR SESSAOID")
    void testCountBySessaoIdAndVoto() {
        Sessao sessao = criarEsalvarSessao();
        votoRepository.save(new Voto(null, LocalDateTime.now(), "SIM", "111", sessao));
        votoRepository.save(new Voto(null, LocalDateTime.now(), "SIM", "222", sessao));
        votoRepository.save(new Voto(null, LocalDateTime.now(), "NAO", "333", sessao));
        long countSim = votoRepository.countBySessaoIdAndVoto(sessao.getId(), "SIM");
        long countNao = votoRepository.countBySessaoIdAndVoto(sessao.getId(), "NAO");
        assertEquals(2, countSim);
        assertEquals(1, countNao);
    }

    @Test
    @DisplayName("DEVE VERIFICAR SE O CPF JA VOTOU NA SESSAO")
    void testExistsByCpfAssociadoAndSessaoId() {
        Sessao sessao = criarEsalvarSessao();
        votoRepository.save(new Voto(null, LocalDateTime.now(), "SIM", "99999999999", sessao));
        assertTrue(votoRepository.existsByCpfAssociadoAndSessaoId("99999999999", sessao.getId()));
        assertFalse(votoRepository.existsByCpfAssociadoAndSessaoId("00000000000", sessao.getId()));
    }
}