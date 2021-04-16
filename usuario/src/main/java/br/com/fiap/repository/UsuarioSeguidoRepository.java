package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.database.UsuarioSeguido;

@Repository
public interface UsuarioSeguidoRepository extends JpaRepository<UsuarioSeguido, Long>{

    List<UsuarioSeguido> findUsuarioSeguidoByUsuarioId(@Param("id") long id);
    UsuarioSeguido findUsuarioSeguidoByUsuarioIdAndUsuarioSeguidoId(@Param("idUsuario") long id, @Param("idSeguido") long idSeguido);

}
