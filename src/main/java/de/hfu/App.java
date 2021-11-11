package de.hfu;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Geben etwas ein: ");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.next();

        System.out.println(eingabe.toUpperCase());
    }
}
