package br.com.emanuel.controle_de_horas.service;

import br.com.emanuel.controle_de_horas.dto.ProjetoRequestDTO;
import br.com.emanuel.controle_de_horas.dto.ProjetoResponseDTO;
import br.com.emanuel.controle_de_horas.exceptions.ProjetoNotFoundException;
import br.com.emanuel.controle_de_horas.model.Projeto;
import br.com.emanuel.controle_de_horas.repository.ProjetoRepository;
import br.com.emanuel.controle_de_horas.validadionEntity.ValidadionProjeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjetoService extends ValidadionProjeto {

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
        podeCadastrarProjeto(data);
        Projeto projetoSave = new Projeto(data);
        projetoSave.setDataInicio(LocalDateTime.now());
        repository.save(projetoSave);
        return "projeto criado com sucesso!";

    }

    public String terminarProjeto(String nome){
        var projetoOptional = repository.findByNomeProjeto(nome);
        if(projetoOptional.isPresent()){
            Projeto projeto = projetoOptional.get();
            projeto.setDataFim(LocalDateTime.now());
            calculaHorasTrabalhadasNoProjeto(projeto);
            return retornaInfoDeProjetoFinalizado(projeto);
        }
        else {
            throw new ProjetoNotFoundException("Projeto não encontrado");
        }
    }

    public void apagarProjeto(Long id){
        repository.deleteById(id);
    }
}
