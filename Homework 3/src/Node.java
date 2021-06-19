/**
 * Node class for KWLinkedList
 *
 * */

public class Node<E> {

    private E data;
    private Node<E> next;

    public Node(E newData){
        data = newData;
        next = null;
    }

    public Node(E dataitem, Node<E> nodeRef) {
        data = dataitem;
        next = nodeRef;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }
}
