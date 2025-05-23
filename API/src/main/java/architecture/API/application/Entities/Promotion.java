package architecture.API.application.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Promotion  implements Serializable { //Adding "Serializable" esnures this class can be serialized and peristed into or read out of the database files. taking it out causes errors
    private @Id
    @GeneratedValue Long promotionID;

    @NotNull(message = "Discount percent cannot be empty")
    private double discountPercent;

    @NotBlank(message = "Discount code cannot be empty")
    private String discountCode;

    public Promotion(double discountPercent, String discountCode) {
        this.discountPercent = discountPercent;
        this.discountCode = discountCode;
    }

    public Long getPromotionID() {
        return promotionID;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public String getDiscountCode(){
        return discountCode;
    }

    @Override
    public String toString() {
        return "promotion ID: " + promotionID + ", discount code: " + discountCode + ", disocunt percentage" + discountPercent + "%";
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionID, discountCode, discountPercent);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Promotion p = (Promotion) obj;
        return this.promotionID.equals(p.promotionID) && this.discountCode.equals(p.discountCode) && this.discountPercent == p.discountPercent;
    }
}
