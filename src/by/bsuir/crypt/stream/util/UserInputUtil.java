package by.bsuir.crypt.stream.util;

import java.util.Scanner;

public class UserInputUtil {

    public static long inputKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter key: ");
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.printf("\"%s\" is not a valid number.\n", input);
        }
        return scanner.nextInt();
    }

    public static String inputFileName(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (!scanner.hasNextLine()) {
            System.out.println("Is not a valid input.\n");
        }
        return scanner.nextLine();
    }
}
