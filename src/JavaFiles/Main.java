import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Heap<Integer> heap = new Heap<Integer>(5);
        heap.insert(5);
        heap.insert(10);
        heap.insert(6);
        heap.insert(3);
        heap.insert(19);
        heap.insert(1);
        heap.insert(17);
        heap.insert(23);
        System.out.println("HEAP 1");
        heap.print();
        System.out.println(heap.peek());
        System.out.println(heap.contains(1));
        System.out.println(heap.contains(24));
        Heap<Integer> heap2 = new Heap<Integer>();
        heap2.insert(100);
        heap2.insert(120);
        heap2.insert(130);
        heap2.insert(80);
        heap2.insert(70);
        System.out.println("HEAP 2");
        heap2.print();
        Heap<Integer> mergedHeap = heap.merge(heap2);
        System.out.println("Merged Heap");
        mergedHeap.print();
        System.out.println("Sorted Merged Heap");
        mergedHeap.sort(mergedHeap).print();
        System.out.println("Merged Heap");
        mergedHeap.print();
        System.out.println("Heap Removed element: "+heap.removeLargest(2));
        heap.print();
        System.out.println("Merged Heap Removed element: "+mergedHeap.removeLargest(2));
        mergedHeap.print();

        /* ITERATOR OPERATIONS */

        Heap.Iterator iter = heap.iterator();
        iter.next();
        System.out.println("PRINTING HEAP USING ITERATOR");
        heap.print();
        iter.set(28);
        System.out.println("SET THE LAST RETURNED ITEM OF HEAP 28");
        heap.print();

        // BINARY SEARCH TREE

        Random rand = new Random();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        BSTHeapTree<Integer> bstheap = new BSTHeapTree<Integer>();


        for(int i = 0; i < 3000; i++){
            int num = rand.nextInt(5000);
            bstheap.insert(num);
            arr.add(num);
        }

        System.out.println("\n\n\nFIRST VERSION OF TREE (PREORDER) \n\n");
        bstheap.preOrder();

        System.out.println("\n---------------------------------\n\n");

        for(int i = 0; i < 100; i++){
            System.out.println("Occurence of " + arr.get(i) + " is " + bstheap.find(arr.get(i)));
        }

        for(int i = 0; i < 10; i++){
            int num = rand.nextInt(5000);
            if(arr.contains(num)) i--;
            else System.out.println("Occurence of element that is not in the BSTHeapTree is " + bstheap.find(num));
        }

        System.out.println("Mode of BSTHeapTree is " + bstheap.find_mode());

        for(int i = 0; i < 100; i++){
            System.out.println(arr.get(i)+" which has "+ bstheap.find(arr.get(i)) +" occurence is removed from BSTHeapTree");
            bstheap.remove(arr.get(i));
            arr.remove(i);
        }

        for(int i = 0; i < 10; i++){
            int num = rand.nextInt(5000);
            if(arr.contains(num)) i--;
            else System.out.println(num + " is not in the BSTHeapTree and tried to remove it. Returned find value of "+ num + " is " + bstheap.find(num));
        }

        System.out.println("\n\n\nLAST VERSION OF TREE (PREORDER)\n\n");

        bstheap.preOrder();



    }
}
