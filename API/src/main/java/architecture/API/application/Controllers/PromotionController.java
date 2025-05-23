package architecture.API.application.Controllers;

import architecture.API.application.Entities.Basket;
import architecture.API.application.Entities.Promotion;
import architecture.API.infrastructure.BasketRepository;
import architecture.API.infrastructure.PromotionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class PromotionController {

    private static final Logger log = LoggerFactory.getLogger(BasketController.class);
    private final BasketRepository basketRepository;


    private final PromotionRepository promotionRepository;

    PromotionController(BasketRepository basketRepository, PromotionRepository promotionRepository) {
        this.basketRepository = basketRepository;
        this.promotionRepository = promotionRepository;

        promotionRepository.save(new Promotion(10, "DISCOUNT10"));
        promotionRepository.save(new Promotion(20, "DISCOUNT20"));
    }

    @PutMapping("/promotion/{basketID}")
    Basket addDiscountToBasket(@PathVariable Long basketID, @RequestBody String discountCode) throws Exception {
        Promotion temp = null;

        for (Promotion p: promotionRepository.findAll()) {
            if (discountCode.equals(p.getDiscountCode())) {
                temp = p;
            }
        }

        if (temp != null) {
            Promotion finalTemp = temp; //to prevent an edge case bug
            return basketRepository.findById(basketID)
                    .map(basket -> {
                        basket.ApplyPromotion(finalTemp);
                        return basketRepository.save(basket);
                    })
                    .orElseThrow(() -> new Exception("Failed"));
        } else {
            return basketRepository.getById(basketID);
        }

    }

    @GetMapping("/promotion/test")
    String checkPromo(){
        return basketRepository.getById(1L).getPromotion().toString();
    }
}
