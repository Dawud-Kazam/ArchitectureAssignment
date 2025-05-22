package architecture.API.application.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class Product {
    private @Id
    @GeneratedValue Long productID;

    @NotNull
    private double fullPrice;

    public Long getProductID() {
        return productID;
    }
    
    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double price) {
        fullPrice = price;
    }

    @Override
    public String toString() {
        return "Product id: " + productID + ", product full price: " + fullPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, fullPrice);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product p = (Product) obj;
        return p.getProductID().equals(this.productID) && p.getFullPrice() == this.fullPrice;
    }
}
