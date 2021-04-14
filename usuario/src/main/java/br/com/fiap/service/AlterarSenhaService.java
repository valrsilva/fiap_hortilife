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

    public boolean execute(RedefinicaoSenhaModel dadosRedefinirSenha){
        if (!verificaSenhaRedefinicao(dadosRedefinirSenha)){
            RedefinicaoResponseModel redefinicaoSenhaModel = new RedefinicaoResponseModel();
            redefinicaoSenhaModel.setMensagem("Senhas não conferem");
            return false;
        }
        redefinirSenha(validarUsuario(dadosRedefinirSenha), dadosRedefinirSenha);
        return true;
    }

    public boolean verificaSenhaRedefinicao(RedefinicaoSenhaModel dadosRedefinirSenha){
        if (dadosRedefinirSenha.getSenha().equals(dadosRedefinirSenha.getConfirmarSenha())){
            return true;
        }
        return false;
    }

    private void redefinirSenha(UsuarioGenerico dadosUsuario, RedefinicaoSenhaModel dadadosRedefinirSenha){
        try {
            usuarioGenericoRepository.save(converterParaEntity(dadosUsuario, dadadosRedefinirSenha));
        }catch (BancoDadosException ex){
            throw new BancoDadosException("Problema ao redefinir a senha", ex);
        }
    }

    private UsuarioGenerico validarUsuario(RedefinicaoSenhaModel dadosRedefinirSenha){
        UsuarioGenerico dadosUsuario = usuarioGenericoRepository.selectPorLogin(dadosRedefinirSenha.getLogin());
        if (dadosUsuario != null) {
            return dadosUsuario;
        }else{
            throw new BancoDadosException("Usuário não encontrado", new Exception());
        }
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
