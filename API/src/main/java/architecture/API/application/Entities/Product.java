package architecture.API.application.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Product implements Serializable {
    private @Id
    @GeneratedValue Long productID;

    @NotBlank
    private String productCode;

    @NotNull
    private double fullPrice;

    Product() {
    }

    public Product(double fullPrice, String productCode) {
        this.fullPrice = fullPrice;
        this.productCode = productCode;
    }

    public Long getProductID() {
        return productID;
    }

    public String getProductCode() {
        return productCode;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    @Override
    public String toString() {
        return "Product id: " + productID + ", product code: " + productCode + ", product full price: " + fullPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productCode, fullPrice);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product p = (Product) obj;
        return p.getProductID().equals(this.productID) && p.getFullPrice() == this.fullPrice && p.getProductCode().equals(this.productCode);
    }
}
