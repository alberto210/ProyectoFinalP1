package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Control implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Usuario> misUsuarios;
	private static Control control;
	private static Usuario loginUser;
	
	
	private Control() {
		misUsuarios = new ArrayList<>();
	}
	
	public static Control getInstance(){
		if(control == null){
			control = new Control();
		}
		return control;
	}

	public ArrayList<Usuario> getMisUsers() {
		return misUsuarios;
	}

	public void setMisUsers(ArrayList<Usuario> misUsuarios) {
		this.misUsuarios = misUsuarios;
	}

	public static Control getControl() {
		return control;
	}

	public static void setControl(Control control) {
		Control.control = control;
	}

	public static Usuario getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(Usuario loginUser) {
		Control.loginUser = loginUser;
	}

	public void regUser(Usuario user) {
		misUsuarios.add(user);
		
	}

	
	

	public boolean confirmLogin(String usuario, String contrasena) {
		boolean login = false;
		for (Usuario user : misUsuarios) {
			if(user.getNombreDeUsuario().equals(usuario) && user.getContrasena().equals(contrasena)){
				loginUser = user;
				login = true;
			}
		}
		return login;
	}

}