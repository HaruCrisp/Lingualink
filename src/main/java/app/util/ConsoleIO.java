package app.util;

import java.util.Scanner;

public class ConsoleIO {
    private final Scanner sc = new Scanner(System.in);

    public void println(String s) { System.out.println(s); }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}
