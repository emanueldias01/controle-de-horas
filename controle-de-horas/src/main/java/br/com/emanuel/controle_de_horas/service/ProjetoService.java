package br.com.emanuel.controle_de_horas.service;

import br.com.emanuel.controle_de_horas.dto.ProjetoRequestDTO;
import br.com.emanuel.controle_de_horas.dto.ProjetoResponseDTO;
import br.com.emanuel.controle_de_horas.exceptions.ProjetoExistException;
import br.com.emanuel.controle_de_horas.exceptions.ProjetoNotFoundException;
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
        if(projeto.isPresent()){
            return new ProjetoResponseDTO(projeto.get());
        }
        else {
            throw new ProjetoNotFoundException("O projeto nao foi encontrado no banco de dados");
        }
    }

    public String createProject(ProjetoRequestDTO data){
        var projetoOptional = repository.findByNomeProjeto(data.getNomeProjeto());
        if(data.getNomeProjeto() != null){;
            if(projetoOptional.isEmpty()) {
                Projeto projetoSave = new Projeto(data);
                projetoSave.setDataInicio(LocalDateTime.now());
                repository.save(projetoSave);
                return "projeto criado com sucesso!";
            }else{
                throw new ProjetoExistException("O projeto que está tentando criar já existe");
            }
        }
        else{
            throw new RuntimeException("Inconsistência nos dados fornecidos");
        }
    }

    public String terminarProjeto(String nome){
        var projetoOptional = repository.findByNomeProjeto(nome);
        if(projetoOptional.isPresent()){
            Projeto projeto = projetoOptional.get();
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
                    Tempo de projeto: %s
                    """, projeto.getNomeProjeto(), projeto.getDataInicio(), projeto.getDataFim(), projeto.getHorasTrabalhadasNoProjeto());
        }
        else {
            throw new ProjetoNotFoundException("Projeto não encontrado");
        }
    }

    public void apagarProjeto(Long id){
        repository.deleteById(id);
    }
}
