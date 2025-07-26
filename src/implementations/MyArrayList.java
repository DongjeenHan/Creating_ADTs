package implementations;

import utilities.Iterator;
import utilities.ListADT;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A simplified custom ArrayList implementation.
 * @param <E> the type of elements in this list
 */
public class MyArrayList<E> implements ListADT<E> {

    private static final int MIN_CAPACITY = 10;
    private static final double RESIZE_SCALING = 1.5;
    private static final double MIN_FILL_PERCENTAGE = 0.5/Math.pow(RESIZE_SCALING, 2);

    private E[] list;
    private int size;

    /** Constructs an empty list. */
    public MyArrayList(){
        list = getNewArray(MIN_CAPACITY);
        size = 0;
    }

    /** @return the number of elements in the list */
    @Override
    public int size() {
        return size;
    }

    /** Clears the list. */
    @Override
    public void clear() {
        list = getNewArray(MIN_CAPACITY);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private E[] getNewArray(int size){
        return (E[]) (new Object[size]);
    }

    private E[] copy(int copyCapacity){
        E[] copy = getNewArray(copyCapacity);
        System.arraycopy(list, 0, copy, 0, size);
        return copy;
    }

    private E[] copy(int copyCapacity, int removeIndex){
        E[] newList = getNewArray(copyCapacity);
        System.arraycopy(list, 0, newList, 0, removeIndex);
        System.arraycopy(list, removeIndex+1, newList, removeIndex, size-removeIndex-1);
        return newList;
    }

    private void checkCapacity(){
        if(size == list.length)
            list = copy((int) (list.length * RESIZE_SCALING));
    }

    private void checkCapacity(int sizeIncrease){
        int newSize = size + sizeIncrease;
        if (newSize > list.length){
            double newCapacity = list.length;
            do newCapacity = Math.floor(newCapacity * RESIZE_SCALING);
            while(newCapacity < newSize);
            list = copy((int) newCapacity);
        }
    }

    private void checkIndexInRange(int index) throws IndexOutOfBoundsException{
        if(index >= size || index < 0)
            throw new IndexOutOfBoundsException("index of " + index + "is out of range for list of size " + size);
    }

    /** Adds an element at a specific index. */
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (index == size) return add(toAdd);
        checkIndexInRange(index);
        if(toAdd == null) throw new NullPointerException("cannot add a null element to list");
        checkCapacity();

        int i = size;
        while(i>index){
            list[i] = list[--i];
        }
        list[index] = toAdd;
        size++;
        return true;
    }

    /** Adds an element to the end of the list. */
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if(toAdd == null) throw new NullPointerException("cannot add a null element to list");
        checkCapacity();
        list[size++] = toAdd;
        return true;
    }

    /** Adds all elements from another list. */
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("cannot append null list");
        checkCapacity(toAdd.size());
        for (int i = 0; i < toAdd.size(); i++) {
            E item = toAdd.get(i);
            if(item != null){
                list[size++] = item;
            }
        }
        return true;
    }

    /** Gets an element at a given index. */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        checkIndexInRange(index);
        return list[index];
    }

    private boolean isArrayOversized(){
        return (size-1) < MIN_FILL_PERCENTAGE * list.length && list.length > MIN_CAPACITY;
    }

    private int reducedCapacity(){
        double firstScale = Math.ceil(list.length/RESIZE_SCALING);
        return Math.max(MIN_CAPACITY, (int) Math.ceil(firstScale/RESIZE_SCALING));
    }

    /** Removes an element by index. */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        checkIndexInRange(index);
        E removed = list[index];
        if (isArrayOversized()) {
            list = copy(reducedCapacity(), index);
        } else {
            while (index < size-1){
                list[index] = list[++index];
            }
            list[index] = null;
        }
        size--;
        return removed;
    }

    /** Removes the first occurrence of an element. */
    @Override
    public E remove(E toRemove) throws NullPointerException {
        int index = indexOf(toRemove);
        if (index == -1) return null;
        return remove(index);
    }

    public int indexOf(E toFind) throws NullPointerException{
        if (toFind == null) throw new NullPointerException("Cannot search for null value");
        for (int i = 0; i<size ; i++){
            if(toFind.equals(list[i]))
                return i;
        }
        return -1;
    }

    /** Replaces an element at a specific index. */
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        checkIndexInRange(index);
        if(toChange == null) throw new NullPointerException("cannot set element in list to null");
        E replaced = list[index];
        list[index] = toChange;
        return replaced;
    }

    /** @return true if list is empty */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks if list contains an element. */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return indexOf(toFind) != -1;
    }

    /** Converts list to array of type E. */
    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("toHold cannot be null");
        if (toHold.length < size) toHold = Arrays.copyOf(toHold, size);
        System.arraycopy(list, 0, toHold, 0, size);
        return toHold;
    }

    /** Converts list to Object array. */
    @Override
    public Object[] toArray() {
        return copy(size);
    }

    /** @return an iterator over the list */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /** Internal iterator class. */
    private class Itr implements Iterator<E> {
        private int index;

        private Itr(){
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index<size;
        }

        @Override
        public E next()  throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException();
            return get(index++);
        }
    }
}
