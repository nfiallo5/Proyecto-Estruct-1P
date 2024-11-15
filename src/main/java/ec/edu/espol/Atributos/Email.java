package ec.edu.espol.Atributos;

public abstract class Email {
	protected String email;

	public Email(String email){
		this.email = email;
	}

	public void setCorreo(String correo){
		this.email = correo;
	}
}
