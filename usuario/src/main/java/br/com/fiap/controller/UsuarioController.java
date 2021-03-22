package br.com.fiap.controller;

import br.com.fiap.database.UsuarioGenerico;
import br.com.fiap.model.UsuarioGenericoModel;
import br.com.fiap.service.CadastrarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.repository.UsuarioRepository;

@RestController
@CrossOrigin
@RefreshScope
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	DiscoveryClient discoveryClient;

	@Value("${max-results:10}")
	private int maxResults;

	@Autowired
	CadastrarUsuarioService cadastrarUsuarioService;

	@PostMapping(path = "/cadastrarUsuarioGenerico")
	public String listAll(@RequestBody UsuarioGenericoModel dadosUsuarioGenerico) {
		if (cadastrarUsuarioService.execute(dadosUsuarioGenerico)){
			return "ok";
		}
		return "nok";
	}
}