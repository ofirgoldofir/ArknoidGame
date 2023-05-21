// Ofir Goldberg 315141325
package Interfaces;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hit listener to hit events.
     *
     * @param hl the hl
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener from the list of listeners to hit events.
     *
     * @param hl the hl
     */
    void removeHitListener(HitListener hl);
}