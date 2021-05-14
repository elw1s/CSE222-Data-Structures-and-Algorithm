/**
 *
 * The HashMap implemented by using TreeSet.
 *
 */
import java.util.TreeSet;

public class HashMapChainTree<K,V> implements KWHashMap<K,V>{
    private TreeSet<Entry<K,V>>[] hashtable;
    private int numKeys;
    private static final int DEFAULT_CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 3.0;

    /**
     * Constructor
     */
    public HashMapChainTree(){
        hashtable = new TreeSet[DEFAULT_CAPACITY];
    }
    /**
     * Returns the value of the given key
     * @param key
     * @return The value of the given key
     */
    @Override
    public V get(Object key) {
        int index = key.hashCode() % hashtable.length;
        if(index < 0)
            index += hashtable.length;
        if(hashtable[index] == null)
            return null;

        for(Entry<K,V> nextItem : hashtable[index]){
            if(nextItem.getKey().equals(key))
                return nextItem.getValue();
        }
        return null;
    }
    /**
     *
     * @return true if the hashmap is empty
     */
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }
    /**
     *
     * @param key
     * @param value
     * @return Added value
     */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % hashtable.length;
        if(index < 0)
            index += hashtable.length;
        if(hashtable[index] == null){
            hashtable[index] = new TreeSet<Entry<K,V>>();
        }

        for(Entry<K,V> nextItem : hashtable[index]){
            if(nextItem.getKey().equals(key)){
                V oldVal = nextItem.getValue();
                nextItem.setValue(value);
                return oldVal;
            }
        }
        hashtable[index].add(new Entry<K,V>(key,value));
        numKeys++;
        if(numKeys > (LOAD_THRESHOLD * hashtable.length))
            rehash();
        return null;
    }
    /**
     * Increases the size of hashtable
     */
    private void rehash(){
        TreeSet<Entry<K,V>>[] oldTable = hashtable;
        hashtable = new TreeSet[2 * oldTable.length + 1];
        numKeys=0;
        for(int i=0; i < oldTable.length; i++){
            if(oldTable[i] != null){
                for(Entry<K,V> entry : oldTable[i]) {
                    if ((entry != null)) {
                        put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
    }
    /**
     * Removes the element by using the given key
     * @param key
     * @return The value of the removed entry
     */
    @Override
    public V remove(Object key) {
        int index = key.hashCode() % hashtable.length;
        if(index < 0)
            index += hashtable.length;
        if(hashtable[index] == null) return null;

        for(Entry<K,V> entry : hashtable[index]){
            if(entry.getKey().equals(key)){
                V returnItem = entry.getValue();
                hashtable[index].remove(entry);
                numKeys--;
                if(hashtable[index].size() == 0){
                    hashtable[index] = null;
                }
                return returnItem;
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
        return numKeys;
    }
}
