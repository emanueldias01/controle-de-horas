package br.com.emanuel.controle_de_horas.service;

import br.com.emanuel.controle_de_horas.dto.ProjetoRequestDTO;
import br.com.emanuel.controle_de_horas.dto.ProjetoResponseDTO;
import br.com.emanuel.controle_de_horas.model.Projeto;
import br.com.emanuel.controle_de_horas.repository.ProjetoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    public List<ProjetoResponseDTO> getAllProjetos(){
        return repository.findAll().stream().map(ProjetoResponseDTO::new).toList();
    }

    public ProjetoResponseDTO getProjetoByNome(String nome){
        var projeto = repository.findByNomeProjeto(nome);
        if(projeto != null){
            return new ProjetoResponseDTO(projeto);
        }
        else {
            throw new EntityNotFoundException("projeto nao encontrado");
        }
    }

    public String createProject(ProjetoRequestDTO data){
        if(data.getNomeProjeto() != null){
            Projeto projetoSave = new Projeto(data);
            projetoSave.setDataInicio(LocalDateTime.now());
            repository.save(projetoSave);
            return "projeto criado com sucesso!";
        }
        else{
            throw new NullPointerException("o projeto está nulo");
        }
    }

    public String terminarProjeto(String nome){
        var projeto = repository.findByNomeProjeto(nome);
        if(projeto != null){
            projeto.setDataFim(LocalDateTime.now());
            var tempoDeProjeto = Duration.between(projeto.getDataInicio(), projeto.getDataFim());
            var horasDeProjeto = tempoDeProjeto.toHours();
            var minutosDeProjeto = tempoDeProjeto.toMinutes();
            var segundosDeProjeto = tempoDeProjeto.toSeconds();
            projeto.setHorasTrabalhadasNoProjeto(String.format("%dH/%dM/%dS", horasDeProjeto, minutosDeProjeto, segundosDeProjeto));
            return String.format("""
                    Projeto finalizado com sucesso! \n
                    Nome do projeto: %s \n
                    Data de inicio: %s \n
                    Data de finalizacao: %s \n
                    Horas de projeto: %d
                    """);
        }
        else {
            throw new EntityNotFoundException("Projeto não encontrado");
        }
    }

    public void apagarProjeto(Long id){
        repository.deleteById(id);
    }
}
