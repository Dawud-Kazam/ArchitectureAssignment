package architecture.API.application.Controllers;

import architecture.API.application.Entities.Promotion;
import architecture.API.infrastructure.BasketRepository;
import architecture.API.infrastructure.ProductRepository;
import architecture.API.infrastructure.PromotionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

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
}
