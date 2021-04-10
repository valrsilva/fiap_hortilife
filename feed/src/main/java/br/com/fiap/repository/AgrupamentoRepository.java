package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.Agrupamento;

@Repository
public interface AgrupamentoRepository extends JpaRepository<Agrupamento, Long>{

}
