public class BSTHeapTree<E extends Comparable<E>> {
    private BSTNode<E> root;
    /**
     * Find the occurence of the given item
     *  */        
    public int find(E item){
        if(root == null) return -1;

        return root.getOccurence(item);
    }
    /**
     * Inserts the given value to the BSTHeapTree
     * @param value
     */
    public void insert(E value){
        if(root == null) root = new BSTNode<E>(value);
        else root.insert(value);
    }
    /**
     * Returns the item which has highest occurence
     * @return
     */
    public E find_mode(){
        if(root == null) return null;

        return root.findMode(new HeapNode<E>(null, 0)).getData();
    }
    /**
     * Removes item from the BSTHeapTree
     * @param item
     * @return
     */
    public E remove(E item){
        if(root == null) return null;
        root = remove(root, item);
        return item;
    }
    /**
     * Swaps the given nodes and removes the second parameter
     * @param subTree
     * @param removedTree
     */
    private void swap(BSTNode<E> subTree , BSTNode<E> removedTree){
        subTree.getData().swap(removedTree.getData().getNode(0));

        int occurence = removedTree.getData().getOccurence(0);
        for(int i = 0; i < occurence; i++){
            removedTree.getData().remove(0);
        }
    }
    /**
     * Removes node
     * @param subTree
     * @param item
     * @return
     */
    private BSTNode<E> remove(BSTNode subTree , E item){
        if(subTree == null) return null;

        if(subTree.getData().peek().compareTo(item) < 0 && !subTree.getData().contains(item)){
            subTree.setRightChild(remove(subTree.getRightChild(),item));
        }
        else if(subTree.getData().peek().compareTo(item) > 0 &&  !subTree.getData().contains(item)){
            subTree.setLeftChild(remove(subTree.getLeftChild(),item));
        }
        else if(subTree.getData().contains(item)){
            subTree.getData().remove(subTree.getData().getIndex(item));
            if(subTree.getData().size() == 0){
                if(subTree.getLeftChild() == null){
                    return subTree.getRightChild();
                }
                else if(subTree.getRightChild() == null){
                    return subTree.getLeftChild();
                }
                else if(subTree.getData().peek() == null){
                    subTree.setData(subTree.getLeftChild().findMax());

                    subTree.setLeftChild(remove(subTree.getLeftChild(), (E) subTree.getData().peek()));
                }
            }
            else if(subTree.getData().size() < 7){
                if(subTree.getLeftChild() != null){
                    if(subTree.getLeftChild().max().getData().size() > 1)
                        swap(subTree, subTree.getLeftChild().max());
                }
                else if(subTree.getRightChild() != null){
                    if(subTree.getRightChild().min().getData().size() > 1)
                        swap(subTree, subTree.getRightChild().min());
                }
            }
        }
        return subTree;
    }
    /**
     * Prints pre-order
     */
    public void preOrder(){
        if(root != null) preOrder(root);
    }
    /**
     * Prints pre-order
     * @param subTree
     */
    private void preOrder(BSTNode subTree){
        if(subTree == null) return;

        for(int i = 0; i < subTree.getData().size(); i++){
            System.out.print(subTree.getData().getHeapData(i) + "(" + subTree.getData().getOccurence(i) + ") ");
        }
        System.out.println();

        if(subTree.getLeftChild() != null) preOrder(subTree.getLeftChild());
        if(subTree.getRightChild() != null) preOrder(subTree.getRightChild());

    }



}
