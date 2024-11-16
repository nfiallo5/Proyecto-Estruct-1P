package ec.edu.espol.Atributos;

import java.io.Serializable;

public abstract class Numero implements Serializable{
    protected String numerotelefonico;
    protected String descripcion;

    public Numero(String numero){
        if(contarPalabra(numero) != 10){
            throw new NumberFormatException();
	}
	this.numerotelefonico = numero;
    }

    private int contarPalabra(String palabra){
	if(palabra == null || palabra.isEmpty()){
            return 0;
	} 
	String[] letras = palabra.trim().split("\\s");
	return letras.length;
    }
}
