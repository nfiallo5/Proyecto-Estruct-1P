package ec.edu.espol.Atributos;

import java.io.Serializable;

public class Email implements Serializable{
	private static final long serialVersionUID = 1L;
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
