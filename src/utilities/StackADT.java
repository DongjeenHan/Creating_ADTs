package utilities;

/**
 * Defines basic stack operations.
 *
 * @param <T> type of elements in the stack
 */
public interface StackADT<T> {

    /**
     * Adds an element to the top.
     * @param element the element to add
     * @throws NullPointerException if element is null
     */
    void push(T element);

    /**
     * Removes and returns the top element.
     * @return the removed element
     * @throws java.util.NoSuchElementException if stack is empty
     */
    T pop();

    /**
     * Returns the top element without removing it.
     * @return the top element
     * @throws java.util.NoSuchElementException if stack is empty
     */
    T peek();

    /**
     * Checks if the stack is empty.
     * @return true if empty, false otherwise
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
