package ec.edu.espol.Atributos;

import java.io.Serializable;

public abstract class Numero implements Serializable{
    protected String numerotelefonico;
    private static final long serialVersionUID = 1L;


    public Numero(String numero){
        if(!numero.matches("\\d{10}")) throw new NumberFormatException();
	    this.numerotelefonico = numero;
    }



}
