package architecture.API.application.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Order {
    private @Id
    @GeneratedValue Long orderID;

    private final Long basketID;

    private final double totalPrice;

    public Order(Long basketID, double totalPrice) {
        this.basketID = basketID;
        this.totalPrice = totalPrice;
    }

    public Long getOrderID() {
        return orderID;
    }

    public Long getBasketID() {
        return basketID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
