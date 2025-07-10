package utilities;

import exceptions.EmptyQueueException;

/**
 * Defines basic queue operations.
 * follows first in first out principle.
 *
 * @param <T> type of elements in the queue
 */
public interface QueueADT<T> {

    /**
     * Adds an element to the end of the queue.
     * @param element the element to add
     * @throws NullPointerException if <code>element</code> is <code>null</code>
     */
    void push(T element) throws NullPointerException;

    /**
     * Removes the first element in the queue.
     * @return the removed element
     * @throws EmptyQueueException if the queue is empty
     */
    T pop() throws EmptyQueueException;

    /**
     * View the next element.
     * @return the first element in the queue
     * @throws EmptyQueueException if the queue is empty
     */
    T peek() throws EmptyQueueException;

    /**
     * Checks if the queue is empty.
     * @return <code>true</code> if the queue is empty; <code>false</code> otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements.
     * @return the size of the queue
     */
    int size();

    /**
     * Removes all elements from the queue.
     */
    void clear();
}
