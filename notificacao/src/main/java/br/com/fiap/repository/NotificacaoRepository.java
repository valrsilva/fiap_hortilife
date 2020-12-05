package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.Notificacao;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long>{
	
}
