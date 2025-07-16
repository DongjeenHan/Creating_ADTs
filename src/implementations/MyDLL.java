package implementations;

import utilities.Iterator;
import utilities.ListADT;
import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {
    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException();
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        if (index == 0) {
            newNode.setNext(head);
            if (head != null) head.setPrev(newNode);
            head = newNode;
            if (tail == null) tail = head;
        } else if (index == size) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        } else {
            MyDLLNode<E> current = getNode(index);
            MyDLLNode<E> previous = current.getPrev();
            previous.setNext(newNode);
            newNode.setPrev(previous);
            newNode.setNext(current);
            current.setPrev(newNode);
        }
        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        return add(size, toAdd);
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException();
        for (int i = 0; i < toAdd.size(); i++) {
            add(toAdd.get(i));
        }
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return getNode(index).getElement();
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        MyDLLNode<E> toRemove = getNode(index);

        if (toRemove == head) {
            head = head.getNext();
            if (head != null) head.setPrev(null);
            else tail = null;
        } else if (toRemove == tail) {
            tail = tail.getPrev();
            if (tail != null) tail.setNext(null);
        } else {
            toRemove.getPrev().setNext(toRemove.getNext());
            toRemove.getNext().setPrev(toRemove.getPrev());
        }
        size--;
        return toRemove.getElement();
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) throw new NullPointerException();
        MyDLLNode<E> current = head;
        int index = 0;
        while (current != null) {
            if (toRemove.equals(current.getElement())) {
                return remove(index);
            }
            current = current.getNext();
            index++;
        }
        return null;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException();
        checkIndex(index);
        MyDLLNode<E> node = getNode(index);
        E old = node.getElement();
        node.setElement(toChange);
        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException();
        MyDLLNode<E> current = head;
        while (current != null) {
            if (toFind.equals(current.getElement())) return true;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException();
        if (toHold.length < size) {
            toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
        }
        MyDLLNode<E> current = head;
        int index = 0;
        while (current != null) {
            toHold[index++] = current.getElement();
            current = current.getNext();
        }
        return toHold;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyDLLNode<E> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.getElement();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private MyDLLNode<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException();
            E data = current.getElement();
            current = current.getNext();
            return data;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    private MyDLLNode<E> getNode(int index) {
        MyDLLNode<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) current = current.getNext();
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) current = current.getPrev();
        }
        return current;
    }
}
