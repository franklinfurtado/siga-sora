/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.repository;

import java.time.LocalTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author franklin.furtado
 */

import co.ao.sigp.catequese.domain.model.CatequeseHorarioCatequese;
import co.ao.sigp.catequese.domain.model.DiaSemana;
 
@Repository
public interface CatequeseHorarioRepository extends CustomJpaRepository<CatequeseHorarioCatequese, Integer>{
    
    @Query("SELECT CASE WHEN (COUNT(catequeseHorario) > 0) THEN true ELSE false END FROM CatequeseHorarioCatequese catequeseHorario WHERE catequeseHorario.diaSemana = :diaSemana and catequeseHorario.horaInicio = :horaInicio and  catequeseHorario.horaTermino = :horaTermino and  catequeseHorario.nomePeriodo = :nomePeriodo and catequeseHorario.id != :idHorario")
    public boolean horarioExisteNoSistema(@Param("idHorario") Integer idHorario, @Param("diaSemana") DiaSemana diaSemana, @Param("horaInicio") LocalTime horaInicio, @Param("horaTermino") LocalTime horaTermino, @Param("nomePeriodo") String nomePeriodo);
    
    @Query("SELECT CASE WHEN (COUNT(catequeseHorario) > 0) THEN true ELSE false END FROM CatequeseHorarioCatequese catequeseHorario WHERE catequeseHorario.diaSemana = :diaSemana and catequeseHorario.horaInicio = :horaInicio and  catequeseHorario.horaTermino = :horaTermino and  catequeseHorario.nomePeriodo = :nomePeriodo")
    public boolean horarioExisteNoSistema(@Param("diaSemana") DiaSemana diaSemana, @Param("horaInicio") LocalTime horaInicio, @Param("horaTermino") LocalTime horaTermino, @Param("nomePeriodo") String nomePeriodo);
}