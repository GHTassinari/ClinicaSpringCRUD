package br.edu.ifsuldeminas.machado.CrudManagerSpringClinicaVeterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.machado.CrudManagerSpringClinicaVeterinaria.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {}
