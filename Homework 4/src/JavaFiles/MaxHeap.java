import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MaxHeap<E extends Comparable<E>> {
    private ArrayList<HeapNode<E>> heap;
    private int size = 0;
    private final int DEFAULT_CAPACITY = 7;

    public MaxHeap(){
        heap = new ArrayList<HeapNode<E>>(DEFAULT_CAPACITY);
    }

    public MaxHeap(MaxHeap<E> other){
        this.heap = other.heap;
        this.size = other.size;
    }

    public MaxHeap(E item){
        heap = new ArrayList<HeapNode<E>>(DEFAULT_CAPACITY);
        heap.add(0,new HeapNode<E>(item , 1));
        size++;
    }
    /**
     * Returns the data of the given index
     * @param index
     * @return
     */
    public E getHeapData(int index){
        if(index < 0 && index > DEFAULT_CAPACITY && heap.get(index).getData() == null) return null;

        return heap.get(index).getData();
    }
    /**
     * Returns the occurence of the given index
     */
    public int getOccurence(int index) {
        if(index < 0 && index > DEFAULT_CAPACITY && heap.get(index).getData() == null) return -1;

        return heap.get(index).getOccurence();
    }
    public boolean isFull(){
        return size == DEFAULT_CAPACITY;
    }
    /**
     * Returns the index of the given element
     */
    public int getIndex(E item){
        for(int i = 0; i < size; i++){
            if(item.compareTo(heap.get(i).getData()) == 0) return i;
        }
        return -1;
    }
    /**
     * Returns the node of the given index
     * @param index
     * @return
     */
    public HeapNode<E> getNode(int index){
        if(index < 0 || index > size) return null;

        return heap.get(index);
    }
    /**
     * Inserts the given item to the heap and heapifies the heap
     * @param item
     * @return
     */
    public E insert(E item){

        if(contains(item)){
            heap.get(getIndex(item)).increaseOccurence();
        }
        else{
            heap.add(new HeapNode<E>(item));
            heap.get(size).increaseOccurence();
            heapifyAbove(size++);
        }

        return item;
    }
    /**
     * Swaps the two nodes
     * @param node
     * @return
     */
    public E swap(HeapNode<E> node){
        heap.add(node);
        heapifyAbove(size++);
        return node.getData();
    }
    /**
     * Heapifies the above of the given index
     * @param index
     */
    private void heapifyAbove(int index){
        HeapNode<E> temp = new HeapNode<E>(heap.get(index).getData(), heap.get(index).getOccurence());
        while(index > 0 && heap.get(getParent(index)).getData().compareTo(temp.getData()) < 0){
            heap.set(index,heap.get(getParent(index)));
            index = getParent(index);
        }
        heap.set(index,temp);
    }
    /**
     * Heapifies the below of the given index
     * @param index
     */
    private void heapifyBelow(int index , int lastElementIndex){
        int swapIndex = 0;
        while(index <= lastElementIndex){
            int leftChildIndex = getLeftChild(index);
            int rightChildIndex = getRightChild(index);

            if(leftChildIndex <= lastElementIndex){
                if(rightChildIndex > lastElementIndex){
                    swapIndex = leftChildIndex;
                }
                else{
                    swapIndex = (heap.get(leftChildIndex).getData().compareTo(heap.get(rightChildIndex).getData()) > 0) ? leftChildIndex : rightChildIndex;
                }
                if(heap.get(index).getData().compareTo(heap.get(swapIndex).getData()) < 0){
                    HeapNode<E> temp = new HeapNode<E>(heap.get(index).getData(), heap.get(index).getOccurence());
                    heap.set(index,heap.get(swapIndex));
                    heap.set(swapIndex,temp);
                }
                else break;
                index = swapIndex;
            }
            else break;
        }
    }
    /**
     * Removes the given index
     * @param index
     * @return
     */
    public E remove(int index){
        if(index < 0 || index >= size) return null;

        E removedElement = heap.get(index).getData();
        int parentElement = getParent(index);

        if(heap.get(index).getOccurence() > 1) heap.get(index).decreaseOccurence();
        else{
            heap.set(index,heap.get(size - 1));

            if(index == 0 || heap.get(parentElement).getData().compareTo(heap.get(index).getData()) > 0){
                heapifyBelow(index,size - 1);
            }
            else if(heap.get(parentElement).getData().compareTo(heap.get(index).getData()) < 0){
                heapifyAbove(index);
            }
            heap.remove(--size);
        }

        return removedElement;
    }
    /**
     * Returns the data which is in the root
     * @return
     * @throws IndexOutOfBoundsException
     */
    public E peek() throws IndexOutOfBoundsException{
        if(size == 0) throw new IndexOutOfBoundsException();

        return heap.get(0).getData();
    }
    /**
     * Checks that whether heap contains the given item or not
     */
    public boolean contains(E element) throws NoSuchElementException {
        if(element == null) throw new NoSuchElementException();
        for(int i = 0; i < size; i++){
            if(heap.get(i).getData().compareTo(element) == 0) return true;
        }
        return false;
    }

    private int getParent(int index){
        return (index - 1 ) / 2;
    }

    public int size(){
        return size;
    }

    private int getRightChild(int index){
        return (index * 2) + 2;
    }

    private int getLeftChild(int index){
        return (index * 2) + 1;
    }

    public void print(){
        for(int i = 0; i < size; i++){
            System.out.print(heap.get(i).getData() + " ");
        }
        System.out.println();
    }

    public Iterator iterator(){
        return new Iterator();
    }
    /**
     * Iterator
     */
    public class Iterator{
        private int lastItemReturned = -1;
        private int curr = 0;


        public boolean hasNext(){
            return size != curr;
        }

        public E next() throws NoSuchElementException{
            if(!hasNext()) throw new NoSuchElementException();

            lastItemReturned++;
            return (E) heap.get(curr++).getData();
        }

        public void remove() throws NoSuchElementException{
            if(lastItemReturned < 0 ) throw new NoSuchElementException();
            MaxHeap.this.remove(lastItemReturned);
            lastItemReturned--;
            curr--;
        }

    }

}

