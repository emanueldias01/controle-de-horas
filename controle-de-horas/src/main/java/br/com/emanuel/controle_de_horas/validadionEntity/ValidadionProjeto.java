package br.com.emanuel.controle_de_horas.validadionEntity;

import br.com.emanuel.controle_de_horas.dto.ProjetoRequestDTO;
import br.com.emanuel.controle_de_horas.exceptions.ProjetoExistException;
import br.com.emanuel.controle_de_horas.exceptions.ProjetoNotFoundException;
import br.com.emanuel.controle_de_horas.model.Projeto;
import br.com.emanuel.controle_de_horas.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

public class ValidadionProjeto {

    @Autowired
    private ProjetoRepository repository;
    protected void podeCadastrarProjeto(ProjetoRequestDTO data){
        var projetoOptional = repository.findByNomeProjeto(data.getNomeProjeto());
        if(data.getNomeProjeto() != null){
            if(projetoOptional.isEmpty()){}
            else{
                throw new ProjetoExistException("O projeto que está tentando criar já existe");
            }
        }else {
            throw new NullPointerException("nome do projeto está nulo");
        }
    }

    protected String retornaInfoDeProjetoFinalizado(Projeto projeto){
        return String.format("""
                    Projeto finalizado com sucesso! \n
                    Nome do projeto: %s \n
                    Data de inicio: %s \n
                    Data de finalizacao: %s \n
                    Tempo de projeto: %s
                    """, projeto.getNomeProjeto(), projeto.getDataInicio(), projeto.getDataFim(), projeto.getHorasTrabalhadasNoProjeto());

    }

    protected void calculaHorasTrabalhadasNoProjeto(Projeto projeto){
        var tempoDeProjeto = Duration.between(projeto.getDataInicio(), projeto.getDataFim());
        var horasDeProjeto = tempoDeProjeto.toHours();
        var minutosDeProjeto = tempoDeProjeto.toMinutes();
        var segundosDeProjeto = tempoDeProjeto.toSeconds();
        projeto.setHorasTrabalhadasNoProjeto(String.format("%dH/%dM/%dS", horasDeProjeto, minutosDeProjeto, segundosDeProjeto));
    }
}
