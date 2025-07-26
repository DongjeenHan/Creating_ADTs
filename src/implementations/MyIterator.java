package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;

/**
 * A simple iterator over an array of elements.
 *
 * @param <E> the type of elements returned by this iterator
 */
public class MyIterator<E> implements Iterator<E> {
    private E[] data;
    private int current;

    /**
     * Constructs a new iterator for the given array up to the specified size.
     *
     * @param data the source array
     * @param size the number of elements to include in iteration
     */
    public MyIterator(E[] data, int size) {
        this.data = (E[]) new Object[size];
        System.arraycopy(data, 0, this.data, 0, size);
        this.current = 0;
    }

    /**
     * Returns true if the iteration has more elements.
     *
     * @return true if next() will return an element
     */
    @Override
    public boolean hasNext() {
        return current < data.length;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element
     * @throws NoSuchElementException if no more elements exist
     */
    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in iterator.");
        }
        return data[current++];
    }
}
