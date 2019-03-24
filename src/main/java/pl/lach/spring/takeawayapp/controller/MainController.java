package pl.lach.spring.takeawayapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.lach.spring.takeawayapp.dish.Dish;
import pl.lach.spring.takeawayapp.dish.DishRepository;

import java.util.List;

@Controller
public class MainController {

    private DishRepository dishRepository;

    @Autowired
    public MainController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping("/main")
    public String start(Model model){
        List<Dish> dishes = dishRepository.findAll();
        model.addAttribute("dishes",dishes);
        return "MainSite";
    }

}
