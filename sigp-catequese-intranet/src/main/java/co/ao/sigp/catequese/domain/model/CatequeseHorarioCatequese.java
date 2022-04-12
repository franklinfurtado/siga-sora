/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_horario_catequese")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseHorarioCatequese implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "dia_semana")
	@Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o dia da semana")
    private DiaSemana diaSemana;
    
    @Basic(optional = false)
    @NotNull(message = "Informe a hora de início")
    @Column(name = "hora_inicio")
    private LocalTime horaInicio;
    
    @Basic(optional = false)
    @NotNull(message = "Informe a hora de fim")
    @Column(name = "hora_termino")
    private LocalTime horaTermino;
    
    @Basic(optional = false)
    @Column(name = "nome_periodo")  
    @NotBlank(message = "Informe o período")
    private String nomePeriodo;
    
    public boolean isNovoRegisto() {
    	return this.id == null;
    }
    
    public String mostrarHorario() {
    	
    	return String.format("%s - %s, desde %s até %s", nomePeriodo, diaSemana.getValue(), formatarHora(horaInicio), formatarHora(horaTermino));
    }

	public boolean isHoraFimInferiorOuIgualInicial() {
		
		return horaTermino.isBefore(horaInicio) || (horaInicio.compareTo(horaTermino) == 0);
	}
	
	public String formatarHora(LocalTime hora) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		return hora.format(formatter);
	}
}
