package pl.lach.spring.takeawayapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.lach.spring.takeawayapp.Order.Order;
import pl.lach.spring.takeawayapp.Order.OrderRepository;
import pl.lach.spring.takeawayapp.Order.OrderService;
import pl.lach.spring.takeawayapp.Order.OrderStatus;
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
        model.addAttribute("orders", orderService.getOrderedDishes());
        model.addAttribute("totalPrice",orderService.getTotalPrice());
        model.addAttribute("order",new Order());
        return "OrderPage";
    }

    @GetMapping("/order/add")
    public String addOrder(@RequestParam(name = "id", required = true, defaultValue = "1") Long id, Model model) {
        Optional<Dish> dish = dishRepository.findById(id);
        dish.ifPresent(d -> {
            orderService.addDish(d);
            model.addAttribute("dishes", orderService.getOrderedDishes());
            model.addAttribute("totalPrice",orderService.getTotalPrice());
            model.addAttribute("order",new Order());
        });
        return dish.map(d -> "OrderPage").orElse("redirect:/main");
    }

    @PostMapping("/order/complete")
    public String realizeOrder(@ModelAttribute Order order){
        order.setDishes(orderService.getOrderedDishes());
        order.setStatus(OrderStatus.COMPLETE);
        orderRepository.save(order);
        return "OrderComplete";
    }

}
