package ec.edu.espol.Atributos;

import java.io.Serializable;

public abstract class Email implements Serializable{
	protected String email;

	public Email(String email){
		this.email = email;
	}

	public void setCorreo(String correo){
		this.email = correo;
	}
}
