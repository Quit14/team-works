public class Product {
    private final String name;
    private final double price;
    private int inBasket = 0;
    private boolean isItemOnSale = false;

    Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, boolean isItemOnSale) {
        this.name = name;
        this.price = price;
        this.isItemOnSale = isItemOnSale;
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

    public void changeItemInBasket(int itemsNmb) {
        this.inBasket += itemsNmb;
    }

    public boolean onSale() {
        return isItemOnSale;
    }

}
