package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/accident")
public class AccidentController {

    private final AccidentService accidents;

    @GetMapping("/createAccident")
    public String viewCreateAccident() {
        return "accident/createAccident";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        Optional<Accident> accident = this.accidents.findById(id);
        if (accident.isPresent()) {
            model.addAttribute("accident", accident);
            return "updateAccident";
        }
        model.addAttribute("massage", "Not found");
        return "errors/404";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidents.save(accident);
        return "redirect:/index";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, Model model) {
        if (accidents.update(accident)) {
            return "redirect:/index";
        }
        model.addAttribute("massage", "Update error");
        return "errors/404";
    }
}
