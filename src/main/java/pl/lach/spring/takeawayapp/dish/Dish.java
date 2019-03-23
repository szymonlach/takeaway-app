package pl.lach.spring.takeawayapp.dish;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private String name;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(length = 1024)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;
        Dish dish = (Dish) o;
        return Objects.equals(getId(), dish.getId()) &&
                Objects.equals(getPrice(), dish.getPrice()) &&
                Objects.equals(getName(), dish.getName()) &&
                Objects.equals(getShortDescription(), dish.getShortDescription()) &&
                Objects.equals(getDescription(), dish.getDescription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getPrice(), getName(), getShortDescription(), getDescription());
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
