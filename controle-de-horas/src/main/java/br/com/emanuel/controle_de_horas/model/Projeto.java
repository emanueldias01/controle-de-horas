package br.com.emanuel.controle_de_horas.model;

import br.com.emanuel.controle_de_horas.dto.ProjetoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "projetos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome_projeto")
    private String nomeProjeto;

    @Column(name = "data_de_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_de_fim")
    private LocalDateTime dataFim;

    @Column(name = "horas_trabalhadas")
    private String horasTrabalhadasNoProjeto;

    public Projeto(ProjetoRequestDTO data) {
        if(data.getNomeProjeto() != null){
            this.nomeProjeto = data.getNomeProjeto();
        }
    }
}
