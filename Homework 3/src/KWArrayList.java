/**
 * KWArrayList class taken from Book
 *
 * */

import java.util.Arrays;


public class KWArrayList<E> {

        private static final int INITIAL_CAPACITY = 10 ;
        private E[] theData;
        private int size = 0;
        private int capacity = 0;

        public KWArrayList(){
            capacity = INITIAL_CAPACITY;
            theData = (E[]) new Object[capacity];
        }

        public KWArrayList(int initialCapacity){
            if(initialCapacity < 0){
                throw new IllegalArgumentException();
            }
            capacity = initialCapacity;
            theData = (E[]) new Object[capacity];
        }

        //O(n)
        public boolean add(E anEntry){
            if(size == capacity){
                reallocate();
            }
            theData[size] = anEntry;
            size ++;
            return true;
        }
        //O(n)
        public void add(int index , E anEntry){
            if(index < 0 || index > size){
                throw new ArrayIndexOutOfBoundsException(size);
            }
            if(size == capacity){
                reallocate();
            }

            for(int i = size; i > index; i--){
                theData[i] = theData[i-1];
            }
            theData[index] = anEntry;
            size++;
        }

        private void reallocate(){
            capacity = 2 * capacity;
            theData = Arrays.copyOf(theData,capacity);
        }

        //O(1)
        public E get(int index){
            if(index < 0 || index >= size){
                throw new ArrayIndexOutOfBoundsException(index);
            }
            return theData[index];
        }
        //O(1)
        public E set(int index , E newValue){
            if(index < 0 || index >= size){
                throw new ArrayIndexOutOfBoundsException(index);
            }

            E oldValue = theData[index];
            theData[index] = newValue;
            return oldValue;
        }
        //O(n)
        public E remove(int index) throws ArrayIndexOutOfBoundsException{
            if(index < 0 || index >= size){
                throw new ArrayIndexOutOfBoundsException(index);
            }
            E returnValue = theData[index];

            for(int i=index + 1; i < size; i++){
                theData[i-1] = theData[i];
            }
            size --;
            return returnValue;

        }
        //O(n)
        public boolean remove(Object o){
            for(int i = 0; i < size; i++){
                if(theData[i].equals(o)){
                    remove(i);
                    return true;
                }
            }
            return false;
        }
        //O(n)
        public void clear(){
            for(int i = size - 1; i >= 0; i--){
                remove(i);
            }
        }
        //O(1)
        public boolean isEmpty(){
            return size == 0;
        }
        //O(1)
        public int size(){
            return size;
        }
        //O(1)
        public int capacity(){
            return capacity;
    }
        //O(n)
        public void ensureCapacity(int minCapacity) throws IllegalArgumentException{
            if(minCapacity < 0){
                throw new IllegalArgumentException();
            }
            if(minCapacity < size) return;
            capacity = minCapacity;
            theData = Arrays.copyOf(theData,capacity);
        }
        //O(n)
        public void trimToSize(){
            ensureCapacity(size);
        }





}
