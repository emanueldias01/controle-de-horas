package br.com.emanuel.controle_de_horas.exceptions;

public class ProjetoExistException extends RuntimeException{
    private final String lancamento = "O projeto já existe";

    public ProjetoExistException(String message) {
        super(message);
    }

    public String getLancamento() {
        return lancamento;
    }
}
