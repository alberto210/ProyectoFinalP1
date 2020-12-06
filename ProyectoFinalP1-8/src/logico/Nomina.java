package logico;

import java.io.Serializable;
import java.util.Date;

public class Nomina implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Empleado emp;
	private Date fechaDeEmision;
	private String estado; //Pagada o no
	
	public Nomina(int id,Empleado emp, Date fechaDeEmision, String estado) {
		super();
		this.id =("N-"+id);
		if(emp instanceof Administrativo) {
			this.emp = new Administrativo();
		}else {
			this.emp = new Comercial();
		}
		this.emp = emp;
		this.fechaDeEmision = fechaDeEmision;
		this.estado = estado;
	}

	public Empleado getEmp() {
		return emp;
	}

	public void setEmp(Empleado emp) {
		this.emp = emp;
	}

	public Date getFechaDeEmision() {
		return fechaDeEmision;
	}

	public void setFechaDeEmision(Date fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
