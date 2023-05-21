//Ofir Goldberg 315141325
package Helpers;
/**
 * The class Counter.
 */
public class Counter {
    private int counter;

    /**
     * Constructor of a new Counter.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Constructor of a new Counter.
     *
     * @param number the counter.
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * Add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Get current count.
     *
     * @return the int
     */
    public int getValue() {
        return this.counter;
    }

    /**
     * Set current count.
     *
     * @param newCounter the number
     */
    public void setValue(int newCounter) {
        this.counter = newCounter;
    }
}