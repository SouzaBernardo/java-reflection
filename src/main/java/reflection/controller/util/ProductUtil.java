package reflection.controller.util;

import reflection.core.domain.Product;

import java.util.List;

public class ProductUtil {

    /**
     * Simulate products in repository
     * return a list of products
     * */
    public static List<Product> createProducts() {
        return List.of(
                new Product("1", "name1", 20.0, "label1"),
                new Product("2", "name2", 19.0, "label2"),
                new Product("3", "name3", 18.0, "label3"),
                new Product("4", "name4", 17.0, "label4")
        );
    }
}
