package reflection.playground;

import reflection.partTwo.annotations.TagXML;
import reflection.partTwo.domain.Product;

public class Main {
    public static void main(String[] args) {
        var product = new Product("1", "name", 20.0, "brand");
        var productClass = product.getClass();

        var declaredAnnotation = productClass.getDeclaredAnnotation(TagXML.class).value();
        System.out.println(declaredAnnotation);
    }
}
