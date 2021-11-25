package de.hfu;

/**
 * Warteschlange die int Werte abspeichert
 */
public class Queue {
    private final int[] queue;
    private int head;
    private int tail;
    private final int maxqueuelength;

    /**
     * Konstruktor der Queue Klasse
     *
     * @param max maximale Länge der Schlange
     */
    public Queue(int max) {
        if (max < 1) throw new IllegalArgumentException("Queue Arraylength " + max);
        maxqueuelength = max;
        queue = new int[max];
        head = 0;
        tail = -1;
    }

    /**
     * Fügt a hinten in die Warteschlange ein, überschreibt bei voller Liste die letzte Position
     *
     * @param a soll hinzugefügt werden
     */
    public void enqueue(int a) {
        if ((tail - head) < maxqueuelength - 1) {
            tail++;
        }
        queue[tail % maxqueuelength] = a;
    }

    /**
     * Löscht das Element was am längsten in der Warteschlange ist
     *
     * @return gelöschtes Element
     */
    public int dequeue() {
        if (tail < head) {
            throw new IllegalStateException("dequeue on empty queue");
        }
        return queue[(head++) % maxqueuelength];
    }

}