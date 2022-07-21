import java.util.Scanner;

public class Main {
    static Product[] goods = {new Product("Хлеб", 60.0),
            new Product("Гречка", 92.60),
            new Product("Молоко", 90.0),
            new Product("Яблоко", 20.0),
            new Product("Тушенка", 385.50),
            new Product("Сгущенка", 127.80),
            new Product("Сахар", 75.0)
    };

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
                    if (itemCount <= 0) {
                        System.out.println("\nКоличество товара должно быть > 0");
                        continue;
                    }
                    goods[selectedItem - 1].incItemInBasket(itemCount);
                    totalPrice += itemCount * goods[selectedItem - 1].getPrice();
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

        System.out.print(" №       Название   Цена за шт.     В корзине        Стоимость\n" + "");
        int i = 0;
        while (i < goods.length) {
            System.out.printf("%2d. %13s %12.2f %10d %17.2f\n", i + 1,
                    goods[i].getName(), goods[i].getPrice(),
                    goods[i].getInBasket(), goods[i].getInBasket() * goods[i].getPrice());
            i++;
        }
        System.out.printf("ИТОГО Товаров в корзине на %10.2f\n\n", totalPrice);
        System.out.print("Добавьте товар в корзину (№ и количество<ENTER>) или введите end для завершения.\n");
    }

    static void printBasket() {
        System.out.print("Ваша корзина:\n");
        for (Product item : goods) {
            if (item.getInBasket() > 0) {
                System.out.printf("%13s %3d шт. %6.2f руб/шт. %8.2f в сумме\n",
                        item.getName(), item.getInBasket(), item.getPrice(),
                        item.getInBasket() * item.getPrice());
            }
        }
        System.out.printf("ИТОГО Товаров в корзине на %10.2f\n\n", totalPrice);
    }
}
