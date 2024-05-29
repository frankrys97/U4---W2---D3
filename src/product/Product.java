package product;

import enums.Category;

import java.text.DecimalFormat;
import java.util.Random;

public class Product {

    long id;
    String name;
    String category;
    double price;


    public Product(Category category, double price) {

        Random random = new Random();

        this.id = Math.abs(random.nextLong());
        this.name = generateRandomicName(category);
        this.category = String.valueOf(category);
        this.price = price;
    }

    public static String generateRandomicName(Category category) {
        Random random = new Random();

        switch (category) {
            case BOOKS:
                return "Book " + random.nextInt(100, 999);
            case ELECTRONICS:
                return "Laptop " + random.nextInt(100, 999);
            case TOYS:
                return "Toy " + random.nextInt(100, 999);
            case SCHOOL:
                return "Notebook " + random.nextInt(100, 999);
            default:
                return "Unknown ";
        }
    }

    public static double generateRandomPrice() {
        Random random = new Random();
        double price = random.nextDouble(10, 2000);
        DecimalFormat df = new DecimalFormat("#,##");
        return Double.parseDouble(df.format(price));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name= '" + name + '\'' +
                ", category= '" + category + '\'' +
                ", price= " + price +
                '}';
    }
}

