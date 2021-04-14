package br.com.fiap.service;

import br.com.fiap.database.UsuarioGenerico;
import br.com.fiap.handler.BancoDadosException;
import br.com.fiap.model.RedefinicaoResponseModel;
import br.com.fiap.model.RedefinicaoSenhaModel;
import br.com.fiap.repository.UsuarioGenericoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarSenhaService {
    @Autowired
    UsuarioGenericoRepository usuarioGenericoRepository;
    RedefinicaoResponseModel redefinicaoSenhaModel = new RedefinicaoResponseModel();

//    public boolean execute(RedefinicaoSenhaModel dadosRedefinirSenha){
//        if (!verificaSenhaRedefinicao(dadosRedefinirSenha)){
//            redefinicaoSenhaModel.setMensagem("Senhas não conferem");
//            return false;
//        }
//        if(validarUsuario(dadosRedefinirSenha) == null){
//            redefinicaoSenhaModel.setMensagem("Usuário não encontrado");
//            return false;
//        }
//        redefinirSenha(validarUsuario(dadosRedefinirSenha), dadosRedefinirSenha);
//            return true;
//    }

    public boolean verificaSenhaRedefinicao(RedefinicaoSenhaModel dadosRedefinirSenha){
        if (dadosRedefinirSenha.getSenha().equals(dadosRedefinirSenha.getConfirmarSenha())){
            return true;
        }
        return false;
    }

    public void redefinirSenha(UsuarioGenerico dadosUsuario, RedefinicaoSenhaModel dadadosRedefinirSenha){
        try {
            usuarioGenericoRepository.save(converterParaEntity(dadosUsuario, dadadosRedefinirSenha));
        }catch (BancoDadosException ex){
            throw new BancoDadosException("Problema ao redefinir a senha", ex);
        }
    }

    public UsuarioGenerico validarUsuario(RedefinicaoSenhaModel dadosRedefinirSenha){
        return usuarioGenericoRepository.selectPorLogin(dadosRedefinirSenha.getLogin());
    }

    private UsuarioGenerico converterParaEntity(UsuarioGenerico dadosUsuario, RedefinicaoSenhaModel dadadosRedefinirSenha){
        dadosUsuario.setAtivo(dadosUsuario.isAtivo());
        dadosUsuario.setEmail(dadosUsuario.getEmail());
        dadosUsuario.setLogin(dadosUsuario.getLogin());
        dadosUsuario.setSenha(dadadosRedefinirSenha.getSenha());
        dadosUsuario.setNome(dadosUsuario.getNome());
        return dadosUsuario;
    }
}
