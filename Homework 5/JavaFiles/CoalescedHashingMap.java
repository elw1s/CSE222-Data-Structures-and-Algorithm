/**
 *
 * The HashMap implemented by using Coalesced Hashing Method.
 *
 */

import java.util.NoSuchElementException;

public class CoalescedHashingMap<K,V> implements KWHashMap<K,V>{
    private Entry<K,V>[] hashtable;
    private int DEFAULT_CAPACITY = 10;
    private int size = 0;

    /**
     *
     * Constructor - Zero Parameter
     *
     */
    public CoalescedHashingMap(){
        hashtable = new Entry[DEFAULT_CAPACITY];
    }
    /**
     *
     * Constructor - with Parameter
     *
     */
    public CoalescedHashingMap(int DEFAULT_CAPACITY){
        this.DEFAULT_CAPACITY = DEFAULT_CAPACITY;
        hashtable = new Entry[DEFAULT_CAPACITY];
    }
    /**
     * Returns the value of the given key
     * @param key
     * @return The value of the given key
     */
    @Override
    public V get(Object key) {
        for(int i =0; i < DEFAULT_CAPACITY; i++){
            if(hashtable[i] != null && hashtable[i].getKey().equals(key)){
                return hashtable[i].getValue();
            }
        }
        return null;
    }
    /**
     *
     * @return true if the hashmap is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Prints the HashMap
     */
    public void print(){
        System.out.println("Hash  Key  Next");
        for(int i = 0; i < hashtable.length; i++){
            System.out.print(i + " ");
            if(hashtable[i] != null)
                System.out.println(hashtable[i].getKey() + "  " + hashtable[i].getNext());
            else
                System.out.println("null  null");
        }
    }
    /**
     *
     * @param key
     * @param value
     * @return Added value
     * @throws NoSuchFieldException
     */
    @Override
    public V put(K key, V value) throws NoSuchFieldException {
        if(size == DEFAULT_CAPACITY) throw new NoSuchFieldException("HashMap is full");

        int index = Math.abs(key.hashCode() % DEFAULT_CAPACITY);

        if(hashtable[index] == null){
            hashtable[index] = new Entry<K,V>(key,value);
            size++;
            return value;
        }

        int next = getNext(key);
        int prev = getPrev(key);
        hashtable[next] = new Entry<K,V>(key,value);
        size++;
        hashtable[prev].setNext(next);
        return value;

    }

    /**
     *
     * @param key
     * @return Next index of the given Key
     */
    private int getNext(K key){
        int hash = key.hashCode();
        int i = 0;
        while(hashtable[Math.abs((hash + (i*i)) % DEFAULT_CAPACITY)] != null) i++;
        return Math.abs((hash + (i*i)) % DEFAULT_CAPACITY);
    }

    /**
     *
     * @param key
     * @return Previous index of the given Key
     */
    private int getPrev(K key){
        int hash = key.hashCode();
        int prev = Math.abs(hash % DEFAULT_CAPACITY);
        int i = 0;
        if(prev  == Math.abs(hashtable[prev].getKey().hashCode() % DEFAULT_CAPACITY)) {
            while (hashtable[Math.abs((hash + (i * i)) % DEFAULT_CAPACITY)] != null) {
                if (Math.abs(hashtable[Math.abs((hash + (i * i)) % DEFAULT_CAPACITY)].getKey().hashCode() % DEFAULT_CAPACITY) == Math.abs(hashtable[prev].getKey().hashCode() % DEFAULT_CAPACITY)) {
                    prev = Math.abs((hash + (i * i)) % DEFAULT_CAPACITY);
                } else if (Math.abs(hashtable[Math.abs((hash + (i * i)) % DEFAULT_CAPACITY)].getKey().hashCode() % DEFAULT_CAPACITY) != Math.abs(hashtable[prev].getKey().hashCode() % DEFAULT_CAPACITY) && hashtable[prev].getNext() != -1) {
                    prev = hashtable[prev].getNext();
                }
                i++;
            }
        }
        else{
            while(hashtable[prev].getNext() != -1){
                prev = hashtable[prev].getNext();
            }
        }
        return prev;
    }
    /**
     * Removes the element by using the given key
     * @param key
     * @return The value of the removed entry
     *
     */
    @Override
    public V remove(Object key){
        int index = Math.abs(key.hashCode() % DEFAULT_CAPACITY);
        if(hashtable[index] == null) return null;

        for(int i = 0; i < DEFAULT_CAPACITY; i++){
            if(hashtable[i] != null && hashtable[i].getKey().equals(key)){
                V oldVal = hashtable[i].getValue();
                if(hashtable[i].getNext() == -1){
                    hashtable[i] = null;
                }
                else{
                    int k = i;
                    while(hashtable[k].getNext() != -1){
                        int next = hashtable[k].getNext();
                        hashtable[k] = hashtable[next];
                        k = next;
                    }
                    hashtable[k] = null;
                }
                size--;
                return oldVal;
            }
        }
        return null;
    }
    /**
     *
     * @return Size of the hashmap
     */
    @Override
    public int size() {
        return size;
    }
}
