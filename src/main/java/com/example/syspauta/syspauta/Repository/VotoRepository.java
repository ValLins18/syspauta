package com.example.syspauta.syspauta.Repository;

import com.example.syspauta.syspauta.Model.Voto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {

    long countBySessaoIdAndVoto(Long sessaoId, String voto);

    boolean existsByCpfAssociadoAndSessaoId(String cpfAssociado, Long sessaoId);
}
