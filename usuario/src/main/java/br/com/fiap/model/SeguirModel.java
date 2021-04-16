package br.com.fiap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class SeguirModel {
    private long idUsuario;
    private long idUsuarioSeguir;
}
