/**
 *HybridList class is a Linked List but its nodes are holding data with Array List
 *
 * */


import java.util.NoSuchElementException;

public class HybridList<E> implements CONSTANTS {
    private int node_size;
    private int item_size;
    private HybridNode<E> head;

    public HybridList() {
        head = null;
        node_size = 0;
        item_size = 0;
    }

    public int size() {
        return item_size;
    }

    public int nodeSize() {
        return node_size;
    }

    //O(n)
    public boolean add(E item) {
        if (head == null) {
            head = new HybridNode<E>(new KWArrayList<>(MAX_CAPACITY), null);
            node_size++;
        } else if (MAX_CAPACITY == getNode(node_size - 1).getData().size()) {
            HybridNode<E> node = new HybridNode<E>(new KWArrayList<E>(), null);
            getNode(node_size - 1).setNext(node);
            node_size++;
        }
        getNode(node_size - 1).getData().add(item);
        item_size++;
        return true;
    }
    //O(n)
    private HybridNode<E> getNode(int index) {
        HybridNode<E> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.getNext();
        }
        return node;
    }
    //O(1)
    private int getNodeIndex(int index) {
        return ((index * MAX_CAPACITY) % MAX_CAPACITY) + (index / MAX_CAPACITY);
    }
    //O(n)
    public boolean contains(Object o) {
        for (int i = 0; i < node_size; i++) {
            if (get(i).equals(o)) return true;
        }
        return false;
    }
    //O(n)
    public E get(int index) {
        if (index < 0 || index >= node_size * MAX_CAPACITY) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        int nodeIndex = getNodeIndex(index);
        KWArrayList<E> result = getNode(nodeIndex).getData();
        return result.get(index % MAX_CAPACITY);
    }
    //O(n)
    public E set(int index, E newValue) {
        if (index < 0 || index >= node_size * MAX_CAPACITY) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        int nodeIndex = getNodeIndex(index);
        KWArrayList<E> result = getNode(nodeIndex).getData();
        E previousValue = result.get(index % MAX_CAPACITY);
        result.set(index % MAX_CAPACITY, newValue);
        getNode(nodeIndex).setData(result);
        return previousValue;
    }

    //O(n)
    public E remove(int index) throws NoSuchElementException{
        if (index < 0 || index >= node_size * MAX_CAPACITY) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        if (node_size == 0) throw new NoSuchElementException();

        E removedItem = getNode(getNodeIndex(index)).getData().get(index % MAX_CAPACITY);
        getNode(getNodeIndex(index)).getData().set(index % MAX_CAPACITY, null);
        cleanNode();
        item_size--;
        return removedItem;
    }
    //O(n^3)
    public boolean remove(Object o) throws NoSuchElementException{
        if (node_size == 0) throw new NoSuchElementException();
        for (int i = 0; i < node_size; i++) {
            for (int k = 0; k < getNode(i).getData().size(); k++) {
                if (getNode(i).getData().get(k).equals(o)) {
                    remove(i);
                    item_size--;
                    cleanNode();
                    return true;
                }
            }
        }
        return false;
    }
    //O(n)
    private void cleanNode() {
        boolean flag = false;
        HybridNode<E> curr = head;
        while (curr != null) {
            if (curr.getNext().getData().size() == 0) {
                curr.setNext(curr.getNext().getNext());
                node_size--;
            }
        }
        curr = curr.getNext();
    }

}