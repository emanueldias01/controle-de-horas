package br.com.emanuel.controle_de_horas.controller;

import br.com.emanuel.controle_de_horas.dto.ProjetoRequestDTO;
import br.com.emanuel.controle_de_horas.service.ProjetoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    ProjetoService service;

    @GetMapping
    public ResponseEntity getAllProjetos(){
        return ResponseEntity.ok(service.getAllProjetos());
    }

    @GetMapping("/{nome}")
    public ResponseEntity getProjetoByNome(@PathVariable String nome){
        return ResponseEntity.ok(service.getProjetoByNome(nome));
    }

    @PostMapping
    @Transactional
    public ResponseEntity createProject(@RequestBody ProjetoRequestDTO data){
        return ResponseEntity.ok(service.createProject(data));
    }

    @PutMapping("/{nome}")
    @Transactional
    public ResponseEntity finalizarProjeto(@PathVariable String nome){
        return ResponseEntity.ok(service.terminarProjeto(nome));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProject(@PathVariable Long id){
        service.apagarProjeto(id);
        return ResponseEntity.noContent().build();
    }
}
