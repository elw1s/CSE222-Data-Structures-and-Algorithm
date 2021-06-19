/** HEAP CLASS
*   USED FOR TREE STRUCTURE
 *   SAME AS OTHER HOMEWORKS
*  */

public class Heap{
    private Product[] heap;
    private int size = 0;
    private final int DEFAULT_CAPACITY = 10;


    public Heap(int capacity){
        heap = new Product[capacity];
    }

    public Heap(){
        heap = new Product[DEFAULT_CAPACITY];
    }

    public Heap(Heap other){
        this.heap = other.heap;
        this.size = other.size;
    }

    public Heap(Product[] heap , int size){
        this.heap = heap;
        this.size = size;
    }

    public Product getMax(){
        if(heap[0] != null)
            return heap[0];
        return null;
    }

    public void removeMax(){
        remove(0);
    }

    public void remove(int index){
        if(index < 0 || index >= size) return;

        int parentElement = getParent(index);

        heap[index] = heap[size - 1];

        if(index == 0 || Double.parseDouble(heap[parentElement].productPercentage()) >  Double.parseDouble(heap[index].productPercentage())){
            heapifyBelow(index,size - 1);
        }
        else if(Double.parseDouble(heap[parentElement].productPercentage()) < Double.parseDouble(heap[index].productPercentage())){
            heapifyAbove(index);
        }
        heap[--size] = null;

    }

    public Product insert(Product item){
        if(size == heap.length){
            reallocate();
        }
        heap[size] = item;
        heapifyAbove(size++);
        return item;
    }

    private void heapifyAbove(int index){
        Product element = heap[index];
        while(index > 0 && Double.parseDouble(heap[getParent(index)].productPercentage()) > Double.parseDouble(element.productPercentage())){
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }
        heap[index] = element;
    }

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
                    swapIndex = Double.parseDouble(heap[leftChildIndex].productPercentage()) > Double.parseDouble(heap[rightChildIndex].productPercentage()) ? leftChildIndex : rightChildIndex;
                }
                if(Double.parseDouble(heap[index].productPercentage()) < Double.parseDouble(heap[swapIndex].productPercentage())){
                    Product temp = heap[index];
                    heap[index] = heap[swapIndex];
                    heap[swapIndex] = temp;
                }
                else break;
                index = swapIndex;
            }
            else break;
        }
    }

    private void reallocate(){
        Product[] temp = new Product[heap.length * 2];

        for(int i = 0; i < size; i++){
            temp[i] = heap[i];
        }
        heap = temp;
    }

    public Heap sort(Heap tempHeap){
        int lastElementIndex = tempHeap.size() - 1;
        for(int i = 0 ;  i < lastElementIndex; i++){
            Product temp = tempHeap.heap[0];
            tempHeap.heap[0] = tempHeap.heap[lastElementIndex - i];
            tempHeap.heap[lastElementIndex - i] = temp;
            tempHeap.heapifyBelow(0 , lastElementIndex - i - 1);
        }
        return tempHeap;
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


}
