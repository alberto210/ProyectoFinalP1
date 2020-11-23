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
	
	@SuppressWarnings("deprecation")
	public float cobrarDiasConsumidosPrimeraFactura() {
		monto = 0;
		for (Plan plan : misPlanes) {
			int date = plan.getFechaDeEmision().getDate();
			int mes = plan.getFechaDeEmision().getMonth();
			if(date>15 && primeraFactura == true) {
				if(mes==0||mes==2||mes==4||mes==6||mes==7||mes==9||mes==11) {
					monto += (float) ((plan.getPrecioTotal()/31)*(32-date));
				}
				if(mes==3||mes==5||mes==8||mes==10) {
					monto += (float) ((plan.getPrecioTotal()/30)*(31-date));
				}
				if(mes==1) {
					monto += (float) ((plan.getPrecioTotal()/28)*(29-date));
				}
			}
			if(date<15 && primeraFactura == true) {
				monto += plan.getPrecioTotal();
			}
		}
		return monto;
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
