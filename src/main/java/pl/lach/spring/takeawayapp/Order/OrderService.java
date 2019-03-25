package pl.lach.spring.takeawayapp.Order;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.lach.spring.takeawayapp.dish.Dish;

@Service
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderService {

    private Order order;

    public OrderService() {
        clear();
    }

    public Order getOrder() {
        return order;
    }

    public void addDishToOrder(Dish dish) {
        order.getDishes().add(dish);
    }

    public void clear() {
        order = new Order();
        order.setStatus(OrderStatus.NEW);
    }


}
