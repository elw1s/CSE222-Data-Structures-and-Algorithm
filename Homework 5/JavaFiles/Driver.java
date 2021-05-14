/**
 *
 * Driver Function for testing
 *
 */

import java.util.Random;

public class Driver {

    private static final int SMALL = 10;
    private static final int MEDIUM = 100;
    private static final int LARGE = 1000;

    public static void main(String[] args) {
        try {
            Random rand = new Random();

            HashMap<String, Integer> map = new HashMap<String, Integer>();
            map.put("Bir", 1);
            map.put("İki", 2);
            map.put("Üç", 3);
            map.put("Dört", 4);
            map.put("Beş", 5);
            System.out.println("Size after insertion = " + map.size());
            System.out.println("map.get('4') = " + map.get("Dört"));
            System.out.println(map.remove("Beş") + " is removed.");
            System.out.println("'Altı' is removed but it is not in the HashMap so remove funtion returned " + map.remove("Altı"));
            System.out.println("Size after remove = " + map.size());
            System.out.println("*************************ITERATOR (WITH ZERO-PARAMETER CONSTRUCTOR)**********************");
            HashMap.MapIterator iter = map.iterator();
            System.out.print("Values in HashMap started from first: ");
            while (iter.hasNext()) {
                System.out.print(iter.next() + " ");
            }
            System.out.println("\nPrevious item is " + iter.prev());

            System.out.println("*************************ITERATOR (WITH PARAMETER CONSTRUCTOR)**********************");
            HashMap.MapIterator iter2 = map.iterator("Üç");
            System.out.print("Values in HashMap started from the given key: ");
            while (iter2.hasNext()) {
                System.out.print(iter2.next() + " ");
            }
            System.out.println("\nPrevious item is " + iter2.prev());

            //HASH MAP CHAIN (LINKED LIST)
            System.out.println("*************************HASH MAP CHAIN (LINKED LIST)**********************");
            HashMapChain<Integer, String> chainMapLinked = new HashMapChain<Integer, String>();
            System.out.println("START~ HashMap is empty: " + chainMapLinked.isEmpty());
            long start = System.nanoTime();
            int removeKey = 0;
            for (int i = 0; i < SMALL; i++) {
                int random = rand.nextInt() % SMALL;
                chainMapLinked.put(random, "A" + (i % 25));
                removeKey = random;
            }
            long stop1 = System.nanoTime();
            System.out.println("SMALL~ Remove(" + removeKey + ") : " + chainMapLinked.remove(removeKey));
            long stop2 = System.nanoTime();
            System.out.println("SMALL~ Removing an element which is not in HashMap : " + chainMapLinked.remove(SMALL * 2));
            System.out.println("SMALL~ Size is " + chainMapLinked.size());
            System.out.println("SMALL~ HashMap is empty: " + chainMapLinked.isEmpty());
            System.out.println("SMALL~ Get(0) = " + chainMapLinked.get(0));
            System.out.println("SMALL~ Get an element which is not in the HashMap = " + chainMapLinked.get(SMALL * 2));
            System.out.println("Time taken for adding " + SMALL + " number of elements = " + (double)((stop1-start) / Math.pow(10,6)) + " ms");
            System.out.println("Time taken for removing an element = " + (double)((stop2-stop1)/ Math.pow(10,6)) + " ms");
            start = System.nanoTime();
            for (int i = 0; i < MEDIUM; i++) {
                int random = rand.nextInt() % MEDIUM;
                chainMapLinked.put(random, "A" + (i % 25));
                removeKey = random;
            }
            stop1 = System.nanoTime();
            System.out.println("MEDIUM~ Remove(" + removeKey + ") : " + chainMapLinked.remove(removeKey));
            stop2 = System.nanoTime();
            System.out.println("MEDIUM~ Removing an element which is not in HashMap : " + chainMapLinked.remove(MEDIUM * 2));
            System.out.println("MEDIUM~ Size is " + chainMapLinked.size());
            System.out.println("MEDIUM~ HashMap is empty: " + chainMapLinked.isEmpty());
            System.out.println("MEDIUM~ Get(0) = " + chainMapLinked.get(0));
            System.out.println("MEDIUM~ Get an element which is not in the HashMap = " + chainMapLinked.get(MEDIUM * 2));
            System.out.println("Time taken for adding " + MEDIUM + " number of elements = " + (double)((stop1-start) / Math.pow(10,6)) + " ms");
            System.out.println("Time taken for removing an element = " + (double)((stop2-stop1)/ Math.pow(10,6)) + " ms");
            start = System.nanoTime();
            for (int i = 0; i < LARGE; i++) {
                int random = rand.nextInt() % LARGE;
                chainMapLinked.put(random, "A" + (i % 25));
                removeKey = random;
            }
            stop1 = System.nanoTime();
            System.out.println("LARGE~ Remove(" + removeKey + ") : " + chainMapLinked.remove(removeKey));
            stop2 = System.nanoTime();
            System.out.println("LARGE~ Removing an element which is not in HashMap : " + chainMapLinked.remove(LARGE * 2));
            System.out.println("LARGE~ Size is " + chainMapLinked.size());
            System.out.println("LARGE~ HashMap is empty: " + chainMapLinked.isEmpty());
            System.out.println("LARGE~ Get(0) = " + chainMapLinked.get(0));
            System.out.println("LARGE~ Get an element which is not in the HashMap = " + chainMapLinked.get(LARGE * 2));
            System.out.println("Time taken for adding " + LARGE + " number of elements = " + (double)((stop1-start) / Math.pow(10,6)) + " ms");
            System.out.println("Time taken for removing an element = " + (double)((stop2-stop1)/ Math.pow(10,6)) + " ms");
            //HASH MAP CHAIN (TREE SET)
            System.out.println("*************************HASH MAP CHAIN (TREE SET)**********************");
            HashMapChainTree<Integer, Integer> chainMapTree = new HashMapChainTree<Integer, Integer>();
            System.out.println("START~ HashMap is empty: " + chainMapTree.isEmpty());
            start = System.nanoTime();
            for (int i = 0; i < SMALL; i++) {
                int random = rand.nextInt() % SMALL;
                chainMapTree.put(random, i);
                removeKey = random;
            }
            stop1 = System.nanoTime();
            System.out.println("SMALL~ Remove(" + removeKey + ") : " + chainMapTree.remove(removeKey));
            stop2 = System.nanoTime();
            System.out.println("SMALL~ Removing an element which is not in HashMap : " + chainMapTree.remove(SMALL *2 ));
            System.out.println("SMALL~ Size is " + chainMapTree.size());
            System.out.println("SMALL~ HashMap is empty: " + chainMapTree.isEmpty());
            System.out.println("SMALL~ Get(0) = " + chainMapTree.get(0));
            System.out.println("SMALL~ Get an element which is not in the HashMap = " + chainMapTree.get(SMALL *2));
            System.out.println("Time taken for adding " + SMALL + " number of elements = " + (double)((stop1-start) / Math.pow(10,6)) + " ms");
            System.out.println("Time taken for removing an element = " + (double)((stop2-stop1)/ Math.pow(10,6)) + " ms");
            start = System.nanoTime();
            for (int i = 0; i < MEDIUM; i++) {
                int random = rand.nextInt() % MEDIUM;
                chainMapTree.put(random, i);
                removeKey = random;
            }
            stop1 = System.nanoTime();
            System.out.println("MEDIUM~ Remove(" + removeKey + ") : " + chainMapTree.remove(removeKey) );
            stop2 = System.nanoTime();
            System.out.println("MEDIUM~ Removing an element which is not in HashMap : " + chainMapTree.remove(MEDIUM * 2));
            System.out.println("MEDIUM~ Size is " + chainMapTree.size());
            System.out.println("MEDIUM~ HashMap is empty: " + chainMapTree.isEmpty());
            System.out.println("MEDIUM~ Get(0) = " + chainMapTree.get(0));
            System.out.println("MEDIUM~ Get an element which is not in the HashMap = " + chainMapTree.get(MEDIUM * 2));
            System.out.println("Time taken for adding " + MEDIUM + " number of elements = " + (double)((stop1-start) / Math.pow(10,6)) + " ms");
            System.out.println("Time taken for removing an element = " + (double)((stop2-stop1)/ Math.pow(10,6)) + " ms");
            start = System.nanoTime();
            for (int i = 0; i < LARGE; i++) {
                int random = rand.nextInt() % LARGE;
                chainMapTree.put(random, i);
                removeKey = random;
            }
            stop1 = System.nanoTime();
            System.out.println("LARGE~ Remove(" + removeKey + ") : " + chainMapTree.remove(removeKey));
            stop2 = System.nanoTime();
            System.out.println("LARGE~ Removing an element which is not in HashMap : " + chainMapTree.remove(LARGE * 2));
            System.out.println("LARGE~ Size is " + chainMapTree.size());
            System.out.println("LARGE~ HashMap is empty: " + chainMapTree.isEmpty());
            System.out.println("LARGE~ Get(0) = " + chainMapTree.get(0));
            System.out.println("LARGE~ Get an element which is not in the HashMap = " + chainMapTree.get(LARGE * 2));
            System.out.println("Time taken for adding " + LARGE + " number of elements = " + (double)((stop1-start) / Math.pow(10,6)) + " ms");
            System.out.println("Time taken for removing an element = " + (double)((stop2-stop1)/ Math.pow(10,6)) + " ms");
            //HASH MAP COALESCED HASHING
            System.out.println("*************************HASH MAP COALESCED HASHING**********************");
            CoalescedHashingMap<Integer, Integer> chainCoalesced = new CoalescedHashingMap<>(1200);
            System.out.println("START~ HashMap is empty: " + chainMapTree.isEmpty());
            start = System.nanoTime();
            for (int i = 0; i < SMALL; i++) {
                int random = rand.nextInt() % SMALL;
                chainCoalesced.put(random, i);
                removeKey = random;
            }
            stop1 = System.nanoTime();
            System.out.println("SMALL~ Remove(" + removeKey + ") : " + chainCoalesced.remove(removeKey));
            stop2 = System.nanoTime();
            System.out.println("SMALL~ Removing an element which is not in HashMap : " + chainCoalesced.remove(SMALL * 2));
            System.out.println("SMALL~ Size is " + chainCoalesced.size());
            System.out.println("SMALL~ HashMap is empty: " + chainCoalesced.isEmpty());
            System.out.println("SMALL~ Get(0) = " + chainCoalesced.get(0));
            System.out.println("SMALL~ Get an element which is not in the HashMap = " + chainCoalesced.get(SMALL * 2));
            System.out.println("Time taken for adding " + SMALL + " number of elements = " + (double)((stop1-start) / Math.pow(10,6)) + " ms");
            System.out.println("Time taken for removing an element = " + (double)((stop2-stop1)/ Math.pow(10,6)) + " ms");
            start = System.nanoTime();
            for (int i = 0; i < MEDIUM; i++) {
                int random = rand.nextInt() % MEDIUM;
                chainCoalesced.put(random, i);
                removeKey = random;
            }
            stop1 = System.nanoTime();
            System.out.println("MEDIUM~ Remove(" + removeKey + ") : " + chainCoalesced.remove(removeKey));
            stop2 = System.nanoTime();
            System.out.println("MEDIUM~ Removing an element which is not in HashMap : " + chainCoalesced.remove(MEDIUM * 2));
            System.out.println("MEDIUM~ Size is " + chainCoalesced.size());
            System.out.println("MEDIUM~ HashMap is empty: " + chainCoalesced.isEmpty());
            System.out.println("MEDIUM~ Get(0) = " + chainCoalesced.get(0));
            System.out.println("MEDIUM~ Get an element which is not in the HashMap = " + chainCoalesced.get(MEDIUM * 2));
            System.out.println("Time taken for adding " + MEDIUM + " number of elements = " + (double)((stop1-start) / Math.pow(10,6)) + " ms");
            System.out.println("Time taken for removing an element = " + (double)((stop2-stop1)/ Math.pow(10,6)) + " ms");
            start = System.nanoTime();
            for (int i = 0; i < LARGE; i++) {
                int random = rand.nextInt() % LARGE;
                chainCoalesced.put(random, i);
                removeKey = random;
            }
            stop1 = System.nanoTime();
            System.out.println("LARGE~ Remove(" + removeKey + ") : " + chainCoalesced.remove(removeKey));
            stop2 = System.nanoTime();
            System.out.println("LARGE~ Removing an element which is not in HashMap : " + chainCoalesced.remove(LARGE * 2));
            System.out.println("LARGE~ Size is " + chainCoalesced.size());
            System.out.println("LARGE~ HashMap is empty: " + chainCoalesced.isEmpty());
            System.out.println("LARGE~ Get(0) = " + chainCoalesced.get(0));
            System.out.println("LARGE~ Get an element which is not in the HashMap = " + chainCoalesced.get(LARGE * 2));
            System.out.println("Time taken for adding " + LARGE + " number of elements = " + (double)((stop1-start) / Math.pow(10,6)) + " ms");
            System.out.println("Time taken for removing an element = " + (double)((stop2-stop1)/ Math.pow(10,6)) + " ms");
        }
        catch (Exception exc){
            System.err.println(exc.getMessage());
        }
    }
}
