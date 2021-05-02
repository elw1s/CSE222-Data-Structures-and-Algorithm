/**
 *HybridNode class is used for HybridList
 *
 * */

public class HybridNode<E> implements CONSTANTS{
    private KWArrayList<E> data;
    private HybridNode<E> next;

    public HybridNode(KWArrayList<E> newData){
        if(newData.size() < MAX_CAPACITY)
        data = newData;
        next = null;
    }

    public HybridNode(KWArrayList<E> dataitem, HybridNode<E> nodeRef) {
        data = dataitem;
        next = nodeRef;
    }

    public void setNext(HybridNode<E> next) {
        this.next = next;
    }

    public void setData(KWArrayList<E> data) {
        this.data = data;
    }

    public HybridNode<E> getNext() {
        return next;
    }

    public KWArrayList<E> getData() {
        return data;
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

}
