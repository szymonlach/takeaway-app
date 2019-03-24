package pl.lach.spring.takeawayapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.lach.spring.takeawayapp.Order.OrderRepository;
import pl.lach.spring.takeawayapp.Order.OrderService;

@Controller
public class OrderController {

    private OrderRepository orderRepository;
    private OrderService orderService;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }


    @GetMapping("/order")
    public String showOrder(){

        return "OrderPage";
    }

}
