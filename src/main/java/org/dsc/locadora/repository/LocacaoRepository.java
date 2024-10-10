package org.dsc.locadora.repository;

import org.dsc.locadora.models.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    List<Locacao> findByClienteId(Long clienteId);
    List<Locacao> findByVeiculoId(Long veiculoId);
}
