/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.List;

import java.util.Iterator;

/**
 *
 * @author sebas
 * @param <T>
 */
public class CircularLinkedList <T> extends MiLinkedList<T>{
    
    	// Arma una lista circular, formando el nodo siguiente del tail como el head
	// y el anterior al head como tail
    private void formarCircular(){
	    this.tail.setSigt(head);
	    this.head.setPrev(tail);
    }
    
    public CircularLinkedList(){
        super();
    }
    
    @Override
    public void add(T elemento){
        super.add(elemento);
        formarCircular();
    }
    
    @Override
    public T removeLast(){
        T data = super.removeLast();
        formarCircular();
        return data;
    }
    

    @Override
    public CircularLinkedListIterator iterator(){
        return new CircularLinkedListIterator();
    }
    
    public class CircularLinkedListIterator implements Iterator<T> {
        private Nodo<T> current = head;
        private boolean initialIteration = true;

        // Devuelve si hay más elementos por recorrer
        @Override
        public boolean hasNext() {
            return size > 0 && (current != head || initialIteration);
        }

        // Devuelve el siguiente elemento
        @Override
        public T next() {
            if (!hasNext()) throw new IllegalStateException("No more elements");
            T data = current.getData();
            current = current.getSigt();
            initialIteration = false;
            return data;
        }

        // Retrocede al elemento anterior
        public T previous() {
            if (current == null) throw new IllegalStateException("No elements to traverse");
            current = current.getPrev();
            return current.getData();
        }

        // Reinicia el iterador al inicio
        public void reset() {
            current = head;
            initialIteration = true;
        }

        // Devuelve el próximo elemento sin avanzar
        public T peek() {
            if (!hasNext()) throw new IllegalStateException("No elements to peek");
            return current.getData();
        }

        // Elimina el elemento actual
        @Override
        public void remove() {
            if (current == null) throw new IllegalStateException("No current element to remove");
            Nodo<T> nextNode = current.getSigt();
            Nodo<T> prevNode = current.getPrev();

            // Desconectar el nodo actual
            if (current == head) head = nextNode;
            if (current == tail) tail = prevNode;

            prevNode.setSigt(nextNode);
            nextNode.setPrev(prevNode);

            current = nextNode;
            size--;

            if (size == 0) head = tail = null; // Lista vacía
        }
    }

}
