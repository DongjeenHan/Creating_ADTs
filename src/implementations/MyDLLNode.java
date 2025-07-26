package implementations;

/**
 * A node used in a doubly linked list.
 * 
 * @param <E> the type of element stored in the node
 */
public class MyDLLNode<E> {
    /** The element stored in this node. */
    E element;

    /** Reference to the next node in the list. */
    MyDLLNode<E> next;

    /** Reference to the previous node in the list. */
    MyDLLNode<E> prev;

    /**
     * Constructs a new node with the given element.
     * 
     * @param element the data to store in this node
     */
    public MyDLLNode(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    /** @return the element stored in this node */
    public E getElement() {
        return element;
    }

    /** @return the next node */
    public MyDLLNode<E> getNext() {
        return next;
    }

    /** @return the previous node */
    public MyDLLNode<E> getPrev() {
        return prev;
    }

    /**
     * Sets the element for this node.
     * 
     * @param element the new element
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Sets the next node reference.
     * 
     * @param next the next node
     */
    public void setNext(MyDLLNode<E> next) {
        this.next = next;
    }

    /**
     * Sets the previous node reference.
     * 
     * @param prev the previous node
     */
    public void setPrev(MyDLLNode<E> prev) {
        this.prev = prev;
    }
}
