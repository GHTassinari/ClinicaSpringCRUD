package br.edu.ifsuldeminas.machado.CrudManagerSpringClinicaVeterinaria.controller;

import br.edu.ifsuldeminas.machado.CrudManagerSpringClinicaVeterinaria.model.Consulta;
import br.edu.ifsuldeminas.machado.CrudManagerSpringClinicaVeterinaria.repository.AnimalRepository;
import br.edu.ifsuldeminas.machado.CrudManagerSpringClinicaVeterinaria.repository.ConsultaRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaRepository consultaRepo;
    private final AnimalRepository animalRepo;

    @Autowired
    public ConsultaController(ConsultaRepository consultaRepo, AnimalRepository animalRepo) {
        this.consultaRepo = consultaRepo;
        this.animalRepo = animalRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("consultas", consultaRepo.findAll());
        return "consultas/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("animais", animalRepo.findAll());
        return "consultas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Consulta consulta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("animais", animalRepo.findAll());
            return "consultas/form";
        }
        consultaRepo.save(consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Consulta consulta = consultaRepo.findById(id).orElseThrow();
        model.addAttribute("consulta", consulta);
        model.addAttribute("animais", animalRepo.findAll());
        return "consultas/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        consultaRepo.deleteById(id);
        return "redirect:/consultas";
    }
}
