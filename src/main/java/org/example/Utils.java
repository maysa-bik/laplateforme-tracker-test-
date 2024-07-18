package org.example;
import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }


    public static float getFloatInput(String prompt) {
        System.out.print(prompt);
        return Float.parseFloat(scanner.nextLine());
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
