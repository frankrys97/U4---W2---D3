import customer.Customer;
import enums.Category;
import order.Order;
import product.Product;

import java.time.LocalDate;
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
        System.out.println("La lista prodotti del nostro negozio: ");
        System.out.println(products);
        System.out.println();

        System.out.println("La lista clienti del nostro negozio: ");
        System.out.println(customers);
        System.out.println();

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


        System.out.println("La lista degli ordini del nostro negozio: ");
        System.out.println(orders);
        System.out.println();

        // Esercizio 1

        List<Product> bookProducts = products
                .stream()
                .filter(product -> product.getCategory().equals(Category.BOOKS) && product.getPrice() > 10)
                // ho messo 10 e non 100 poichè avendo generato randomicamente tutti i valori ho preferito mettere un numero più basso
                // altrimenti la probabilità che uscisse un libro nella lista degli ordini con prezzo maggiore di 100 era molto bassa!!!
                .toList();


        System.out.println("La lista di prodotti per libri con prezzo maggiore di 10:");
        if (bookProducts.isEmpty()) {
            System.out.println("Nessun libro trovato");
            System.out.println();
        } else {
            System.out.println(bookProducts);
            System.out.println();
        }

        // Esercizio 2

        List<Order> babyOrders = orders.stream().filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals(Category.BABY))).toList();


        System.out.println("La lista di prodotti per bambini:");

        if (babyOrders.isEmpty()) {
            System.out.println("Nessun prodotto per bambini trovato");
            System.out.println();
        } else {
            System.out.println(babyOrders);
            System.out.println();
        }

        // Esercizio 3

        List<Product> boysWithDiscount = products.stream().filter(product -> product.getCategory().equals(Category.BOYS)).map(product -> {
            product.setPrice(product.getPrice() * 0.9);
            return product;
        }).toList();


        System.out.println("La lista di prodotti per ragazzi con sconto del 10%:");
        if (boysWithDiscount.isEmpty()) {
            System.out.println("Nessun prodotto per ragazzi trovato");
            System.out.println();
        } else {
            System.out.println(boysWithDiscount);
            System.out.println();
        }

        // Esercizio 4

        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2022, 4, 1);

        List<Product> productClient2BetwDate = orders.stream().filter(order -> order.getCustomer().getTier() == 2 && !order.getOrderDate().isBefore(startDate) && !order.getOrderDate().isAfter(endDate)).flatMap(order -> order.getProducts().stream()).toList();

        System.out.println("La lista di prodotti per clienti di livello 2 con ordini tra il 1/2/2021 e il 1/4/2021:");
        if (productClient2BetwDate.isEmpty()) {
            System.out.println("Nessun prodotto trovato");
            System.out.println();
        } else {
            System.out.println(productClient2BetwDate);
            System.out.println();
        }
    }
}
