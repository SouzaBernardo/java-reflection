package reflection;

import reflection.adapters.Impl.ReflectionGatewayImpl;
import reflection.application.service.PathService;
import reflection.core.useCase.impl.ReflectionUseCaseImpl;

import java.util.Scanner;

public class Main {

    public static final String REFLECTION_DOG = "reflection.core.domain.Dog";
    public static final String METHOD_NAME = "sayHiTo";
    public static final String CONTROLLER_PATH = "reflection.controller.";

    public static void main(String[] args) {
        var pathService = new PathService();
        var reflectionUseCase = new ReflectionGatewayImpl();
        var scanner = new Scanner(System.in);
        var userInput = scanner.nextLine();
        do {
            var pathResponse = pathService.validPath(userInput, CONTROLLER_PATH);
            var result = reflectionUseCase.reflect(pathResponse.getFullClassName())
                    .constructor()
                    .useMethod()
                    .invoke(pathResponse);
            System.out.println(result);
            userInput = scanner.nextLine();
        } while (!userInput.equalsIgnoreCase("z"));

        scanner.close();
    }
}
