package br.com.fiap.controller;

import br.com.fiap.database.UsuarioGenerico;
import br.com.fiap.model.*;
import br.com.fiap.service.*;
import com.github.seratch.jslack.api.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.repository.UsuarioGenericoRepository;

import java.util.Optional;

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

	@Autowired
	LoginService loginService;


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
	public ResponseEntity<?> redefiniSenhaUsuario(@RequestBody RedefinicaoSenhaModel dadosRedefinirSenhaModel) {
        RedefinicaoResponseModel redefinicaoSenhaModel = new RedefinicaoResponseModel();

        UsuarioGenerico usuarioGenerico = alterarSenhaService.validarUsuario(dadosRedefinirSenhaModel);
        if (usuarioGenerico == null) {
            redefinicaoSenhaModel.setMensagem("Usuário não encontrado");
            return new ResponseEntity<RedefinicaoResponseModel>(redefinicaoSenhaModel, HttpStatus.NOT_FOUND);
        }
        if (!alterarSenhaService.verificaSenhaRedefinicao(dadosRedefinirSenhaModel)) {
            redefinicaoSenhaModel.setMensagem("Senhas não conferem");
            return new ResponseEntity<RedefinicaoResponseModel>(redefinicaoSenhaModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        alterarSenhaService.redefinirSenha(usuarioGenerico, dadosRedefinirSenhaModel);
        redefinicaoSenhaModel.setMensagem("Senha redefinida");
        return new ResponseEntity<RedefinicaoResponseModel>(HttpStatus.OK);
    }


//        if (alterarSenhaService.execute(dadosRedefinirSenhaModel)) {
//            redefinicaoSenhaModel.setMensagem("Senha redefinida");
//            return new ResponseEntity<RedefinicaoResponseModel>(HttpStatus.OK);
//		}
//        redefinicaoSenhaModel.setMensagem("Senhas não conferem");
//        return new ResponseEntity<RedefinicaoResponseModel>(redefinicaoSenhaModel, HttpStatus.OK);

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody LoginModel dadosLogin){
		if (loginService.execute(dadosLogin)) {
			LoginResponseModel loginResponseModel = new LoginResponseModel();
			loginResponseModel.setMensagem("Login feito com sucesso");
			return new ResponseEntity<LoginResponseModel>(loginResponseModel, HttpStatus.OK);
		}
		return new ResponseEntity<LoginModel>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(path = "/buscaUsuario/{id}")
	public ResponseEntity<UsuarioGenerico> buscarUsuarioPorId(@PathVariable("id") long idLogin) {

		Optional<UsuarioGenerico> findOpt = usuarioGenericoRepository.findById(idLogin);

		if(findOpt.isPresent()) {
			return new ResponseEntity<UsuarioGenerico>(findOpt.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<UsuarioGenerico>(HttpStatus.NOT_FOUND);
		}
	}
}