import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        int counter = 0;

        int number = scanner.nextInt();
        while ((number = scanner.nextInt()) != 0) {
            sum += number;
            ++counter;
        }

        if (counter != 0) {
            System.out.printf(
                    "Среднее арифметическое %.2f%n",
                    (double) sum / counter
            );
        } else {
            System.out.println("Нет данных для расчёта");
        }

    }
}
