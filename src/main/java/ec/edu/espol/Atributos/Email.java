package ec.edu.espol.Atributos;

public class Email {
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
