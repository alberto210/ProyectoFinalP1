package logico;

import java.io.Serializable;
import java.util.Date;

public abstract class Empleado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7540682719052504335L;
	protected String cedula;
	protected String nombre;
	protected float sueldoBase;
	protected float sueldoUnitario;
	protected int horasTrabajadas;
	protected Usuario user;
	protected Date fechaDeInicio;
	
	
	public Empleado(String cedula, String nombre, float sueldoBase, float sueldoUnitario,
			int horasTrabajadas) {
		super();
		this.fechaDeInicio = new Date();
		this.cedula = cedula;
		this.nombre = nombre;
		this.sueldoBase = sueldoBase;
		this.sueldoUnitario = sueldoUnitario;
		this.horasTrabajadas = horasTrabajadas;
		this.user = new Usuario();
	}
	public Empleado() {
		
	}


	public abstract float sueldoTotal();

	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public float getSueldoBase() {
		return sueldoBase;
	}


	public void setSueldoBase(float sueldoBase) {
		this.sueldoBase = sueldoBase;
	}


	public float getSueldoUnitario() {
		return sueldoUnitario;
	}


	public void setSueldoUnitario(float sueldoUnitario) {
		this.sueldoUnitario = sueldoUnitario;
	}


	public int getHorasTrabajadas() {
		return horasTrabajadas;
	}


	public void setHorasTrabajadas(int horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}


	public Usuario getUser() {
		return user;
	}


	public void setUser(Usuario user) {
		this.user = user;
	}
	public Date getFechaDeInicio() {
		return fechaDeInicio;
	}
	public void setFechaDeInicio(Date fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}
	
}
