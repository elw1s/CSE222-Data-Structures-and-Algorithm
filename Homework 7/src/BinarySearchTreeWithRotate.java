/**
 * BinarySearchTreeWithRotate Class
 *
 * @author: Elliot B. Koffman
 * */

public class BinarySearchTreeWithRotate<E
        extends Comparable<E>>
        extends BinarySearchTree<E> {
    protected Node<E> rotateRight(Node<E> root) {
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }

    protected Node<E> rotateLeft(Node<E> localRoot) {
        Node<E> temp = localRoot.right;
        localRoot.right = temp.left;
        temp.left = localRoot;
        return temp;
    }
}
