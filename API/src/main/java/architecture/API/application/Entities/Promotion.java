package architecture.API.application.Entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Promotion {
    @NotNull(message = "Discount percent cannot be empty")
    private double discountPercent;

    @NotBlank(message = "Discount code cannot be empty")
    private String discountCode;

    public Promotion(double discountPercent, String discountCode) {
        this.discountPercent = discountPercent;
        this.discountCode = discountCode;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public String getDiscountCode(){
        return discountCode;
    }
}
