package logico;

import java.io.Serializable;

public class Usuario implements Serializable{
    
	public Usuario(String tipoDeUsuario, String nombreDeUsuario, String contrasena) {
		super();
		this.tipoDeUsuario = tipoDeUsuario;
		this.nombreDeUsuario = nombreDeUsuario;
		this.contrasena = contrasena;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoDeUsuario;
	private String nombreDeUsuario;
	private String contrasena;
	
	
	
	
	
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
