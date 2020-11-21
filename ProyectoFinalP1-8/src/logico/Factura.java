package logico;

import java.util.ArrayList;

public class Factura {

	private String codFactura;
	private boolean verificacion;
	private String estado;
	private Cliente cliente;
	private Empleado empleado;
	private ArrayList<Plan> misPlanes;
	
	public Factura(String codFactura, boolean verificacion, String estado, Cliente cliente, Empleado empleado) {
		super();
		this.codFactura = codFactura;
		this.verificacion = verificacion;
		this.estado = estado;
		this.cliente = new Cliente();
		this.cliente = cliente;
		this.empleado = empleado;
		this.misPlanes = new ArrayList<Plan>();
	}

	public String getCodFactura() {
		return codFactura;
	}

	public void setCodFactura(String codFactura) {
		this.codFactura = codFactura;
	}

	public boolean isVerificacion() {
		return verificacion;
	}

	public void setVerificacion(boolean verificacion) {
		this.verificacion = verificacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public ArrayList<Plan> getMisPlanes() {
		return misPlanes;
	}

	public void setMisPlanes(ArrayList<Plan> misPlanes) {
		this.misPlanes = misPlanes;
	}
	
	
}
