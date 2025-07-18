package implementations;

import utilities.StackADT;
import utilities.Iterator;
import java.util.EmptyStackException;

public class MyStack<E> implements StackADT<E> {

    private MyArrayList<E> data;

    public MyStack() {
        data = new MyArrayList<>();
    }

    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Cannot push null to stack.");
        data.add(toAdd);
    }

    @Override
    public E pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return data.remove(data.size() - 1);
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return data.get(data.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void clear() {
        data.clear();
    }

    public boolean contains(E element) {
        if (element == null)
            throw new NullPointerException();
        return data.contains(element);
    }

    public boolean equals(Object other) {
        if (!(other instanceof MyStack<?>))
            return false;

        MyStack<?> otherStack = (MyStack<?>) other;

        if (this.size() != otherStack.size())
            return false;

        Object[] thisArray = this.toArray();
        Object[] otherArray = otherStack.toArray();

        for (int i = 0; i < thisArray.length; i++) {
            if (!thisArray[i].equals(otherArray[i]))
                return false;
        }

        return true;
    }

    public Object[] toArray() {
        int size = data.size();
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = data.get(size - 1 - i);
        }
        return arr;
    }

    public E[] toArray(E[] holder) {
        int size = data.size();
        if (holder.length < size) {
            holder = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), size);
        }
        for (int i = 0; i < size; i++) {
            holder[i] = data.get(size - 1 - i);
        }
        if (holder.length > size) {
            holder[size] = null;
        }
        return holder;
    }

    public int search(E element) {
        if (element == null)
            return -1;

        int index = 1;
        for (int i = data.size() - 1; i >= 0; i--) {
            if (data.get(i).equals(element))
                return index;
            index++;
        }
        return -1;
    }

    public boolean stackOverflow() {
        return false;
    }

    public Iterator<E> iterator() {
        Object[] elements = this.toArray();
        return new ReverseArrayIterator<>(elements);
    }

    // fixed custom reverse iterator
    private static class ReverseArrayIterator<E> implements Iterator<E> {
        private final Object[] array;
        private int cursor;

        public ReverseArrayIterator(Object[] array) {
            this.array = array;
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < array.length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements in the stack.");
            }
            return (E) array[cursor++];
        }
    }
}
