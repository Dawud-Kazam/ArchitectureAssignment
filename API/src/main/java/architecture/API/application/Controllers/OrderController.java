package architecture.API.application.Controllers;

import architecture.API.application.Entities.CreditCard;
import architecture.API.application.Entities.Order;
import architecture.API.application.Entities.Product;
import architecture.API.infrastructure.OrderRepository;
import org.springframework.web.bind.annotation.RestController;
import architecture.API.application.Entities.Basket;
import architecture.API.infrastructure.BasketRepository;
import architecture.API.infrastructure.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final BasketRepository basketRepository;
    private final OrderRepository orderRepository;


    private final ProductRepository productRepository;

    OrderController(OrderRepository orderRepository, BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    List<Order> GetAllOrders(){
        return orderRepository.findAll();
    }

    @PostMapping("/order/{basketID}")
    String TestCardDetails(@PathVariable Long basketID, @RequestBody CreditCard cardDetails){
        if (CheckCard(cardDetails)) {
             orderRepository.save(CreateOrder(basketID));

             return "order number " + orderRepository.getById(orderRepository.count()).getOrderID() + " created";
        } else {
            return "please enter valid card details";
        }
    }

    boolean CheckCard(CreditCard card) {
        String cardNumber = card.getCardNumber();
        int sum = 0;
        boolean everyOtherDigit = false;

        //Go through digits from right to left
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            char c = cardNumber.charAt(i);
            if (!Character.isDigit(c)) {
                return false; // Invalid character
            }

            int n = c - '0';
            if (everyOtherDigit) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }

            sum += n;
            everyOtherDigit = !everyOtherDigit;
        }

        // Valid if total mod 10 = 0
        return sum % 10 == 0;
    }


    Order CreateOrder(@PathVariable Long basketID){
        Basket basket = basketRepository.getById(basketID);
        double totalPrice = 0;

        for (Long key: basket.getProducts().keySet()) {
            Product p = productRepository.getById(key);
            totalPrice += p.getFullPrice() * basket.getAmount(key);
        }

        totalPrice = totalPrice * (1 - (basket.getPromotion().getDiscountPercent() / 100));

        return new Order(basketID, totalPrice);
    }
}
