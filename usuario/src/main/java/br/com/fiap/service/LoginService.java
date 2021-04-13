package br.com.fiap.service;

import br.com.fiap.database.UsuarioGenerico;
import br.com.fiap.handler.BancoDadosException;
import br.com.fiap.model.LoginModel;
import br.com.fiap.repository.UsuarioGenericoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UsuarioGenericoRepository usuarioGenericoRepository;

    public boolean execute(LoginModel dadosLogin){
        verificarLogin(dadosLogin);
        return true;
    }

    public boolean verificarLogin(LoginModel dadosLogin){
        UsuarioGenerico dadosLoginSelect = usuarioGenericoRepository.selectPorLogin(dadosLogin.getLogin());
        if (dadosLoginSelect != null) {
            return true;
        }else{
            throw new BancoDadosException("Usuário não encontrado", new Exception());
        }
    }
}
