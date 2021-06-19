/**
 * KWLinkedList class taken from Book
 *
 * */

public class KWLinkedList<E> {
    private Node<E> head;
    private int size;

    public KWLinkedList(){
        head = null;
        size = 0;
    }

    public int size(){
        return size;
    }
    //O(1)
    public void addFirst(E item){
        head = new Node<E>(item);
        size ++;
    }
    //O(1)
    private void addAfter(Node<E> node, E item){
        node.setNext(new Node<E>(item, node.getNext()));
        size ++;
    }
    //O(n)
    private E removeAfter(Node<E> node) {
        Node<E> temp = node.getNext();
        if(temp != null){
            node.setNext(temp.getNext());
            size --;
            return temp.getData();
        }
        else return null;
    }
    //O(n)
    public E remove(int index){
        Node<E> node = getNode(index-1);
        return removeAfter(node);
    }
    //O(n^2)
    public boolean remove(Object o){
        for(int i = 0; i < size; i++){
            if(get(i).equals(o)){
                remove(i);
                return true;
            }
        }
        return false;
    }
    //O(1)
    public E remove(){
        return removeFirst();
    }
    //O(1)
    private E removeFirst(){
        Node<E> temp = head;
        if(head != null){
            head = head.getNext();
        }
        if(temp != null){
            size --;
            return temp.getData();
        }
        else return null;
    }
    //O(n)
    private Node<E> getNode(int index){
        Node<E> node = head;
        for(int i = 0; i < index && node != null; i++){
            node = node.getNext();
        }
        return node;
    }
    //O(n)
    public E get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        return node.getData();
    }
    //O(n)
    public E set(int index , E newValue) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        E result = node.getData();
        node.setData(newValue);
        return result;
    }
    //O(n)
    public void add(int index , E item) throws IndexOutOfBoundsException{
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if(index == 0){
            addFirst(item);
        }
        else{
            Node<E> node = getNode(index - 1);
            addAfter(node , item);
        }
    }
    //O(n)
    public boolean add(E item){
        add(size , item);
        return true;
    }
    //O(n)
    public void clear(){
        Node<E> node = head;
        for(int i = 0; i < size && node != null; i++){
            removeFirst();
            node.setNext(node.getNext());
        }
    }
    //O(1)
    public E getFirst(){
        if(head != null){
            return head.getData();
        }
        else return null;
    }
    //O(n)
    public E getLast(){
        Node<E> node = head;
        for(int i = 0; i < size && node != null; i++){
            node.setNext(node.getNext());
        }
        return node.getData();
    }


}
