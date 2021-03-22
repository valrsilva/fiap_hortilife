package br.com.fiap.handler;

public class BancoDadosException extends RuntimeException{
    public BancoDadosException(String msg, Throwable err){ super(msg, err);}
}
