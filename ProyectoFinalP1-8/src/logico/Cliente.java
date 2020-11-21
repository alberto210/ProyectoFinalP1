package logico;

import java.util.ArrayList;

public class Cliente {

	private String cedula;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private ArrayList<Factura> misFacturas;
	private ArrayList<Plan> misPlanes;
	
	public Cliente(String cedula, String nombre, String direccion, String telefono, String email) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.misFacturas = new ArrayList<Factura>();
		this.misPlanes = new ArrayList<Plan>();
	}
	
	public Cliente() {
		super();
		this.cedula = "";
		this.nombre = "";
		this.direccion ="";
		this.telefono = "";
		this.email = "";
		this.misFacturas = new ArrayList<Factura>();
		this.misPlanes = new ArrayList<Plan>();
	}

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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}

	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}

	public ArrayList<Plan> getMisPlanes() {
		return misPlanes;
	}

	public void setMisPlanes(ArrayList<Plan> misPlanes) {
		this.misPlanes = misPlanes;
	}
	
}
