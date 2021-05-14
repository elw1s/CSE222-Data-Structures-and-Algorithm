/**
 *
 * The Entry class to hold the KEY and VALUE (and also NEXT for Coalesced Hashing Method)
 *
 */

public class Entry<K ,V> implements Comparable<Entry<K,V>> {
    private K key;
    private V value;
    private int next = -1;

    public Entry(K key,V value){
        this.key = key;
        this.value = value;
    }
    public Entry(K key,V value , int next){
        this.key = key;
        this.value = value;
        this.next = next;
    }
    public int getNext() {
        return next;
    }
    public void setNext(int next) {
        this.next = next;
    }
    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Compares the parameter and calling object regarding their keys.
     * @param o
     * @return 0 when the given parameter's key is equal to the calling object's key
     */
    @Override
    public int compareTo(Entry<K, V> o) {
        if(o.getKey().equals(this.getKey())) return 0;
        return 1;
    }
}
