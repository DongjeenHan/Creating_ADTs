package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;

public class MyIterator<E> implements Iterator<E> {
    private E[] data;
    private int current;

    public MyIterator(E[] data, int size) {
        this.data = (E[]) new Object[size];
        System.arraycopy(data, 0, this.data, 0, size);
        this.current = 0;
    }
    
    @Override
    public boolean hasNext() {
        return current < data.length;
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in iterator.");
        }
        return data[current++];
    }
}
