import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * NavigableSetSkipList class
 * @param <E>
 */
public class NavigableSetSkipList <E extends Comparable<E>>{

    SkipList<E> skipList = new SkipList<E>();

    /**
     * Inserts the given item to the NavigableSet
     * @param item
     * @return the inserted Element
     * @throws IllegalArgumentException
     */
    public E insert(E item) throws IllegalArgumentException{
        if(item == null) throw new IllegalArgumentException();
        skipList.add(item);
        return item;
    }

    /**
     * Deletes the given item from the Navigable Set
     * @param item
     * @return the deleted item
     * @throws IllegalArgumentException
     */
    public E delete(E item) throws IllegalArgumentException{
        if(item == null) throw new IllegalArgumentException();
        return skipList.remove(item);
    }

    /**
     * Descending Iterator
     * @return an iterator
     */
    public Iterator descendingIterator(){
        return new Iterator();
    }

    /**
     * Iterator Class
     */
    public class Iterator{
        int curr = skipList.size() - 1;
        int lastItemReturned = skipList.size();
        ArrayList<E> arr = skipList.toArray();

        public Iterator(){ }

        public E next(){
            if(!hasNext()) return null;
            curr--;
            return arr.get(--lastItemReturned);
        }

        public boolean hasNext(){
            return -1 != curr;
        }

        public E remove() throws NoSuchElementException{
            if(lastItemReturned == skipList.size() + 1) throw new NoSuchElementException();
            return arr.remove(lastItemReturned);
        }

    }

}
