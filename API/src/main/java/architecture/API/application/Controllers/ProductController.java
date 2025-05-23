package architecture.API.application.Controllers;

import architecture.API.application.Entities.Product;
import architecture.API.infrastructure.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {


    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository productRepository;

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;

        productRepository.save(new Product(11.0, "Beans1"));
        productRepository.save(new Product(6.0, "Chocolate1"));
        productRepository.save(new Product(23.0, "Eggs1"));
        productRepository.save(new Product(4.0, "Eggs2"));
    }

    @GetMapping("/products")
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
