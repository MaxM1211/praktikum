package de.hfu;

/**
 * Hilfsfunktionen
 */
public class Util {
    /**
     * Prüft ob gegebener Monat im ersten Halbjahr ist oder nicht
     *
     * @param monat übergebener Monat
     * @return Wahrheitswert true wenn im ersten Halbjahr
     */
    public static boolean istErstesHalbjahr(int monat) {
        if ((monat < 1) || (monat > 12)) throw new IllegalArgumentException();
        return monat <= 6;
    }

}