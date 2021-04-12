package br.com.fiap.repository;

import br.com.fiap.database.UsuarioGenerico;
import br.com.fiap.database.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long>{

}
