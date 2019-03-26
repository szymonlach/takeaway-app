package pl.lach.spring.takeawayapp.order;

import pl.lach.spring.takeawayapp.dish.Dish;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "order_item",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"))
    private List<Dish> dishes = new ArrayList<>();
    private String address;
    private String telephone;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddres(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getDishes(), order.getDishes()) &&
                Objects.equals(getAddress(), order.getAddress()) &&
                Objects.equals(getTelephone(), order.getTelephone()) &&
                getStatus() == order.getStatus();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDishes(), getAddress(), getTelephone(), getStatus());
    }

    @Override
    public String toString() {
        return "order{" +
                "id=" + id +
                ", dishes=" + dishes +
                ", addres='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", status=" + status +
                '}';
    }
}
