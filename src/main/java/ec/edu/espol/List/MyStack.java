package ec.edu.espol.List;

import java.io.Serializable;

public class MyStack<E> extends MiLinkedList<E> implements Serializable{
	public MyStack(){}

	public E peek(){
		E data = tail.getData();
		return data;
	}

	public E pop(){
		E data = removeLast();
		return data;
	}

	public void push(E element){
		add(element);
	}
}