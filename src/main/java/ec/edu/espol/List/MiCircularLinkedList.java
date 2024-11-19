
package ec.edu.espol.List;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

public class MiCircularLinkedList <E> extends MiLinkedList<E> implements Serializable{
    private void formarCircular(){
        this.tail.setNext(head);
        this.head.setPrev(tail);
    }
    
    public MiCircularLinkedList(){
        super();
    }
    
    @Override
    public void add(E elemento){
        super.add(elemento);
        formarCircular();
    }
    
    @Override
    public E removeLast(){
        E data = super.removeLast();
        formarCircular();
        return data;
    }
    
    @Override
    public MiCircularLinkedListIterator iterator() {
        return new MiCircularLinkedListIterator();
    }
    
    public class MiCircularLinkedListIterator implements Iterator<E> {
        private Nodo<E> current = head;
        private boolean initialIteration = true;

        @Override
        public boolean hasNext() {
            return size > 0 && (current != head || initialIteration);
        }

        @Override
        public E next() {
            if (!hasNext()) throw new IllegalStateException("No hay más elementos");
            E data = current.getData();
            current = current.getNext();
            initialIteration = false;
            return data;
        }

        public E previous() {
            if (current == null) throw new IllegalStateException("No hay elemento previo");
            current = current.getPrev();
            return current.getData();
        }

        public void reset() {
            current = head;
            initialIteration = true;
        }

        public E current() {
            if (!hasNext()) throw new IllegalStateException("No hay elemento actual");
            return current.getData();
        }

        @Override
        public void remove() {
            if (current == null) throw new IllegalStateException("No hay elementos para eliminar");
            Nodo<E> nextNode = current.getNext();
            Nodo<E> prevNode = current.getPrev();
            if (current == head) head = nextNode;
            if (current == tail) tail = prevNode;
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
            current = nextNode;
            size--;
            if (size == 0) head = tail = null;
            formarCircular();
        }
    }
}