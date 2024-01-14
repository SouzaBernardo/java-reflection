package reflection.controller;

import reflection.application.service.ProductXmlService;

public class ProductController {

    private final ProductXmlService productXmlService;

    public ProductController() {
        productXmlService = new ProductXmlService();
    }

    public String listXML() {
        return productXmlService.getProducts();
    }

    public String getProduct(String id) {
        return productXmlService.getProduct(id);
    }
}
