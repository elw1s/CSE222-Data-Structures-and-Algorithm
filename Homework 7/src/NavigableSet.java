/**
 * NavigableSet Interface
 *
 * */
public interface NavigableSet<E extends Comparable<E>> {
    public E insert(E item) throws IllegalArgumentException;
    public Object iterator();
}
