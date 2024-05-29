import customer.Customer;
import enums.Category;
import order.Order;
import product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        Supplier<Product> productSupplier = () -> new Product(Category.values()[random.nextInt(Category.values().length)], Product.generateRandomPrice());

        Supplier<Customer> customerSupplier = () -> new Customer();

        List<Product> products = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            products.add(productSupplier.get());
            customers.add(customerSupplier.get());
        }

        Supplier<Order> orderSupplier = () -> {
            Customer customer = customers.get(random.nextInt(customers.size()));
            List<Product> orderProducts = new ArrayList<>();

            int numProducts = random.nextInt(1, products.size() + 1);
            for (int i = 0; i < numProducts; i++) {
                orderProducts.add(products.get(random.nextInt(products.size())));
            }

            return new Order(customer, orderProducts);
        };

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            orders.add(orderSupplier.get());
        }

        // Esercizio 1: Ottenere una lista di prodotti della categoria Books ed hanno un prezzo maggiore di 100


    }
}