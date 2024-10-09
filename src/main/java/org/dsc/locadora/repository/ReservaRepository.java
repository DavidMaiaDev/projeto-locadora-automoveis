package org.dsc.locadora.repository;

import org.dsc.locadora.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByClienteId(Long clienteId);
    List<Reserva> findByVeiculoId(Long veiculoId);
    List<Reserva> findByStatus(String status);
}
