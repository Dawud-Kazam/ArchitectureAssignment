package architecture.API.application.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class CreditCard {
    @NotBlank(message = "Card number is required")
    @Size(min = 16, max = 16, message = "Card number must be 16 digits long with no spaces")
    private String cardNumber;

    @NotBlank(message = "expiry date is required")
    @Size(min = 5, max = 5, message = "expiry must be in the format MM/yy")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "MM/yy")
            @Future
    private String expiry;

    CreditCard(){}

    CreditCard(String cardNumber, String expiry) {
        this.cardNumber = cardNumber;
        this.expiry = expiry;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiry() {
        return expiry;
    }
}
