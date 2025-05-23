package architecture.API.application.Controllers;

import architecture.API.application.Entities.Basket;
import architecture.API.infrastructure.BasketRepository;
import architecture.API.infrastructure.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasketController {

    private static final Logger log = LoggerFactory.getLogger(BasketController.class);
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    BasketController(BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/basket/{customerID}")
    String createBasket(@PathVariable Long customerID) {
        Basket temp = new Basket(customerID);
        basketRepository.save(temp);
        return "Basket created with ID: " + basketRepository.count();
    }

    @PutMapping("/basket/{basketID}/{productID}")
    Basket addToBasket(@PathVariable Long basketID, @PathVariable Long productID, @RequestBody Integer quantity) throws Exception {
        return basketRepository.findById(basketID)
                .map(basket -> {
                    basket.AddProduct(productID, quantity);
                    return basketRepository.save(basket);
                })
                .orElseThrow(() -> new Exception("Failed"));
    }
}
