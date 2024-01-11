package reflection.controller;

import reflection.application.service.XMLService;
import reflection.core.domain.Product;

import java.util.List;

import static reflection.application.util.ProductUtil.createProducts;

public class ProductController {
    public static final List<Product> PRODUCTS = createProducts();
    private static final XMLService xmlService = new XMLService();
    public String list() {
        return xmlService.convertToXml(PRODUCTS);
    }

    public String getProduct(String id) {

        var product = PRODUCTS.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id) )
                .findFirst()
                .orElseThrow();

        return xmlService.convertToXml(product);
    }

}
