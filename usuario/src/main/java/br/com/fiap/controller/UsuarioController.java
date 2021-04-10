package br.com.fiap.controller;

import br.com.fiap.model.ConsumidorModel;
import br.com.fiap.model.ProdutorModel;
import br.com.fiap.model.RedefinicaoSenhaModel;
import br.com.fiap.model.UsuarioGenericoModel;
import br.com.fiap.service.AlterarSenhaService;
import br.com.fiap.service.CadastrarConsumidorService;
import br.com.fiap.service.CadastrarProdutorService;
import br.com.fiap.service.CadastrarUsuarioGenericoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.repository.UsuarioGenericoRepository;

@RestController
@CrossOrigin
@RefreshScope
public class UsuarioController {

	@Autowired
	UsuarioGenericoRepository usuarioGenericoRepository;

	@Autowired
	DiscoveryClient discoveryClient;

	@Value("${max-results:10}")
	private int maxResults;

	@Autowired
	CadastrarUsuarioGenericoService cadastrarUsuarioGenericoService;

	@Autowired
	CadastrarConsumidorService cadastrarConsumidorService;

	@Autowired
	CadastrarProdutorService cadastrarProdutorService;

	@Autowired
	AlterarSenhaService alterarSenhaService;

	@PostMapping(path = "/cadastrarUsuarioGenerico")
	public String cadastraUsuario(@RequestBody UsuarioGenericoModel dadosUsuarioGenerico) {
		if (cadastrarUsuarioGenericoService.execute(dadosUsuarioGenerico)) {
			return "ok";
		}
		return "nok";
	}

	@PostMapping(path = "/cadastrarConsumidor")
	public String cadastraConsumidor(@RequestBody ConsumidorModel dadosConsumidor) {
		if (cadastrarConsumidorService.execute(dadosConsumidor)) {
			return "ok";
		}
		return "nok";
	}

	@PostMapping(path = "/cadastrarProdutor")
	public String cadastraProdutor(@RequestBody ProdutorModel dadosProdutor) {
		if (cadastrarProdutorService.execute(dadosProdutor)) {
			return "ok";
		}
		return "nok";
	}

	@PostMapping(path = "/redefinirSenha")
	public String redefiniSenhaUsuario(@RequestBody RedefinicaoSenhaModel dadosRedefinirSenhaModel) {
		if (alterarSenhaService.execute(dadosRedefinirSenhaModel)) {
			return "ok";
		}
		return "nok";
	}
}