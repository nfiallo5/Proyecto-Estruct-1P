/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.List;

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

}
