import java.util.NoSuchElementException;

public class Heap<E extends Comparable<E>> {
    private E[] heap;
    private int size = 0;
    private final int DEFAULT_CAPACITY = 10;

    /**
     * Constructor
     * @param capacity
     */
    public Heap(int capacity){
        heap = (E[])new Comparable[capacity];
    }
    /**
     * Constructor
     */
    public Heap(){
        heap = (E[])new Comparable[DEFAULT_CAPACITY];
    }
    /**
     * Constructor
     */
    public Heap(Heap<E> other){
        this.heap = other.heap;
        this.size = other.size;
    }
    /**
     * Constructor
     * @param heap
     * @param size
     */
    public Heap(E[] heap , int size){
        this.heap = heap;
        this.size = size;
    }
    /**
     * Inserts the given item to the heap and heapifies the heap
     * @param item
     * @return
     */
    public E insert(E item){
        if(size == heap.length){
            reallocate();
        }
        heap[size] = item;
        heapifyAbove(size++);
        return item;
    }
    /**
     * Heapifies the items above of the given index
     * @param index
     */
    private void heapifyAbove(int index){
        E element = heap[index];
        while(index > 0 && heap[getParent(index)].compareTo(element) < 0){
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }
        heap[index] = element;
    }
    /**
     * Heapifies the items below of the given index
     * @param index
     */
    private void heapifyBelow(int index , int lastElementIndex){
        int swapIndex = 0;
        while(index > 0 ||index <= lastElementIndex){
            int leftChildIndex = getLeftChild(index);
            int rightChildIndex = getRightChild(index);

            if(leftChildIndex <= lastElementIndex){
                if(rightChildIndex > lastElementIndex){
                    swapIndex = leftChildIndex;
                }
                else{
                    swapIndex = (heap[leftChildIndex].compareTo(heap[rightChildIndex]) > 0) ? leftChildIndex : rightChildIndex;
                }
                if(heap[index].compareTo(heap[swapIndex]) < 0){
                    E temp = heap[index];
                    heap[index] = heap[swapIndex];
                    heap[swapIndex] = temp;
                }
                else break;
                index = swapIndex;
            }
            else break;
        }
    }
    /**
     * Removes item regarding to the given index
     */
    public E remove(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

        E removedElement = heap[index];
        heap[index] = heap[size - 1];

        if(index == 0 || heap[getParent(index)].compareTo(heap[index]) > 0){
            heapifyBelow(index,size - 1);
        }
        else if(heap[getParent(index)].compareTo(heap[index]) < 0){
            heapifyAbove(index);
        }
        heap[--size] = null;
        return removedElement;
    }

    /**
     * Reallocates new space for array
     */
    private void reallocate(){
        E[] temp = (E[]) new Comparable[heap.length * 2];

        for(int i = 0; i < size; i++){
            temp[i] = heap[i];
        }
        heap = temp;
    }
    /**
     * Returns the root of the heap
     */
    public E peek() throws IndexOutOfBoundsException{
        if(size == 0) throw new IndexOutOfBoundsException();

        return heap[0];
    }
    /**
     * Checks whether or not the heap contains the given element
     * @param element
     * @return
     * @throws NoSuchElementException
     */
    public boolean contains(E element) throws NoSuchElementException{
        if(element == null) throw new NoSuchElementException();
        for(int i = 0; i < size; i++){
            if(heap[i].compareTo(element) == 0) return true;
        }
        return false;
    }

    /**
     * Merges two heap and returns it
     * @param other
     * @return
     */
    public Heap<E> merge(Heap<E> other){
        E[] temp = (E[]) new Comparable[heap.length + other.capacity()];

        for(int i = 0; i < size; i++){
            temp[i] = heap[i];
        }
        for(int i = 0; i < other.size(); i++){
            temp[size + i] = other.heap[i];
        }

        int newSize = size + other.size();
        Heap<E> tempHeap = new Heap(temp , newSize);

        for(int i = (newSize/2) - 1; i >= 0; i--){
            tempHeap.heapifyBelow(i,newSize);
        }

        return tempHeap;
    }
    /**
     * Removes the largest element in the heap
     * @param index
     * @return
     */
    public E removeLargest(int index){
        if(index <= 0 || index > size) throw new IndexOutOfBoundsException();
        if(!isSorted()) sort(this);
        E removedItem  = remove(size - index);
        heapifyBelow(0,size-1);
        for(int i = 0; i < size; i++){
            heapifyAbove(i);
        }
        return removedItem;
    }
    /**
     * Sorts the heap
     * @param tempHeap
     * @return
     */
    public Heap<E> sort(Heap<E> tempHeap){
        int lastElementIndex = tempHeap.size() - 1;
        for(int i = 0 ;  i < lastElementIndex; i++){
            E temp = tempHeap.heap[0];
            tempHeap.heap[0] = tempHeap.heap[lastElementIndex - i];
            tempHeap.heap[lastElementIndex - i] = temp;
            tempHeap.heapifyBelow(0 , lastElementIndex - i - 1);
        }
        return tempHeap;
    }
    /**
     * Checks that whether heap is sorted or not
     * @return
     */
    public boolean isSorted(){
        for(int i = 1; i < size; i++){
            if(heap[i-1].compareTo(heap[i]) > 0) return false;
        }
        return true;
    }


    private int getParent(int index){
        return (index - 1 ) / 2;
    }

    public int size(){
        return size;
    }

    public int capacity(){
        return heap.length;
    }

    private int getRightChild(int index){
        return (index * 2) + 2;
    }

    private int getLeftChild(int index){
        return (index * 2) + 1;
    }

    public void print(){
        for(int i = 0; i < size; i++){
            System.out.print(heap[i] + " ");
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
            return (E) heap[curr++];
        }

        public E set(E item) throws IndexOutOfBoundsException{
            if(lastItemReturned < 0 ) throw new IndexOutOfBoundsException();

            E changedItem = (E) heap[lastItemReturned];
            heap[lastItemReturned] = item;
            return changedItem;
        }

        public void remove() throws NoSuchElementException{
            if(lastItemReturned < 0 ) throw new NoSuchElementException();
            Heap.this.remove(lastItemReturned);
            lastItemReturned--;
            curr--;
        }

    }

}
