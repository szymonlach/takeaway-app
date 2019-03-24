package pl.lach.spring.takeawayapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.lach.spring.takeawayapp.dish.Dish;
import pl.lach.spring.takeawayapp.dish.DishRepository;

import java.util.Optional;

@Controller
public class SubController {


    private DishRepository dishRepository;

    @Autowired
    public SubController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    @GetMapping("/dish/{name}")
    public String getSubPage(@PathVariable String name, Model model) {
        Optional<Dish> dish = dishRepository.findByName(name.replaceAll("-", " "));
        dish.ifPresent(d -> model.addAttribute("dish", d));
        return dish.map(d->"SubPage").orElse("redirect:/main");
    }
}
