package br.itau.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itau.dashboard.model.Usuario;
import br.itau.dashboard.repo.UsuarioRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepo repo;

    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable int id) {
        Usuario usuario = repo.findById(id).orElse(null);

        if(usuario != null) {
            usuario.setSenha("********");
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login (@RequestBody Usuario user) {
        Usuario userBd = repo.findByEmailOrRacf(user.getEmail(), user.getRacf());

        if(userBd != null) {
            if(userBd.getSenha().equals(user.getSenha())){
                return ResponseEntity.ok(userBd);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) repo.findAll();
    }
    
}
