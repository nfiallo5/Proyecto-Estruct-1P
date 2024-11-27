package ec.edu.espol.List;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class MiLinkedList<E extends Comparable<E>> implements Iterable<E>, Serializable {
    protected Nodo<E> head;
    protected Nodo<E> tail;
    protected int size;
	
    public MiLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void add(E element){
        Nodo<E> newNode = new Nodo<>(element);
        if (head == null) head = tail = newNode;
        else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            this.tail = newNode;
        }
        size++; 
    }
        
    private Nodo<E> getNodo(int index){
	    if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Nodo<E> current = head;
        for(int i = 0; i < index; i++) current = current.getNext(); 
        return current;
    }

    //Refactorizar Método
    public E get(int indice){
	    Nodo<E> nodo = getNodo(indice);
	    return nodo.getData();
    }

    public E getLast(){
	    if(tail == null) throw new IndexOutOfBoundsException();
        return tail.getData();
    }

    public E removeFirst() {
        if (head == null) throw new IndexOutOfBoundsException();
        E data = head.getData();
        if (size == 1) head = tail = null;
        else {
            head = head.getNext();
            head.setPrev(null);
        }
        size--;
        return data;
    }

    public E removeLast(){
	    if(tail == null) throw new IndexOutOfBoundsException();
        E data = tail.getData();
        if(size == 1) head = tail = null;
        else {
            this.tail = tail.getPrev();
            tail.setNext(null);
        }
        size--;
        return data;
    }

    public E remove(E element){
        Nodo<E> current = head;
        E data;
        for(int i = 0; i < size; i++){
            if(current.getData().equals(element)){
                data = remove(i);
                return data;
            }
            current = current.getNext();
        }
        throw new RuntimeException("No existe el elemento en la lista");
    }

    public E remove(int index){
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
	    Nodo<E> current = head; 
	    E data;
        if(index == 0) return removeFirst();
        else if(index == size - 1) return removeLast();
        else{
            for(int i = 0; i < index; i++) current = current.getNext(); 
            data = current.getData();
            Nodo<E> anterior = current.getPrev();
            anterior.setNext(current.getNext());
            Nodo<E> posterior = current.getNext();
            posterior.setPrev(current.getPrev());
        }
        size--;
        return data;
	}

    public void sort() {
        if (head == null || head.getNext() == null) return; 
        head = mergeSort(head);
    
        tail = head;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
    }
    
    private Nodo<E> mergeSort(Nodo<E> node) {
        if (node == null || node.getNext() == null) return node;
    
        Nodo<E> middle = getMiddle(node);
        Nodo<E> secondHalf = middle.getNext();
        middle.setNext(null);
    
        if (secondHalf != null) secondHalf.setPrev(null);
    
        Nodo<E> left = mergeSort(node);
        Nodo<E> right = mergeSort(secondHalf);
    
        return merge(left, right);
    }
    
    private Nodo<E> getMiddle(Nodo<E> node) {
        if (node == null) return null;
    
        Nodo<E> slow = node;
        Nodo<E> fast = node;
    
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }
    
    private Nodo<E> merge(Nodo<E> left, Nodo<E> right) {
        Nodo<E> temp = new Nodo<>(null); 
        Nodo<E> current = temp;
    
        while (left != null && right != null) {
            if (left.getData().compareTo(right.getData()) <= 0) {
                current.setNext(left);
                left.setPrev(current);
                left = left.getNext();
            } else {
                current.setNext(right);
                right.setPrev(current);
                right = right.getNext();
            }
            current = current.getNext();
        }
    
        if (left != null) {
            current.setNext(left);
            left.setPrev(current);
        } else if (right != null) {
            current.setNext(right);
            right.setPrev(current);
        }
    
        return temp.getNext();
    }
    

    public int size(){
    	return size;
    }

    public boolean isEmpty(){
    	return size == 0;
    }
    
    public Iterator<E> iterator() {
        return new MiLinkedListIterator();
    }
    
    public class MiLinkedListIterator implements Iterator<E> {
        protected Nodo<E> current = head;

        public void reset() {
            current = head;
        }

        public E current() {
            if (!hasNext()) throw new IllegalStateException("No hay elemento actual");
            return current.getData();
        }

        public E previous() {
            if (current == null || current.getPrev() == null) throw new IllegalStateException("No hay elemento previo");
            current = current.getPrev();
            return current.getData();
        }

        public int countRemaining() {
            Nodo<E> temp = current;
            int count = 0;
            while (temp != null) {
                count++;
                temp = temp.getNext();
            }
            return count;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new IllegalStateException("No hay más elementos");
            E data = current.getData();
            current = current.getNext();
            return data;
        }

        @Override
        public void remove() {
            if (current == null) throw new IllegalStateException("No hay elementos para eliminar");
            Nodo<E> prev = current.getPrev();
            Nodo<E> next = current.getNext();
            if (prev != null) prev.setNext(next);
            if (next != null) next.setPrev(prev);
            if (current == head) head = next;
            if (current == tail) tail = prev;
            current = next;
            size--;
            if (size == 0) head = tail = null;
        }
    }

}