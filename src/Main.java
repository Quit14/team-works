import java.util.Scanner;

public class Main {
    static Product[] goods = {
            new Product("Хлеб", 60.0),
            new Product("Гречка", 92.60),
            new Product("Молоко", 90.0, true),
            new Product("Яблоко", 20.0),
            new Product("Тушенка", 385.50, true),
            new Product("Сгущенка", 127.80, true),
            new Product("Сахар", 75.0)
    };
    static final int GOODS_IN_SALE_KIT = 3;  // Кратность товара одного типа для учакстия в акции
    static final int SALE_KIT_PRICE = 2;   // Множитель Стоимости одной "кратности" товара
    static double totalPrice = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s; // Пользовательский ввод
        int selectedItem;
        int itemCount;

        while (true) { // Вертимся тут до ввода end выхода
            printGoodsList(); // Выводим на экран Инфопанель
            s = sc.nextLine();
            String[] inputValues = s.split(" ");
            if (inputValues.length == 2) {
                try {
                    selectedItem = Integer.parseInt(inputValues[0]);
                    itemCount = Integer.parseInt(inputValues[1]);

                    if (selectedItem <= 0 || selectedItem > goods.length) {
                        System.out.print("\nНеправильный номер товара\n");
                        continue;
                    }
                    if ((itemCount < 0) && ((goods[selectedItem - 1].getInBasket() + itemCount) < 0)) {
                        System.out.println("\nТакого Количества данного товара нет в корзине");
                        continue;
                    }
                    if (itemCount == 0) {
                        itemCount -= goods[selectedItem - 1].getInBasket();
                    }
                    totalPrice -= goodsValue(goods[selectedItem - 1]);  // Тотал минус стоимость текущего товара в корзине
                    goods[selectedItem - 1].changeItemInBasket(itemCount);
                    totalPrice += goodsValue(goods[selectedItem - 1]);
                } catch (NumberFormatException nfe) {
                    // Во вводе что-то отличное от двух целых чисел
                    System.out.println("\nНужно 2 аргумента - 2 целых числа");
                }
            } else if (s.equals("end")) {
                break;  // Game Over
            }
            System.out.println("\nНужно 2 аргумента");
        }
        sc.close();
        printBasket();
    }

    static void printGoodsList() {
        var goodsOnSale = new StringBuffer();

        System.out.print(" №       Название   Цена за шт.     В корзине        Стоимость\n" + "");
        for (int i = 0; i < goods.length; i++) {
            if (goods[i].onSale()) { // Товары по акции выведем на печать потом
                goodsOnSale.append(String.format("%2d. %13s %12.2f %10d %17.2f\n", i + 1,
                        goods[i].getName(), goods[i].getPrice(),
                        goods[i].getInBasket(), goodsValue(goods[i])));
            } else {
                System.out.printf("%2d. %13s %12.2f %10d %17.2f\n", i + 1,
                        goods[i].getName(), goods[i].getPrice(),
                        goods[i].getInBasket(), goodsValue(goods[i]));
            }
        }
        System.out.println("Товары по акции три по цене двух:");
        System.out.println(goodsOnSale); // Товары по акции
        System.out.printf("ИТОГО Товаров в корзине на %10.2f\n\n", totalPrice);
        System.out.print("""
                Добавьте товар в корзину  (№ и количество<ENTER>).
                Убрать товар из корзины   (№ и количество со знаком <->)
                Обнулить товар в корзине  (№ и <0>).
                Для завершения работы введите <end>.
                """);
    }

    static void printBasket() {
        System.out.print("Ваша корзина:\n");
        for (Product item : goods) {
            if (item.getInBasket() > 0) {
                System.out.printf("%13s %3d шт. %6.2f руб/шт. %8.2f в сумме\n",
                        item.getName(), item.getInBasket(), item.getPrice(),
                        goodsValue(item));
            }
        }
        System.out.printf("ИТОГО Товаров в корзине на %10.2f\n\n", totalPrice);
    }

    /**
     * Стоимость товара в корзине с учетом участия или неучастия товара d акции
     */
    static double goodsValue(Product item) {
        double itemsValue;
        if (item.onSale()) {
            int saleKits = item.getInBasket() / GOODS_IN_SALE_KIT;
            itemsValue = item.getPrice() *
                    (saleKits * SALE_KIT_PRICE + item.getInBasket() % GOODS_IN_SALE_KIT);
        } else {
            itemsValue = item.getInBasket() * item.getPrice();
        }
        return itemsValue;
    }
}
