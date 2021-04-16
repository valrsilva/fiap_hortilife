package br.com.fiap.controller;

import br.com.fiap.database.UsuarioGenerico;
import br.com.fiap.database.UsuarioSeguido;
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
import br.com.fiap.repository.UsuarioSeguidoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RefreshScope
public class UsuarioController {

	@Autowired
	UsuarioGenericoRepository usuarioGenericoRepository;

	@Autowired
	UsuarioSeguidoRepository usuarioSeguidoRepository;
	
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
	public ResponseEntity<LoginResponseModel> login(@RequestBody LoginModel dadosLogin){
		
		UsuarioGenerico usuario = usuarioGenericoRepository.findUsuarioByLoginAndSenha(dadosLogin.getLogin(), dadosLogin.getSenha());
		
		if (usuario != null) {
			LoginResponseModel loginResponseModel = new LoginResponseModel();
			loginResponseModel.setIdUsuario(usuario.getId());
			loginResponseModel.setMensagem("Login feito com sucesso");
			return new ResponseEntity<LoginResponseModel>(loginResponseModel, HttpStatus.OK);
		}
		
		return new ResponseEntity<LoginResponseModel>(HttpStatus.NOT_FOUND);
		
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
	
	@PostMapping(path = "/seguirUsuario")
	public ResponseEntity<UsuarioSeguido> seguirUsuario(@RequestBody SeguirModel seguirModel){
		
		Optional<UsuarioGenerico> usuarioGenerico = usuarioGenericoRepository.findById(seguirModel.getIdUsuario());
		Optional<UsuarioGenerico> usuarioGenericoSeguir = usuarioGenericoRepository.findById(seguirModel.getIdUsuarioSeguir());
		
		if(usuarioGenerico.isPresent() && usuarioGenericoSeguir.isPresent()) {
			
			UsuarioGenerico usuarioGenericoEntity = usuarioGenerico.get();
			
			UsuarioSeguido usuarioSeguido = usuarioSeguidoRepository.findUsuarioSeguidoByUsuarioIdAndUsuarioSeguidoId(usuarioGenericoEntity.getId(), usuarioGenericoSeguir.get().getId());
			
			if(usuarioSeguido == null ) {
				usuarioGenericoEntity.getUsuariosSeguidos().add(new UsuarioSeguido(0, usuarioGenericoEntity, usuarioGenericoSeguir.get()));
				usuarioGenericoRepository.save(usuarioGenericoEntity);
			}
			
			return new ResponseEntity<UsuarioSeguido>(usuarioSeguido, HttpStatus.OK);
			
		}
		
		return new ResponseEntity<UsuarioSeguido>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping(path = "/usuariosSeguidos/{id}")
	public ResponseEntity<List<UsuarioGenerico>> usuariosSeguidos(@PathVariable("id") long id) {

		List<UsuarioGenerico> listResult = new ArrayList<>();
		
		List<UsuarioSeguido> findUsuarioSeguidoByUsuarioId = usuarioSeguidoRepository.findUsuarioSeguidoByUsuarioId(id);
		
		if(findUsuarioSeguidoByUsuarioId != null ) {
			for(UsuarioSeguido userSeg : findUsuarioSeguidoByUsuarioId) {
				listResult.add(userSeg.getUsuarioSeguido());
			}
		}
		
		return new ResponseEntity<List<UsuarioGenerico>>(listResult, HttpStatus.OK);
		
	}
	
}