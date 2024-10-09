package org.dsc.locadora.repository;


import org.dsc.locadora.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByModelo(String modelo);
    List<Veiculo> findByDisponibilidade(boolean disponibilidade);
}