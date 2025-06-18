package com.example.syspauta.syspauta.Repository;

import com.example.syspauta.syspauta.Model.Pauta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long> {}
