package pl.lach.spring.takeawayapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.lach.spring.takeawayapp.Order.OrderRepository;
import pl.lach.spring.takeawayapp.Order.OrderService;
import pl.lach.spring.takeawayapp.dish.Dish;
import pl.lach.spring.takeawayapp.dish.DishRepository;

import java.util.Optional;

@Controller
public class OrderController {

    private OrderRepository orderRepository;
    private DishRepository dishRepository;
    private OrderService orderService;

    @Autowired
    public OrderController(OrderRepository orderRepository, DishRepository dishRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
        this.orderService = orderService;
    }


    @GetMapping("/order")
    public String showOrder(Model model) {
        model.addAttribute("order", orderService.getOrder());
        model.addAttribute("totalPrice", orderService.getOrder().getDishes()
                .stream().mapToDouble(Dish::getPrice).sum());
        return "OrderPage";
    }

    @GetMapping("/order/add")
    public String addOrder(@RequestParam(name = "id", required = true, defaultValue = "1") Long id, Model model) {
        Optional<Dish> dish = dishRepository.findById(id);
        dish.ifPresent(d -> {
            orderService.addDishToOrder(d);
            model.addAttribute("order", orderService.getOrder());
            model.addAttribute("totalPrice", orderService.getOrder().getDishes()
                    .stream().mapToDouble(Dish::getPrice).sum());
        });
        return dish.map(d -> "OrderPage").orElse("redirect:/main");
    }

    @PostMapping("/order/complete")
    public String realizeOrder(@RequestParam String address, @RequestParam String telephone) {
        orderService.getOrder().setAddres(address);
        orderService.getOrder().setTelephone(telephone);
        orderRepository.save(orderService.getOrder());
        orderService.clear();
        return "MessagePage";
    }

}
