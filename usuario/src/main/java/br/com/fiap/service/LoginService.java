package br.com.fiap.service;

import br.com.fiap.database.UsuarioGenerico;
import br.com.fiap.model.LoginModel;
import br.com.fiap.model.LoginResponseModel;
import br.com.fiap.repository.UsuarioGenericoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UsuarioGenericoRepository usuarioGenericoRepository;

    public boolean execute(LoginModel dadosLogin) {
        return verificarLogin(dadosLogin);
    }

    public boolean verificarLogin(LoginModel dadosLogin) {
        UsuarioGenerico dadosLoginSelect = usuarioGenericoRepository.selectPorLogin(dadosLogin.getLogin());
        if (dadosLoginSelect != null && dadosLogin.getSenha().equals(dadosLoginSelect.getSenha())){
            return true;
        }else {
            return false;
        }

    }
}
