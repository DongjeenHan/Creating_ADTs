package implementations;

import utilities.Iterator;
import utilities.ListADT;

public class MyDLL<E> implements ListADT<E> {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        return false;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        return false;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        return false;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        return null;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return false;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
