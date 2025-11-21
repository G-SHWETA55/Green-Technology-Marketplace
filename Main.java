package main;
import model.*;
import service.MarketplaceService;
import threads.OrderProcessor;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        MarketplaceService service = new MarketplaceService();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to GreenTech Marketplace!");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = new User(name, email, password);
        service.register(user);

        List<Product> products = service.browseProducts();
        for (Product p : products) {
            p.displayDetails();
        }

        System.out.print("Enter product ID to order: ");
        int pid = sc.nextInt();
        Order order = new Order(1, pid); // assuming user ID = 1
        service.orderProduct(order);

        OrderProcessor processor = new OrderProcessor(order);
        processor.start();
    }
}
