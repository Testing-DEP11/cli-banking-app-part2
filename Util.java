import java.util.Scanner;

public class Util {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        String name = "1025";

        String number = "SDB-01025";
        System.out.println(number.substring(4).equals(name));

        // for (int i = 0; i < number.length(); i++) {
        //     if(number.substring(i).equals(name)){ 
        //         System.out.println("found"); 
        //         System.out.println(number.substring(i));
        //     }
        // }

        System.out.printf("Enter value: ");
        String input = SCANNER.nextLine();
        String add = "";

        add += input.length()== 5 ? input:"0".repeat(5-input.length())+input;
        System.out.println(add);

        // System.out.println( (int)Math.floor(Math.random()*10000));

    }
}
