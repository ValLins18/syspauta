package com.example.syspauta.syspauta.Repository;

import com.example.syspauta.syspauta.Model.Sessao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends CrudRepository<Sessao, Long> {}
