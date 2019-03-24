package pl.lach.spring.takeawayapp.Order;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.lach.spring.takeawayapp.dish.Dish;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderService {

    private List<Dish> orderedDishes;

    public OrderService() {
        this.orderedDishes = new ArrayList<>();
    }

    public List<Dish> getOrderedDishes() {
        return orderedDishes;
    }

    public void addDish(Dish dish) {
        this.orderedDishes.add(dish);
    }
}
