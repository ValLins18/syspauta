package com.example.syspauta.syspauta.Repository;

import com.example.syspauta.syspauta.Model.Enums.StatusPauta;
import com.example.syspauta.syspauta.Model.Pauta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PautaRepositoryTest {

    @Autowired
    private PautaRepository pautaRepository;

    @Test
    @DisplayName("DEVE SALVAR E RETORNAR A PAUTA")
    void testeSalvaERetornaPorId() {
        Pauta pauta = new Pauta(null, "Pauta 1", "Descricao 1", StatusPauta.ABERTA);
        Pauta pautaSalva = pautaRepository.save(pauta);
        assertNotNull(pautaSalva.getId());

        Optional<Pauta> PautaRetornada = pautaRepository.findById(pautaSalva.getId());
        assertTrue(PautaRetornada.isPresent());
        assertEquals("Pauta 1", PautaRetornada.get().getNome());
    }

    @Test
    @DisplayName("DEVE ATUALIZAR PAUTA")
    void testeAtualizar() {
        Pauta pauta = new Pauta(null, "Pauta 2", "Descricao 2", StatusPauta.ABERTA);
        Pauta pautaSalva = pautaRepository.save(pauta);
        pautaSalva.setNome("Pauta 2 atualizada");
        pautaRepository.save(pautaSalva);
        Optional<Pauta> PautaRetornada = pautaRepository.findById(pautaSalva.getId());
        assertTrue(PautaRetornada.isPresent());
        assertEquals("Pauta 2 atualizada", PautaRetornada.get().getNome());
    }

    @Test
    @DisplayName("DEVE DELETAR A PAUTA")
    void testeDeletar() {
        Pauta pauta = new Pauta(null, "Pauta 3", "Descricao 3", StatusPauta.ABERTA);
        Pauta pautaSalva = pautaRepository.save(pauta);
        Long id = pautaSalva.getId();
        pautaRepository.deleteById(id);
        assertFalse(pautaRepository.findById(id).isPresent());
    }
}