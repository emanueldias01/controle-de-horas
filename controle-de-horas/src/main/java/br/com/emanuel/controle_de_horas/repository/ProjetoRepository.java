package br.com.emanuel.controle_de_horas.repository;

import br.com.emanuel.controle_de_horas.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    Projeto findByNomeProjeto(String nome);
}
