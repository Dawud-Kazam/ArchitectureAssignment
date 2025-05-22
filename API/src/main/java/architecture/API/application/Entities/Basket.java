package architecture.API.application.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Map;

@Entity
public class Basket {
    private @Id
    @GeneratedValue Long id;

    private Long customerID;
    private Map<Product, Integer> products;
    private Promotion promotion;

    Basket(long customerID){
        promotion = new Promotion(0, "");
        this.customerID = customerID;
    }

    public void AddProduct(Product p, Integer amount){
        products.put(p, amount);
    }

    public Map<Product, Integer> GetProducts(){
        return products;
    }

    public void ApplyPromotion(Promotion promotion){
        this.promotion = promotion;
    }

    public double getTotalPrice(){
        double discountMultiplier = 1 - promotion.getDiscountPercent();
         double totalPrice = 0;

         for (Product product: products.keySet()) {
             Integer amount = products.get(product);

             totalPrice += (product.getFullPrice() * discountMultiplier) * amount;
         }

         return totalPrice;
    }
}
