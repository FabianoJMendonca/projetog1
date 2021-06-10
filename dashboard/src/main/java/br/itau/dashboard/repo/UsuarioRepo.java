package br.itau.dashboard.repo;

import org.springframework.data.repository.CrudRepository;

import br.itau.dashboard.model.Usuario;

// CRUD = Create, Read, Udpate, Delete
public interface UsuarioRepo extends CrudRepository<Usuario, Integer> {
    public Usuario findByEmailAndSenha(String email, String senha);
    public Usuario findByRacfAndSenha(String racf, String senha);
    public Usuario findByEmailOrRacf(String email, String racf);
    
}
