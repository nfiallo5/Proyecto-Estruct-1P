package ec.edu.espol.Atributos;

import java.io.Serializable;

public abstract class Email implements Serializable{
	protected String email;
	private String tipo = "personal";

	public Email(String email){
		this.email = email;
	}

	public Email(String email, String tipo){
		this(email);
		this.tipo = tipo;
	}

	public void setCorreo(String correo){
		this.email = correo;
	}

	public void setTipo(String tipo){
		this.tipo = tipo;
	}
}
