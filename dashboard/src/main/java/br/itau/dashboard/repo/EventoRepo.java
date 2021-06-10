package br.itau.dashboard.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.itau.dashboard.model.Evento;

public interface EventoRepo extends CrudRepository<Evento, Integer>{
    public List<Evento> findByDataevtBetween(LocalDate iniDate, LocalDate finalDate);
    public List<Evento> findByDataevtBetweenOrderByDataevt (LocalDate iniDate, LocalDate finalDate);
}