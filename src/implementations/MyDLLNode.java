package implementations;

public class MyDLLNode<E> {
    E element;
    MyDLLNode<E> next;
    MyDLLNode<E> prev;

    public MyDLLNode(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    public E getElement() {
        return element;
    }

    public MyDLLNode<E> getNext() {
        return next;
    }

    public MyDLLNode<E> getPrev() {
        return prev;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setNext(MyDLLNode<E> next) {
        this.next = next;
    }

    public void setPrev(MyDLLNode<E> prev) {
        this.prev = prev;
    }
}
