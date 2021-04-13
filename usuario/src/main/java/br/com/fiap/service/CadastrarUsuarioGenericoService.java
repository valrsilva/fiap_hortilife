package br.com.fiap.service;

import br.com.fiap.handler.BancoDadosException;
import br.com.fiap.database.UsuarioGenerico;
import br.com.fiap.model.UsuarioGenericoModel;
import br.com.fiap.repository.UsuarioGenericoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class CadastrarUsuarioGenericoService {

    @Autowired
    UsuarioGenericoRepository usuarioGenericoRepository;

    public boolean execute(UsuarioGenericoModel dadosUsuarioGenerico){
        cadastraUsuarioGenerico(dadosUsuarioGenerico);
        return true;
    }

    private UsuarioGenericoModel cadastraUsuarioGenerico(UsuarioGenericoModel dadosUsuarioGenerico){
        try {
            usuarioGenericoRepository.save(converterParaEntity(dadosUsuarioGenerico));
        }catch (Exception ex){
            throw new BancoDadosException("Erro ao salvar usuário genérico no banco de dados.", ex);
        }
        return dadosUsuarioGenerico;
    }

    private UsuarioGenerico converterParaEntity(UsuarioGenericoModel dadosUsuarioGenerico) {
        Date now = new Date();
        return UsuarioGenerico.builder()
                .id(dadosUsuarioGenerico.getId())
                .nome(dadosUsuarioGenerico.getNome())
                .email(dadosUsuarioGenerico.getEmail())
                .login(dadosUsuarioGenerico.getEmail())
                .senha(dadosUsuarioGenerico.getSenha())
                .ativo(dadosUsuarioGenerico.isAtivo())
                .sobrenome(dadosUsuarioGenerico.getSobrenome())
                .genero(dadosUsuarioGenerico.getGenero())
                .dataNascimento(dadosUsuarioGenerico.getDataNascimento())
                .confirmarSenha(dadosUsuarioGenerico.getConfirmarSenha())
                .cpf(dadosUsuarioGenerico.getCpf())
                .build();
    }
}