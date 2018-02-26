import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        for (int i = a; i <= b; i++){
            int Sqrt = (int) Math.sqrt((double) i);
            if (Sqrt * Sqrt == i){
                System.out.print(i + " ");
            }

        }
    }
}
