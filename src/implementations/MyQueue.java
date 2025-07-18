package implementations;

import utilities.QueueADT;
import utilities.Iterator;
import exceptions.EmptyQueueException;

public class MyQueue<E> implements QueueADT<E> {

    private MyDLL<E> data;

    public MyQueue() {
        data = new MyDLL<>();
    }

    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Cannot push null.");
        data.add(toAdd);
    }

    @Override
    public E pop() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException("Queue is empty. Nothing to pop.");
        return data.remove(0);
    }

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

    public Object[] toArray() {
        return data.toArray();
    }

    public E[] toArray(E[] holder) throws NullPointerException {
        return data.toArray(holder);
    }

    public boolean isFull() {
        return false;
    }

    public boolean contains(E element) {
        return data.contains(element);
    }

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

    public Iterator<E> iterator() {
        return data.iterator();
    }
}
