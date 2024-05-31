package br.com.emanuel.controle_de_horas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
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
    private Duration horasTrabalhadasNoProjeto;
}
