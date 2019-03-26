package pl.lach.spring.takeawayapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.lach.spring.takeawayapp.message.Message;
import pl.lach.spring.takeawayapp.order.Order;
import pl.lach.spring.takeawayapp.order.OrderRepository;
import pl.lach.spring.takeawayapp.order.OrderStatus;
import pl.lach.spring.takeawayapp.dish.Dish;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonnelPanelController {

    private OrderRepository orderRepository;

    @Autowired
    public PersonnelPanelController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @GetMapping("/panel/orders")
    public String showPanel(@RequestParam(required = false, defaultValue = "ALL") String status, Model model) {
        List<Order> orders = null;
        switch (status) {
            case "NEW":
                orders = orderRepository.findAllByStatus(OrderStatus.NEW);
                break;
            case "IN_PROGRESS":
                orders = orderRepository.findAllByStatus(OrderStatus.IN_PROGRESS);
                break;
            case "COMPLETE":
                orders = orderRepository.findAllByStatus(OrderStatus.COMPLETE);
                break;
            case "ALL":
                orders = orderRepository.findAll();
        }
        model.addAttribute("orders", orders);

        return "personnel/PanelPage";
    }

    @GetMapping("/panel/orders/{id}")
    public String showOrderDetails(@PathVariable Long id, Model model) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(o -> {
            model.addAttribute("order", o);
            model.addAttribute("totalPrice", o.getDishes().stream().mapToDouble(Dish::getPrice).sum());
        });
        return "personnel/PanelOrderPage";
    }

    @PostMapping("/panel/orders/{id}")
    public String updateOrder(@PathVariable Long id, Model model) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(o -> {
            if (o.getStatus().equals(OrderStatus.NEW))
                o.setStatus(OrderStatus.IN_PROGRESS);
            else if (o.getStatus().equals(OrderStatus.IN_PROGRESS))
                o.setStatus(OrderStatus.COMPLETE);
            orderRepository.save(o);
            model.addAttribute("message", new Message("Update order", "Actually order is " + o.getStatus().toString()));
        });

        return "MessagePage";
    }
}
