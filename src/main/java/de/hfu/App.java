package de.hfu;

import java.util.Scanner;

/**
 * Standardklasse
 */
public class App {
    /**
     * Wandelt eingegebenen Text in Grossbuchstaben um
     */
    public static void main(String[] args) {
        System.out.println("Geben etwas ein: ");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.next();

        System.out.println(eingabe.toUpperCase());
    }
}
