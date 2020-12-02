package logico;

import java.io.Serializable;

public class Usuario implements Serializable{
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoDeUsuario;
	private String nombreDeUsuario;
	private String contrasena;
	
	public Usuario(String tipoDeUsuario, String nombreDeUsuario, String contrasena) {
		super();
		this.tipoDeUsuario = tipoDeUsuario;
		this.nombreDeUsuario = nombreDeUsuario;
		this.contrasena = contrasena;
	}
	public Usuario() {
		this.tipoDeUsuario = "Administrativo";
		this.nombreDeUsuario = "admin";
		this.contrasena = "admin";
	}
	
	public String getTipoDeUsuario() {
		return tipoDeUsuario;
	}
	public void setTipoDeUsuario(String tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}
	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}
	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	
	

}
