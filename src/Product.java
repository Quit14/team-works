public class Product {
    private final String name;
    private final double price;
    private int inBasket = 0;
    private double priceProduct = 0; // для расчета цены конкретного продукта в корзине

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

    public double getPriceProduct() {
        return priceProduct;
    }

    public void priceProduct(int itemCount) {      //расчет цены продукта в корзине
        this.priceProduct = itemCount == 0 ? 0 : (priceProduct + price * itemCount);
    }

    public void incItemInBasket(int itemsNmb) {
        this.inBasket = itemsNmb == 0 ? 0 : (inBasket + itemsNmb); //количество = 0, если ввели 0

    }

}
