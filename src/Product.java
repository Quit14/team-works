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


    public void incItemInBasket(int itemsNmb) {
        this.inBasket = itemsNmb == 0 ? 0 : (inBasket + itemsNmb);
    } //количество = 0, если ввели 0

    public boolean onSale() {
        return isItemOnSale;
    }

}
