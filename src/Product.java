public class Product {
    private final String name;
    private final double price;
    private int inBasket = 0;

    Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getInBasket() {
        return inBasket;
    }

    public void incItemInBasket(int itemsNmb) {
        this.inBasket += itemsNmb;
    }
}
