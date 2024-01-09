package reflection.partTwo;

import reflection.partOne.Reflection;
import reflection.partTwo.domain.Product;
import reflection.partTwo.service.PathService;

import java.util.List;

import static reflection.partTwo.util.ProductUtil.createProducts;

public class Main {



    public static final List<Product> PRODUCTS = createProducts();
    private static final PathService pathService = new PathService();


    public static void main(String[] args) {

//        var scanner = new Scanner(System.in);
//        var userInput = scanner.nextLine();
//        var pathResponse = PathService.validPath(userInput);
        var pathResponse = pathService.validPathController("/product/list");

        var result = Reflection.reflect(pathResponse.getClassName())
                .constructor()
                .useMethod()
                .invoke(pathResponse.getMethod());

        System.out.println(result);

        var pathResponse2 = pathService.validPathController("/product/1");
        System.out.println(pathResponse2);
//        scanner.close();
    }

}
