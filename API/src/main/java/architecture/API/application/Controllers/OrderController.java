package architecture.API.application.Controllers;

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
import org.json.*;

import javax.smartcardio.Card;

@RestController
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(BasketController.class);
    private final BasketRepository basketRepository;
    private final OrderRepository orderRepository;


    private final ProductRepository productRepository;

    OrderController(OrderRepository orderRepository, BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/order/{basketID}")
    Order testCardDetails(@PathVariable Long baaketID, @RequestBody String cardDetails){
        JSONObject obj= new JSONObject(cardDetails);
    }


    Order createOrder(@PathVariable Long basketID){
        Basket basket = basketRepository.getById(basketID);
        double totalPrice = 0;

        for (Long key: basket.getProducts().keySet()) {
            Product p = productRepository.getById(key);
            totalPrice += p.getFullPrice() * basket.getAmount(key);
        }

        totalPrice = totalPrice * (1 - basket.getPromotion().getDiscountPercent());
    }
}
