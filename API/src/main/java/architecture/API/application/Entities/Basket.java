package architecture.API.application.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class Basket {
    private @Id
    @GeneratedValue Long basketID;

    private Long customerID;

    @JdbcTypeCode(SqlTypes.JSON)
    private HashMap<Long, Integer> products;
    @JdbcTypeCode(SqlTypes.JSON)
    private Promotion promotion;

    Basket(){}


    public Basket(long customerID) {
        this.customerID = customerID;
        products = new HashMap<Long, Integer>();
    }

    public void AddProduct(Long pID, Integer amount) {
        products.put(pID, amount);
    }

    public Map<Long, Integer> getProducts() {
        return products;
    }

    public void ApplyPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Long getBasketID() {
        return basketID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public Integer getAmount(Long productID){
        return products.get(productID);
    }


    @Override
    public String toString() {
        return "basket id: " + basketID + ", customer id: " + this.customerID + ", prodcuts: " + products.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketID, customerID, promotion, products);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Basket b = (Basket) obj;
        return this.basketID.equals(b.basketID) && this.customerID.equals(b.customerID) && this.promotion.equals(b.promotion) && this.products.equals(b.products);
    }
}
