package br.com.emanuel.controle_de_horas.service;

import br.com.emanuel.controle_de_horas.model.Projeto;
import br.com.emanuel.controle_de_horas.repository.ProjetoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    public List<Projeto> getAllProjetos(){
        return repository.findAll().stream().toList();
    }

    public Projeto getProjetoByNome(String nome){
        var projeto = repository.findByNome(nome);
        if(projeto != null){
            return projeto;
        }
        else {
            throw new EntityNotFoundException("projeto nao encontrado");
        }
    }
}
