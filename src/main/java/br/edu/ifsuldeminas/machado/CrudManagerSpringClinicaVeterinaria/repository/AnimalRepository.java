package br.edu.ifsuldeminas.machado.CrudManagerSpringClinicaVeterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.machado.CrudManagerSpringClinicaVeterinaria.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {}
