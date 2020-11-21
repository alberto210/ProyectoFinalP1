package logico;

import java.util.ArrayList;

public class Factura {

	private String codFactura;
	private boolean verificacion; //Pagada o no
	//private String estado; // Que es estado????
	private Cliente cliente;
	private ArrayList<Plan> misPlanes;
	private boolean primeraFactura;
	private float monto;
	
	public Factura(boolean verificacion/*, String estado*/, Cliente cliente, float monto) {
		super();
		this.verificacion = verificacion;
		//this.estado = estado;
		this.cliente = new Cliente();
		this.cliente = cliente;
		this.misPlanes = new ArrayList<Plan>();
		this.monto = monto;
		comprobarPrimeraFactura();
	}
	private void comprobarPrimeraFactura() {
		if(cliente.getMisFacturas().size() == 0) {
			this.primeraFactura = true;
		}else {
			this.primeraFactura = false;
		}	
	}
	/*
	 Implementar metodo para reconocer la fecha de agregar plan al cliente y si es la primera factura; listo
	 Implementar metodo para cobrar los dias consumidos si el tiempo de emision es mayor del dia 15.
	 Agregar dia de corte cuando se emite la factura si es entre el dia 1 y 15 de un mes, si se emite en una fecha mayor al dia 15, agregar dia de corte predeterminado como el dia primero.
	 */

	public String getCodFactura() {
		return codFactura;
	}

	public void setCodFactura(String codFactura) {
		this.codFactura = codFactura;
	}

	public boolean getVerificacion() {
		return verificacion;
	}

	public void setVerificacion(boolean verificacion) {
		this.verificacion = verificacion;
	}

	/*public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}*/

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Plan> getMisPlanes() {
		return misPlanes;
	}

	public void setMisPlanes(ArrayList<Plan> misPlanes) {
		this.misPlanes = misPlanes;
	}
	public boolean getPrimeraFactura() {
		return primeraFactura;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	
}
