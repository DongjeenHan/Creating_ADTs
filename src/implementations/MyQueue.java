package implementations;

import utilities.QueueADT;
import utilities.Iterator;
import exceptions.EmptyQueueException;

/**
 * Implementation of a generic queue using a doubly linked list.
 *
 * @param <E> the type of elements in this queue
 */
public class MyQueue<E> implements QueueADT<E> {

    private MyDLL<E> data;

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        data = new MyDLL<>();
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param toAdd the element to add
     * @throws NullPointerException if element is null
     */
    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Cannot push null.");
        data.add(toAdd);
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the removed element
     * @throws EmptyQueueException if the queue is empty
     */
    @Override
    public E pop() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException("Queue is empty. Nothing to pop.");
        return data.remove(0);
    }

    /**
     * Returns the front element without removing it.
     *
     * @return the front element
     * @throws EmptyQueueException if the queue is empty
     */
    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException("Queue is empty. Nothing to peek.");
        return data.get(0);
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
     * Clears all elements from the queue.
     */
    public void dequeueAll() {
        clear();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MyQueue<?>))
            return false;

        MyQueue<?> otherQueue = (MyQueue<?>) other;

        if (this.size() != otherQueue.size())
            return false;

        Iterator<E> it1 = this.iterator();
        Iterator<?> it2 = otherQueue.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().equals(it2.next()))
                return false;
        }

        return true;
    }

    /**
     * Returns all elements in the queue as an array.
     *
     * @return array of elements
     */
    public Object[] toArray() {
        return data.toArray();
    }

    /**
     * Returns all elements in the queue as an array of type E.
     *
     * @param holder the array to hold the elements
     * @return array of elements
     */
    public E[] toArray(E[] holder) throws NullPointerException {
        return data.toArray(holder);
    }

    /**
     * Always returns false since the queue is never full.
     *
     * @return false
     */
    public boolean isFull() {
        return false;
    }

    /**
     * Checks if the queue contains the given element.
     *
     * @param element the element to search for
     * @return true if found, false otherwise
     */
    public boolean contains(E element) {
        return data.contains(element);
    }

    /**
     * Returns the 1-based position of the element from the front.
     *
     * @param element the element to find
     * @return position if found, -1 otherwise
     */
    public int search(E element) {
        if (element == null) return -1;

        Iterator<E> it = this.iterator();
        int index = 1;
        while (it.hasNext()) {
            if (it.next().equals(element)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Returns an iterator over the elements in the queue.
     */
    public Iterator<E> iterator() {
        return data.iterator();
    }

    /**
     * Adds an element to the end of the queue. Same as push().
     *
     * @param element the element to add
     * @throws NullPointerException if element is null
     */
    public void enqueue(E element) throws NullPointerException {
        push(element);
    }

    /**
     * Removes and returns the front element. Same as pop().
     *
     * @return the removed element
     * @throws EmptyQueueException if the queue is empty
     */
    public E dequeue() throws EmptyQueueException {
        return pop();
    }
}
