package reflection.partTwo.controller;

import reflection.partTwo.service.XMLService;

import static reflection.partTwo.Main.PRODUCTS;

public class ProductController {
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
