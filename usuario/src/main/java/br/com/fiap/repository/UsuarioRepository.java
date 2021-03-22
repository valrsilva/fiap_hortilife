package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.database.UsuarioGenerico;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioGenerico, Long>{

}
