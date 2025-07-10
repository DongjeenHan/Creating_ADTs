package utilities;

/**
 * Defines basic stack operations.
 * follows first in last out principle.
 *
 * @param <T> type of elements in the stack
 */
public interface StackADT<T> {

    /**
     * Adds an element to the top.
     * @param element the element to add
     * @throws NullPointerException if <code>element</code> is <code>null</code>
     */
    void push(T element);

    /**
     * Removes the top element in the stack.
     * @return the removed element
     * @throws EmptyQueueException if the stack is empty
     */
    T pop();

    /**
     * view the top element without removing it.
     * @return the top element
     * @throws EmptyQueueException if the stack is empty
     */
    T peek();

    /**
     * Checks if the stack is empty.
     * @return <code>true</code> if the stack is empty; <code>false</code> otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements.
     * @return the size of the stack
     */
    int size();

    /**
     * Removes all elements from the stack.
     */
    void clear();
}
