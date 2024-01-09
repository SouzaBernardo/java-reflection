package reflection.partTwo.controller;

import reflection.partTwo.service.XMLService;

import static reflection.partTwo.Main.PRODUCTS;

public class ProductController {
    private static final XMLService xmlService = new XMLService();
    public String list() {
        return xmlService.convertToXml(PRODUCTS);
    }

    public String getProduct(Long id) {

        var product = PRODUCTS.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow();

        return xmlService.convertToXml(product);
    }

}
