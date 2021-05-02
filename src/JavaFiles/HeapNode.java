public class HeapNode<E extends Comparable<E>> {
    private E data;
    private int occurence = 0;

    public HeapNode(E data){
        this.data = data;
    }
    public HeapNode(E data , int occ){
        this.data = data;
        this.occurence = occ;
    }
    public HeapNode(){
        this.data = null;
    }
    public E getData() {
        return data;
    }
    public void setData(E data) {
        this.data = data;
    }
    public void setOccurence(int occurence) {
        this.occurence = occurence;
    }
    public int getOccurence() {
        return occurence;
    }
    public int increaseOccurence(){
        return ++occurence;
    }
    public int decreaseOccurence(){
        return --occurence;
    }

}
