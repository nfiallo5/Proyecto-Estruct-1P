package ec.edu.espol.List;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

public class MiLinkedList<E> implements Iterable<E>, Serializable{
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

	/*
        public void add(T elemento, int indice){
            Nodo<T> nuevoNodo = new Nodo<>(elemento);
            if(head == null){
		head = nuevoNodo;
		tail = nuevoNodo;
            } else {
                Nodo<T> actual = getNodo(indice);

            }
	}
    */
        
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

    private Nodo<E> mergeSort(Nodo<E> head, Comparator<E> comparator) {
        if (head == null || head.getNext() == null) return head;
        Nodo<E> middle = getMiddle(head);
        Nodo<E> nextOfMiddle = middle.getNext();
        middle.setNext(null);
        Nodo<E> left = mergeSort(head, comparator);
        Nodo<E> right = mergeSort(nextOfMiddle, comparator);
        return sortedMerge(left, right, comparator);
    }

    public void sort(Comparator<E> comparator){
		head = mergeSort(head, comparator);
    }

    private Nodo<E> sortedMerge(Nodo<E> a, Nodo<E> b, Comparator<E> comparator) {
        if (a == null) return b;
        if (b == null) return a;
        Nodo<E> result = comparator.compare(a.getData(), b.getData()) <= 0 ? a : b;
        result.setNext(sortedMerge(result == a ? a.getNext() : a, result == b ? b.getNext() : b, comparator));
        if (result.getNext() != null) result.getNext().setPrev(result);
        return result;
    }

    private Nodo<E> getMiddle(Nodo<E> head) {
        if (head == null) return head;
        Nodo<E> slow = head, fast = head;
        while (fast.getNext() != null && fast.getNextNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext();
        }
        return slow;
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