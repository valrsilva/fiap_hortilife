package br.com.fiap.model;

public class LoginResponseModel {
	
	private long idUsuario;
    private String mensagem;
    
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
    
    
}
