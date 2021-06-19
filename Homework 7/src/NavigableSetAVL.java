import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/***
 * NavigableSetAVL class
 * @param <E>
 */

public class NavigableSetAVL<E extends Comparable<E>>{

    private AVLTree<E> avlTree = new AVLTree<E>();

    /**
     * Constructor
     */
    public NavigableSetAVL(){
        //Intentionally Empty
    }

    /**
     * Constructor
     * @param tree
     */
    public NavigableSetAVL(BinaryTree<E> tree){
        avlTree = (AVLTree<E>) tree;
    }

    /**
     * Inserts the given item to the NavigableSet
     * @param item
     * @return true if the operation is successful
     */
    public boolean insert(E item){
        if(item != null) return avlTree.add(item);
        return false;
    }

    /***
     * Returns a set which is sorted and consists the items which are less than the given element
     * @param toElement
     * @return Returns a set which is sorted and consists the items which are less than the given element
     */
    public SortedSet<E> headSet(E toElement){
        TreeSet<E> set = new TreeSet<>();
        set.addAll(avlTree.toArray(toElement , true));
        return set;
    }
    /***
     * Returns a set which is sorted and consists the items which are higher than the given element
     * @param fromElement
     * @return Returns a set which is sorted and consists the items which are higher than the given element
     */
    public SortedSet<E> tailSet(E fromElement){
        TreeSet<E> set = new TreeSet<>();
        set.addAll(avlTree.toArray(fromElement , false));
        return set;
    }

    @Override
    public String toString() {
        return avlTree.toString();
    }

    /**
     * Iterator
     * @return an iterator
     */
    public Iterator iterator(){
        return new Iterator();
    }

    /**
     * Iterator Class
     * @param <E>
     */
    public class Iterator<E extends Comparable<E>>{
        ArrayList<E> arr = (ArrayList<E>) avlTree.toArray();
        int size = arr.size();
        int curr = 0;
        int lastItem = -1;

        public Iterator(){
            arr.sort((o1, o2) -> {
                if(o1.compareTo(o2) == 0) return 0;
                else if(o1.compareTo(o2) > 0) return 1;
                else return -1;
            });
        }

        public E next(){
            if(!hasNext()) return null;
            curr++;
            return arr.get(++lastItem);
        }
        public E remove(){
            curr--;
            return arr.remove(lastItem--);
        }

        public boolean hasNext(){
            return size != curr;
        }

    }





}
