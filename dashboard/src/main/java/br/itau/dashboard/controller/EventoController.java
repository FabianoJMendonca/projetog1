package br.itau.dashboard.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itau.dashboard.model.Evento;
import br.itau.dashboard.repo.EventoRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/evento")
public class EventoController {
    
    @Autowired
    private EventoRepo repo;

    @PostMapping("/data")
    public ResponseEntity<List<Evento>> buscarPorData(@RequestBody ObjectNode json) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate ini = LocalDate.parse(json.get("dt1").asText(), fmt);
        LocalDate fim = LocalDate.parse(json.get("dt2").asText(), fmt);

        List<Evento> eventos = repo.findByDataevtBetweenOrderByDataevt(ini, fim);

        return ResponseEntity.ok(eventos);
    }
}