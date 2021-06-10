package br.itau.dashboard.repo;

import org.springframework.data.repository.CrudRepository;

import br.itau.dashboard.model.Alarme;

public interface AlarmeRepo extends CrudRepository<Alarme, Integer> {
    
}
