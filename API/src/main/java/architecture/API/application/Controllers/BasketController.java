package architecture.API.application.Controllers;

import architecture.API.ApiApplication;
import architecture.API.application.Entities.Basket;
import architecture.API.application.Entities.Product;
import architecture.API.application.Entities.Promotion;
import architecture.API.infrastructure.BasketRepository;
import architecture.API.infrastructure.ProductRepository;
import architecture.API.infrastructure.PromotionRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BasketController {

    private static final Logger log = LoggerFactory.getLogger(BasketController.class);
    private final BasketRepository basketRepository;


            private  final ProductRepository productRepository;

    BasketController(BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/basket")
    List<Basket> getBbd(){
        return basketRepository.findAll();
    }

    @PostMapping("/basket/{customerID}")
    String createBasket(@PathVariable Long customerID){
        Basket temp = new Basket(customerID);
        basketRepository.save(temp);
        return "Basket created with ID: " + basketRepository.count();
    }

    @PutMapping("/basket/{basketID}/{productID}")
    Basket addToBasket(@PathVariable Long basketID, @PathVariable Long productID, @RequestBody Integer quantity) throws Exception {

        Product temp =  productRepository.getById(productID);

        System.out.println(temp);
        System.out.println(quantity);

        return basketRepository.findById(basketID)
                .map(basket -> {
                    basket.AddProduct(temp, quantity);
                    return basketRepository.save(basket);
                })
                .orElseThrow(() -> new Exception("Failed"));
    }

    @GetMapping("/basket/test")
    String checkBasket(){
//        System.out.println(basketRepository.findAll());
        Basket temp = basketRepository.getById(1L);
        System.out.println(temp);
        return "ID: " +  temp.getBasketID() + ", product quantity: " + temp.GetProducts().get(2); //+ ", total price: " + temp.getTotalPrice();
    }
}
