package br.com.emanuel.controle_de_horas.dto;

import br.com.emanuel.controle_de_horas.model.Projeto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProjetoResponseDTO {
    private Long id;
    private String nomeProjeto;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String horasTrabalhadas;

    public ProjetoResponseDTO(Projeto projeto){
        this.id = projeto.getId();
        this.nomeProjeto = projeto.getNomeProjeto();
        this.dataInicio = projeto.getDataInicio();
        this.dataFim = projeto.getDataFim();
        this.horasTrabalhadas = projeto.getHorasTrabalhadasNoProjeto();
    }
}
