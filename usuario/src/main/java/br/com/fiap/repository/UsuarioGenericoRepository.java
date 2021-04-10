package br.com.fiap.repository;

import br.com.fiap.model.RedefinicaoSenhaModel;
import br.com.fiap.model.UsuarioGenericoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.database.UsuarioGenerico;

@Repository
public interface UsuarioGenericoRepository extends JpaRepository<UsuarioGenerico, Long>{

    @Query(value = "select * from usuario_generico where login = :login", nativeQuery = true)
    UsuarioGenerico selectPorLogin(@Param("login") String login);

}
