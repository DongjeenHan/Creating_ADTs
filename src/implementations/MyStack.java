package implementations;

import utilities.StackADT;
import utilities.Iterator;
import java.util.EmptyStackException;

/**
 * A stack implementation using MyArrayList.
 * Follows Last-In-First-Out (LIFO) principle.
 *
 * @param <E> the type of elements in this stack
 */
public class MyStack<E> implements StackADT<E> {

    private MyArrayList<E> data;

    /**
     * Constructs an empty stack.
     */
    public MyStack() {
        data = new MyArrayList<>();
    }

    /**
     * Pushes an element onto the stack.
     */
    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Cannot push null to stack.");
        data.add(toAdd);
    }

    /**
     * Removes and returns the top element from the stack.
     */
    @Override
    public E pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return data.remove(data.size() - 1);
    }

    /**
     * Returns the top element without removing it.
     */
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

    /**
     * Checks if the stack contains the given element.
     */
    public boolean contains(E element) {
        if (element == null)
            throw new NullPointerException();
        return data.contains(element);
    }

    /**
     * Compares this stack to another for equality.
     */
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

    /**
     * Converts the stack to an Object array.
     */
    public Object[] toArray() {
        int size = data.size();
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = data.get(size - 1 - i);
        }
        return arr;
    }

    /**
     * Converts the stack to an array of specified type.
     */
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

    /**
     * Searches for the element and returns its position from the top.
     */
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

    /**
     * Indicates if stack has overflowed (always false).
     */
    public boolean stackOverflow() {
        return false;
    }

    /**
     * Returns an iterator for the stack in reverse order.
     */
    public Iterator<E> iterator() {
        Object[] elements = this.toArray();
        return new ReverseArrayIterator<>(elements);
    }

    /**
     * Reverse iterator for the stack.
     */
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
