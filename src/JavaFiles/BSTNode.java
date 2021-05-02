public class BSTNode<E extends Comparable<E>>{
    private MaxHeap<E> data;
    private BSTNode<E> leftChild;
    private BSTNode<E> rightChild;

    /**
     * Inserts value to the Heap
     * @param value
     */
    public void insert(E value){
        if(data.isFull() && !data.contains(value)){
            if(value.compareTo(this.data.peek()) < 0){
                if(leftChild == null) leftChild = new BSTNode<E>(value);
                else leftChild.insert(value);
            }
            if(value.compareTo(this.data.peek()) > 0){
                if(rightChild == null) rightChild = new BSTNode<E>(value);
                else rightChild.insert(value);
            }
        }
        else this.data.insert(value);
    }
    /**
     * Returns occurence of the given item
     * @param item
     * @return
     */
    public int getOccurence(E item){
        for(int i = 0; i < data.size(); i++){
            if(data.getHeapData(i).compareTo(item) == 0) return data.getOccurence(i);
        }
        int left = 0, right = 0;
        if(this.leftChild != null) {
            left = this.leftChild.getOccurence(item);
        }
        if(this.rightChild != null){
            right = this.rightChild.getOccurence(item);
        }
        return right != 0 ? right : left;

    }
    /**
     * Returns the mode of the BSTHeapTree
     * @param mode
     * @return
     */
    public HeapNode<E> findMode(HeapNode<E> mode){
        for(int i = 0; i < data.size(); i++){
            if(data.getOccurence(i) > mode.getOccurence()){
                mode.setData(data.getHeapData(i));
                mode.setOccurence(data.getOccurence(i));
            }
        }

        HeapNode<E> left = new HeapNode<E>(null,0);
        HeapNode<E> right = new HeapNode<E>(null,0);

        if(this.rightChild != null) right = this.rightChild.findMode(mode);
        if(this.leftChild != null) left = this.leftChild.findMode(mode);

        if(this.leftChild == null && this.rightChild == null) return mode;

        return right.getOccurence() > left.getOccurence() ? right : left;

    }
    /**
     * Finds min value
     * @return
     */
    public MaxHeap<E> findMin(){
        if(this.leftChild != null) return this.leftChild.findMin();
        else return this.data;
    }
    /**
     * Finds max value
     * @return
     */
    public MaxHeap<E> findMax(){
        if(this.rightChild != null) return this.rightChild.findMax();
        else return this.data;
    }
    /**
     * Finds max value
     * @return
     */
    public BSTNode max(){
        if(this.rightChild != null) return this.rightChild.max();
        else return this;
    }
    /**
     * Finds min value
     * @return
     */

    public BSTNode min(){
        if(this.leftChild != null) return this.leftChild.min();
        else return this;
    }
    
    public void traverseInOrder(){
        if(leftChild != null) leftChild.traverseInOrder();
        System.out.print(this.data + " ");
        if(rightChild != null) rightChild.traverseInOrder();
    }

    public BSTNode(E value){
        this.data = new MaxHeap<E>(value);
    }

    public void setLeftChild(BSTNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(BSTNode<E> rightChild) {
        this.rightChild = rightChild;
    }

    public BSTNode<E> getLeftChild() {
        return leftChild;
    }

    public BSTNode<E> getRightChild() {
        return rightChild;
    }

    public MaxHeap<E> getData() {
        return data;
    }

    public void setData(MaxHeap data){
        this.data = data;
    }
}
