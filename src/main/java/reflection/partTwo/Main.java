package reflection.partTwo;

import reflection.partOne.Reflection;
import reflection.partTwo.domain.Product;
import reflection.partTwo.service.PathService;
import reflection.partTwo.service.XMLService;

import java.util.List;

public class Main {


    public static final String DOMAIN = "reflection.partTwo.domain.";
    public static final List<Product> PRODUCTS = createProducts();

    public static void main(String[] args) {
        final var pathService = new PathService();
        final var xmlService = new XMLService();
//        var scanner = new Scanner(System.in);
//        var userInput = scanner.nextLine();
//        var pathResponse = PathService.validPath(userInput);
        var pathResponse = pathService.validPath("/product/1");

        var className = pathResponse.getClassName();
        var reflectedClass = Reflection.reflection(DOMAIN + className)
                .getReflectedClass();

        var xml = xmlService.convertToXml(PRODUCTS);
        System.out.println(xml);

//        scanner.close();
    }


    private static List<Product> createProducts() {
        return List.of(
                new Product(1, "name1", 20.0, "label1"),
                new Product(2, "name2", 19.0, "label2"),
                new Product(3, "name3", 18.0, "label3"),
                new Product(4, "name4", 17.0, "label4")
        );
    }
}
