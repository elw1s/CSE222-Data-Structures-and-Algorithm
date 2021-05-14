/**
 *
 * Interface for HashMap (Taken from the book)
 *
 */
public interface KWHashMap<K,V>{
    public V get(Object key);
    public boolean isEmpty();
    public V put(K key , V value) throws NoSuchFieldException;
    public V remove(Object key);
    public int size();
}
