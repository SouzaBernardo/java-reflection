package reflection.application.service;

import reflection.adapters.ConvertXmlGateway;
import reflection.adapters.Impl.ConvertXmlGatewayImpl;
import reflection.core.domain.Product;
import reflection.core.useCase.impl.ConvertXmlUseCaseImpl;

import java.util.List;

import static reflection.controller.util.ProductUtil.createProducts;

public class ProductXmlService {

    public static final List<Product> PRODUCTS = createProducts();
    private final ConvertXmlGateway convertXmlGateway;

    public ProductXmlService() {
        convertXmlGateway = new ConvertXmlGatewayImpl(new ConvertXmlUseCaseImpl());
    }

    public String getProducts() {
        return convertXmlGateway.convertToXml(PRODUCTS);
    }

    public String getProduct(String id) {
        var product = PRODUCTS.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id) )
                .findFirst()
                .orElseThrow();
        return convertXmlGateway.convertToXml(product);
    }
}
