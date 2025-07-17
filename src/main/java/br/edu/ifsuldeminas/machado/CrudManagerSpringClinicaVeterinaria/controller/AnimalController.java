package br.edu.ifsuldeminas.machado.CrudManagerSpringClinicaVeterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsuldeminas.machado.CrudManagerSpringClinicaVeterinaria.model.Animal;
import br.edu.ifsuldeminas.machado.CrudManagerSpringClinicaVeterinaria.repository.AnimalRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/animais")
public class AnimalController {
    private final AnimalRepository animalRepo;

    public AnimalController(AnimalRepository animalRepo) {
        this.animalRepo = animalRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("animais", animalRepo.findAll());
        return "animais/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("animal", new Animal());
        return "animais/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Animal animal, BindingResult result) {
        if (result.hasErrors()) return "animais/form";
        animalRepo.save(animal);
        return "redirect:/animais";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalRepo.findById(id).orElseThrow());
        return "animais/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        animalRepo.deleteById(id);
        return "redirect:/animais";
    }
}
