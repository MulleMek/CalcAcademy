import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите данные:");
        Scanner scan = new Scanner(System.in);

        String str = scan.nextLine();
        try {
            Calc calculator = new Calc(str);
            String result = calculator.go();
            System.out.println(str + " = " + result);
        }catch (Exception e){
            System.out.println("Ошибка данных: " + e.getMessage());
        }
    }
}