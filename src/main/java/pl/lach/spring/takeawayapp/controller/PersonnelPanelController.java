package pl.lach.spring.takeawayapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.lach.spring.takeawayapp.Order.Order;
import pl.lach.spring.takeawayapp.Order.OrderRepository;
import pl.lach.spring.takeawayapp.Order.OrderStatus;

import java.util.List;

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
}
