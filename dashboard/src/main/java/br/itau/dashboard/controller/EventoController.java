package br.itau.dashboard.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ParseException;
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

        List<Evento> eventos = repo.findByDataevtBetween(ini, fim);

        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/datapg")
    public ResponseEntity<Page<Evento>> buscarPorDataPg(@RequestBody ObjectNode json) throws ParseException {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate ini = LocalDate.parse(json.get("dt1").asText(), fmt);
        LocalDate fim = LocalDate.parse(json.get("dt2").asText(), fmt);

        Pageable pageable = PageRequest.of(json.get("pg").asInt(), 10);

        Page<Evento> eventos = repo.findByDataevtBetweenOrderByDataevt(ini, fim, pageable);

        return ResponseEntity.ok(eventos);
    }
}