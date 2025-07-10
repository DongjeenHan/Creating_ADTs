package implementations;

import utilities.Iterator;
import utilities.ListADT;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements ListADT<E> {

    private static final int MIN_CAPACITY = 10;  // minimum length of the array
    private static final double RESIZE_SCALING = 1.5;  // multiplier applied to length when resizing array
    private static final double MIN_FILL_PERCENTAGE = 0.5/Math.pow(RESIZE_SCALING, 2);  // the minimum percentage of length used before array length is reduced

    private E[] list;
    private int size; // number of elements in list; different from the lists length

    // creates empty list
    public MyArrayList(){
        list = getNewArray(MIN_CAPACITY);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        list = getNewArray(MIN_CAPACITY);
        size = 0;
    }

    // helper to create new array of type E
    @SuppressWarnings("unchecked")
    private E[] getNewArray(int size){
        return (E[]) (new Object[size]);
    }

    // helper to copy to a new array with a different length
    private E[] copy(int copyCapacity){
        E[] copy = getNewArray(copyCapacity);
        System.arraycopy(list, 0, copy, 0, size);
        return copy;
    }

    // helper to copy to a new array with a different length skipping over removeIndex
    private E[] copy(int copyCapacity, int removeIndex){
        E[] newList = getNewArray(copyCapacity);
        System.arraycopy(list, 0, newList, 0, removeIndex);
        System.arraycopy(list, removeIndex+1, newList, removeIndex, size-removeIndex-1);
        return newList;
    }

    // helper that checks if the list is full, if so increase the length of list
    private void checkCapacity(){
        if(size == list.length)
            list = copy((int) (list.length * RESIZE_SCALING));
    }

    // helper that checks if the list can accommodate the number of elements to
    // be added, if not it will increase the length of list so that it can
    private void checkCapacity(int sizeIncrease){
        int newSize = size + sizeIncrease;
        if (newSize > list.length){
            double newCapacity = list.length;
            do newCapacity = Math.floor(newCapacity * RESIZE_SCALING);
            while(newCapacity < newSize);
            list = copy((int) newCapacity);
        }
    }

    // helper that confirms an index is inside list's range
    private void checkIndexInRange(int index) throws IndexOutOfBoundsException{
        if(index >= size || index < 0)
            throw new IndexOutOfBoundsException("index of " + index + "is out of range for list of size " + size);
    }

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

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if(toAdd == null) throw new NullPointerException("cannot add a null element to list");
        checkCapacity();

        list[size] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("cannot append null list");
        checkCapacity(toAdd.size());
        for (int i = 0; i < toAdd.size(); i++) {
            E item = toAdd.get(i);
            if(item != null){
                list[size] = item;
                size++;
            }
        }
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        checkIndexInRange(index);
        return list[index];
    }


    // helper that checks is array length should be reduced
    private boolean isArrayOversized(){
        return (size-1) < MIN_FILL_PERCENTAGE * list.length && list.length > MIN_CAPACITY;
    }

    // helper that returns the length list should be reduced to
    // size reduction divides by RESIZE_SCALING twice reducing frequency of this action
    private int reducedCapacity(){
        double firstScale = Math.ceil(list.length/RESIZE_SCALING);
        return Math.max(MIN_CAPACITY, (int) Math.ceil(firstScale/RESIZE_SCALING));
    }

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

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        checkIndexInRange(index);
        if(toChange == null) throw new NullPointerException("cannot set element in list to null");
        E replaced = list[index];
        list[index] = toChange;
        return replaced;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return indexOf(toFind) != -1;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("toHold cannot be null");
        if (toHold.length < size) toHold = Arrays.copyOf(toHold, size);
        System.arraycopy(list, 0, toHold, 0, size);
        return toHold;
    }

    @Override
    public Object[] toArray() {
        return copy(size);
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

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
