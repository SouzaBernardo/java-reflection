package reflection.partTwo;

import reflection.core.service.Reflection;
import reflection.partTwo.domain.Product;
import reflection.partTwo.service.PathService;

import java.util.List;
import java.util.Scanner;

import static reflection.partTwo.util.ProductUtil.createProducts;

public class Main {
    public static final List<Product> PRODUCTS = createProducts();
    private static final PathService pathService = new PathService();


    public static void main(String[] args) {

        var scanner = new Scanner(System.in);
        var userInput = scanner.nextLine();
        do {
            var pathResponse = pathService.validPath(userInput);
            var result = Reflection.reflect(pathResponse.getFullClassName())
                    .constructor()
                    .useMethod()
                    .invoke(pathResponse);
            System.out.println(result);
            userInput = scanner.nextLine();
        } while (!userInput.equalsIgnoreCase("z"));

        scanner.close();
    }

}
