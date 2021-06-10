package br.itau.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itau.dashboard.model.Alarme;
import br.itau.dashboard.repo.AlarmeRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/alarme")

public class AlarmeController {
 
        @Autowired
    private AlarmeRepo repo;
    
    @GetMapping("/all")
    public List<Alarme> listarAlarme() {
        return (List<Alarme>) repo.findAll();
    }
}
