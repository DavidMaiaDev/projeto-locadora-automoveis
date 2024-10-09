package org.dsc.locadora.services;


import org.dsc.locadora.models.Reserva;
import org.dsc.locadora.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> listReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> getReserva(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva saveReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva updateReserva(Long id, Reserva reservaAtualizada) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(id);
        if (reservaOpt.isPresent()) {
            Reserva reservaExistente = reservaOpt.get();
            reservaExistente.setDataInicio(reservaAtualizada.getDataInicio());
            reservaExistente.setDataFim(reservaAtualizada.getDataFim());
            reservaExistente.setStatus(reservaAtualizada.getStatus());
            reservaExistente.setCliente(reservaAtualizada.getCliente());
            reservaExistente.setVeiculo(reservaAtualizada.getVeiculo());
            return reservaRepository.save(reservaExistente);
        }
        return null;
    }
    public void deleteReserva (Long id){
        reservaRepository.deleteById(id);
        }
    }
