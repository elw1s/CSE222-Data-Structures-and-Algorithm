/**
 *
 * The HashMap implemented by using Open Addressing Hashing Method.
 *
 */

import java.util.NoSuchElementException;

public class HashMap<K,V> {
    private Entry<K,V>[] hashtable;
    private static final int DEFAULT_CAPACITY = 101;
    private double LOAD_THRESHOLD = 0.75;
    private int numKeys;
    private int numDeletes;
    private Entry<K,V> DELETED = new Entry<K,V>(null,null);

    /**
     * Constructor
     */
    public HashMap(){
        hashtable = new Entry[DEFAULT_CAPACITY];
    }

    /**
     *
     * @param key
     * @param value
     * @return Added value
     */
    public V put(K key, V value){
        int index = find(key);

        if(hashtable[index] == null){
            hashtable[index] = new Entry<K,V>(key,value);
            numKeys++;
            //size++;
            double loadFactor = (double) (numKeys + numDeletes) / hashtable.length;
            if(loadFactor > LOAD_THRESHOLD)
                rehash();
            return null;
        }
        V oldVal = hashtable[index].getValue();
        hashtable[index].setValue(value);
        return oldVal;
    }

    /**
     * Increases the size of hashtable
     */
    private void rehash(){
        Entry<K,V>[] oldTable = hashtable;

        hashtable = new Entry[2 * oldTable.length + 1];

        numKeys=0;
        numDeletes=0;
        for(int i=0; i < oldTable.length; i++){
            if((oldTable[i] != null) && (oldTable[i] != DELETED)){
                put(oldTable[i].getKey(),oldTable[i].getValue());
            }
        }
    }

    /**
     * Removes the element by using given key
     * @param key
     * @return The value of the removed element
     */
    public V remove(Object key){
        int index = find(key);

        if(hashtable[index] == null) return null;

        V removedVal = hashtable[index].getValue();
        DELETED = hashtable[index];
        hashtable[index] = null;
        numDeletes ++;
        numKeys --;
        return removedVal;

    }

    /**
     *
     * @return size of hash table
     */
    public int size(){
        return numKeys;
    }

    /**
     * Returns the index of the given key
     * @param key
     * @return index of the given key
     */
    private int find(Object key){
        int index = key.hashCode() % hashtable.length;
        if(index < 0)
            index += hashtable.length;

        while((hashtable[index] != null) && (!key.equals(hashtable[index].getKey()))){
            index++;
            if(index >= hashtable.length)
                    index = 0;
        }
        return index;
    }

    /**
     * Returns the value of the given key
     * @param key
     * @return The value of the given key
     */
    public V get(Object key){
        int index = find(key);

        if(hashtable[index] != null)
            return hashtable[index].getValue();
        else
            return null;
    }

    /**
     * Iterator
     * @return MapIterator
     */
    public MapIterator iterator(){
        return new MapIterator();
    }

    /**
     * calling an iterator at the given key's position
     * @param key
     * @return
     */
    public MapIterator iterator(K key){
        return new MapIterator(key);
    }

    /**
     * ITERATOR CLASS
     */
    public class MapIterator{
        private int currentItem = 0;
        private int lastItemReturned = -1;
        private int start = 0;
        private boolean iterated = false;

        public MapIterator(){
            start = getFirstIndex();
            currentItem = start;
        }

        public MapIterator(K key){
            start = find(key);
            currentItem = start;
        }


        public V next() throws NoSuchElementException{
            int index;
            if(!isThereNonIteratedKey()){
                index = getFirstIndex();
            }
            else
                index = currentItem;

            lastItemReturned = currentItem;
            currentItem = getNext(currentItem);
            iterated = true;

            return hashtable[index].getValue();
        }

        public V prev() throws NoSuchElementException{
            if(lastItemReturned == -1) throw new NoSuchElementException();

            if(currentItem == getFirstIndex()) return hashtable[getLastIndex()].getValue();

            return hashtable[lastItemReturned].getValue();
        }

        public boolean hasNext(){
            return isThereNonIteratedKey();
        }

        private int getLastIndex(){
            int lastIndex = -1;
            for(int i =0; i < hashtable.length; i++)
                if(hashtable[i] != null) lastIndex = i;
            return lastIndex;
        }

        private int getFirstIndex(){
            for(int i = 0; i < hashtable.length; i++)
                if(hashtable[i] != null){
                    return i;
                }
            return -1;
        }

        private int getNext(int index){
            for(int i = index + 1; i < hashtable.length; i++){
                if(hashtable[i] != null) return i;
            }
            return getFirstIndex();
        }

        private int getPrevious(int index){
            int prev = -1;
            for(int i = 0; i < index; i++){
                if(hashtable[i] != null) prev = i;
            }
            return getLastIndex();
        }

        private int getIndex(int index){
            return index % hashtable.length;
        }

        private boolean isThereNonIteratedKey(){
            return  !iterated || start != currentItem;
        }

    }

}
