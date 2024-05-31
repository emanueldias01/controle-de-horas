package br.com.emanuel.controle_de_horas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO trataProjetoNotFound(ProjetoNotFoundException ex){
        return new ErrorDTO(
                ex.getLancamento(),
                ex.getMessage()

        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO trataProjetoExistException(ProjetoExistException ex){
        return new ErrorDTO(
                ex.getLancamento(),
                ex.getMessage()
        );
    }
}
