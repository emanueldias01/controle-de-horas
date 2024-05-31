package br.com.emanuel.controle_de_horas.exceptions;

public class ProjetoNotFoundException extends RuntimeException{
    private final String lancamento = "Projeto não encontrado";

    public ProjetoNotFoundException(String message) {
        super(message);
    }

    public String getLancamento() {
        return lancamento;
    }
}
