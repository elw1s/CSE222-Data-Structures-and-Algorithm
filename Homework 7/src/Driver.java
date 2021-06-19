import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;
/**
* Driver Class
* */

public class Driver {

    public static void main(String[] args) {
        try {
            /* PART1 */
            Random rand = new Random();
            NavigableSetAVL<Integer> navigableSetAVL = new NavigableSetAVL<Integer>();
            NavigableSetSkipList<Integer> navigableSetSkipList = new NavigableSetSkipList<Integer>();
            System.out.println(" ****************************************PART 1****************************************");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            int removeItem = 0;
            for (int i = 0; i < 10; i++) {
                removeItem = rand.nextInt(100); //This number will be deleted in the code.
                System.out.println("Navigable Set AVL insert: " + navigableSetAVL.insert(rand.nextInt(100)));
                System.out.println("Navigable Set Skip List insert: " + navigableSetSkipList.insert(removeItem));
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            NavigableSetAVL.Iterator iterAvl = navigableSetAVL.iterator();
            NavigableSetSkipList.Iterator iterSkip = navigableSetSkipList.descendingIterator();

            System.out.print("Elements in Navigable Set AVL: ");
            while (iterAvl.hasNext()) {
                System.out.print(iterAvl.next() + " ");
            }
            System.out.println();

            System.out.print("Elements in Navigable Set Skip List (Descending Iterator): ");
            while (iterSkip.hasNext()) {
                System.out.print(iterSkip.next() + " ");
            }
            System.out.println();

            TreeSet<Integer> set1 = (TreeSet<Integer>) navigableSetAVL.headSet(50);
            TreeSet<Integer> set2 = (TreeSet<Integer>) navigableSetAVL.tailSet(50);

            System.out.print("navigableSetAVL.headSet(50): ");
            System.out.print(set1.toString() + "\n");
            System.out.print("navigableSetAVL.tailSet(50): ");
            System.out.print(set2.toString() + "\n");

            System.out.println("Delete " + navigableSetSkipList.delete(removeItem) + " from Navigable Set Skip List");

            System.out.print("Elements in Navigable Set Skip List after removing an element (Descending Iterator): ");
            NavigableSetSkipList.Iterator iterSkip2 = navigableSetSkipList.descendingIterator();
            while (iterSkip2.hasNext()) {
                System.out.print(iterSkip2.next() + " ");
            }
            System.out.println();

            /* PART2 */
            System.out.println(" ****************************************PART 2****************************************");
            BinarySearchTree<Integer> avlBST = new BinarySearchTree<Integer>();
            BinarySearchTree<Integer> redblackBST = new BinarySearchTree<Integer>();
            for (int i = 0; i < 6; i++) {
                avlBST.add(rand.nextInt());
                redblackBST.add(rand.nextInt());
            }

            avlBST.checkTree(avlBST);
            redblackBST.checkTree(redblackBST);

            /* PART3 */
            System.out.println(" ****************************************PART 3****************************************");
            final int INSTANCE = 40;
            final int SIZE1 = 10000;
            final int SIZE2 = 20000;
            final int SIZE3 = 40000;
            final int SIZE4 = 80000;
            final int TEST_SIZE = 100;

            ArrayList<BinarySearchTree<Integer>> binarySearchTree = new ArrayList<BinarySearchTree<Integer>>(INSTANCE);
            ArrayList<RedBlackTree<Integer>> redBlackTree = new ArrayList<RedBlackTree<Integer>>(INSTANCE);
            ArrayList<BTree<Integer>> bTree = new ArrayList<BTree<Integer>>(INSTANCE);
            ArrayList<SkipList<Integer>> skipList = new ArrayList<SkipList<Integer>>(INSTANCE);
            ArrayList<TwoThreeTree<Integer>> twoThreeTree = new ArrayList<TwoThreeTree<Integer>>(INSTANCE);

            for (int i = 0; i < INSTANCE; i++) {
                binarySearchTree.add(new BinarySearchTree<Integer>());
                redBlackTree.add(new RedBlackTree<Integer>());
                bTree.add(new BTree<Integer>(5));
                skipList.add(new SkipList<Integer>());
                twoThreeTree.add(new TwoThreeTree<Integer>());
                if (i < 10) {
                    for (int k = 0; k < SIZE1; k++) {
                        int randomNumber = rand.nextInt(SIZE1);
                        binarySearchTree.get(i).add(randomNumber);
                        redBlackTree.get(i).add(randomNumber);
                        bTree.get(i).insert(randomNumber);
                        skipList.get(i).add(randomNumber);
                        twoThreeTree.get(i).insert(randomNumber);
                    }
                } else if (i >= 10 && i < 20) {
                    for (int k = 0; k < SIZE2; k++) {
                        int randomNumber = rand.nextInt(SIZE2);
                        binarySearchTree.get(i).add(randomNumber);
                        redBlackTree.get(i).add(randomNumber);
                        bTree.get(i).insert(randomNumber);
                        skipList.get(i).add(randomNumber);
                        twoThreeTree.get(i).insert(randomNumber);
                    }
                } else if (i >= 20 && i < 30) {
                    for (int k = 0; k < SIZE3; k++) {
                        int randomNumber = rand.nextInt(SIZE3);
                        binarySearchTree.get(i).add(randomNumber);
                        redBlackTree.get(i).add(randomNumber);
                        bTree.get(i).insert(randomNumber);
                        skipList.get(i).add(randomNumber);
                        twoThreeTree.get(i).insert(randomNumber);
                    }
                } else if (i >= 30 && i < 40) {
                    for (int k = 0; k < SIZE4; k++) {
                        int randomNumber = rand.nextInt(SIZE4);
                        binarySearchTree.get(i).add(randomNumber);
                        redBlackTree.get(i).add(randomNumber);
                        bTree.get(i).insert(randomNumber);
                        skipList.get(i).add(randomNumber);
                        twoThreeTree.get(i).insert(randomNumber);
                    }
                }
            }

            double size1Time = 0, size2Time = 0, size3Time = 0, size4Time = 0;
            double start, stop;
            /* BINARY SEARCH TREE */
            for (int i = 0; i < INSTANCE; i++) {
                if (i < 10) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        binarySearchTree.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size1Time += (stop - start);
                } else if (i >= 10 && i < 20) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        binarySearchTree.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size2Time += (stop - start);
                } else if (i >= 20 && i < 30) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        binarySearchTree.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size3Time += (stop - start);
                } else if (i >= 30 && i < 40) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        binarySearchTree.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size4Time += (stop - start);
                }
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.println("Average time for Binary Search Tree while inserting 100 elements over 10K elements: " + ((double) size1Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for Binary Search Tree while inserting 100 elements over 20K elements: " + ((double) size2Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for Binary Search Tree while inserting 100 elements over 40K elements: " + ((double) size3Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for Binary Search Tree while inserting 100 elements over 80K elements: " + ((double) size4Time / Math.pow(10, 6)) + " ms");
            size1Time = 0;
            size2Time = 0;
            size3Time = 0;
            size4Time = 0;

            /*Red-Black Tree */
            for (int i = 0; i < INSTANCE; i++) {
                if (i < 10) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        redBlackTree.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size1Time += (stop - start);
                } else if (i >= 10 && i < 20) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        redBlackTree.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size2Time += (stop - start);
                } else if (i >= 20 && i < 30) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        redBlackTree.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size3Time += (stop - start);
                } else if (i >= 30 && i < 40) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        redBlackTree.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size4Time += (stop - start);
                }
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.println("Average time for Red-Black Tree while inserting 100 elements over 10K elements: " + ((double) size1Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for Red-Black Tree while inserting 100 elements over 20K elements: " + ((double) size2Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for Red-Black Tree while inserting 100 elements over 40K elements: " + ((double) size3Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for Red-Black Tree while inserting 100 elements over 80K elements: " + ((double) size4Time / Math.pow(10, 6)) + " ms");
            size1Time = 0;
            size2Time = 0;
            size3Time = 0;
            size4Time = 0;

            /*2-3 TREE */
            for (int i = 0; i < INSTANCE; i++) {
                if (i < 10) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        twoThreeTree.get(i).insert(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size1Time += (stop - start);
                } else if (i >= 10 && i < 20) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        twoThreeTree.get(i).insert(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size2Time += (stop - start);
                } else if (i >= 20 && i < 30) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        twoThreeTree.get(i).insert(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size3Time += (stop - start);
                } else if (i >= 30 && i < 40) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        twoThreeTree.get(i).insert(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size4Time += (stop - start);
                }
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.println("Average time for 2-3 Tree while inserting 100 elements over 10K elements: " + ((double) size1Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for 2-3 Tree while inserting 100 elements over 20K elements: " + ((double) size2Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for 2-3 Tree while inserting 100 elements over 40K elements: " + ((double) size3Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for 2-3 Tree while inserting 100 elements over 80K elements: " + ((double) size4Time / Math.pow(10, 6)) + " ms");
            size1Time = 0;
            size2Time = 0;
            size3Time = 0;
            size4Time = 0;
            /*B TREE*/
            for (int i = 0; i < INSTANCE; i++) {
                if (i < 10) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        bTree.get(i).insert(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size1Time += (stop - start);
                } else if (i >= 10 && i < 20) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        bTree.get(i).insert(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size2Time += (stop - start);
                } else if (i >= 20 && i < 30) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        bTree.get(i).insert(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size3Time += (stop - start);
                } else if (i >= 30 && i < 40) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        bTree.get(i).insert(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size4Time += (stop - start);
                }
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.println("Average time for B Tree while inserting 100 elements over 10K elements: " + ((double) size1Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for B Tree while inserting 100 elements over 20K elements: " + ((double) size2Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for B Tree while inserting 100 elements over 40K elements: " + ((double) size3Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for B Tree while inserting 100 elements over 80K elements: " + ((double) size4Time / Math.pow(10, 6)) + " ms");
            size1Time = 0;
            size2Time = 0;
            size3Time = 0;
            size4Time = 0;
            /*SKIP LIST */
            for (int i = 0; i < INSTANCE; i++) {
                if (i < 10) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        skipList.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size1Time += (stop - start);
                } else if (i >= 10 && i < 20) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        skipList.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size2Time += (stop - start);
                } else if (i >= 20 && i < 30) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        skipList.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size3Time += (stop - start);
                } else if (i >= 30 && i < 40) {
                    start = System.nanoTime();
                    for (int k = 0; k < TEST_SIZE; k++) {
                        skipList.get(i).add(rand.nextInt(SIZE4));
                    }
                    stop = System.nanoTime();
                    size4Time += (stop - start);
                }
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.println("Average time for Skip List while inserting 100 elements over 10K elements: " + ((double) size1Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for Skip List while inserting 100 elements over 20K elements: " + ((double) size2Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for Skip List while inserting 100 elements over 40K elements: " + ((double) size3Time / Math.pow(10, 6)) + " ms");
            System.out.println("Average time for Skip List while inserting 100 elements over 80K elements: " + ((double) size4Time / Math.pow(10, 6)) + " ms");
            System.out.println("-----------------------------------------------------------------------------------------------");
        }
        catch (Exception exc){
            System.err.println(exc.getMessage());
        }

    }
}
