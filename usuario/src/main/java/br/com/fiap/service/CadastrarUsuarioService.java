package br.com.fiap.service;

import br.com.fiap.handler.BancoDadosException;
import br.com.fiap.database.UsuarioGenerico;
import br.com.fiap.model.UsuarioGenericoModel;
import br.com.fiap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public boolean execute(UsuarioGenericoModel dadosUsuarioGenerico){
        cadastraUsuario(dadosUsuarioGenerico);
        return true;
    }

    private UsuarioGenericoModel cadastraUsuario(UsuarioGenericoModel dadosUsuarioGenerico){
        try {
            usuarioRepository.save(converterParaEntity(dadosUsuarioGenerico));
        }catch (Exception ex){
            throw new BancoDadosException("Erro ao salvar usuário genérico no banco de dados.", ex);
        }
        return dadosUsuarioGenerico;
    }

    private UsuarioGenerico converterParaEntity(UsuarioGenericoModel dadosUsuarioGenerico){
        return UsuarioGenerico.builder()
                .id(dadosUsuarioGenerico.getId())
                .nome(dadosUsuarioGenerico.getNome())
                .email(dadosUsuarioGenerico.getEmail())
                .login(dadosUsuarioGenerico.getLogin())
                .senha(dadosUsuarioGenerico.getSenha())
                .perfil(dadosUsuarioGenerico.getPerfil())
                .ativo(dadosUsuarioGenerico.isAtivo())
                .dataInclusao(dadosUsuarioGenerico.getDataInclusao())
                .build();
    }
}