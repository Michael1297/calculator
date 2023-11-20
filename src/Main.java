import java.util.Scanner;

public class Main {
    private static final int NUMBER_1 = 0;
    private static final int NUMBER_2 = 2;
    private static final int OPERATOR = 1;

    public static String calc(String input) throws Exception {
        String[] elements = input.split(" ");
        if(elements.length != 3){
            throw new Exception("Invalid format of a math operation");
        }

        Number number1 = new Number(elements[NUMBER_1]);
        Number number2 = new Number(elements[NUMBER_2]);

        Number result = number1.calculate(number2, elements[OPERATOR]);
        return result.getNumber();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input:");
        String result = calc(scanner.nextLine());
        System.out.println(result);
    }
}