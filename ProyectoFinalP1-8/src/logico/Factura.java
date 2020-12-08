package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Factura implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 178664072442668691L;
	private String codFactura;
	private boolean verificacion; //Pagada o no
	private String estado; // Pagada, noPagada, Atrasada
	private Cliente cliente;
	private Plan miPlan;
	private boolean primeraFactura;
	private float monto;
	private float montoPagado;
	private Date fechaEmision;
	private Date corte;
	
	public Factura(int idFactura,boolean verificacion, Cliente cliente, Plan plan) {
		super();
		this.codFactura = ("F-"+idFactura);
		this.verificacion = verificacion;
		this.cliente = new Cliente();
		this.cliente = cliente;
		this.miPlan = plan;
		
		this.estado= "No Pagada";
		this.primeraFactura = true;
		//comprobarPrimeraFactura();
		DiaCorte();
		monto =0;
		montoPagado =0;
	}
	

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

	public boolean getPrimeraFactura() {
		return primeraFactura;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public Date getFechaEmision() {
		return fechaEmision;
	}


	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}


	public Date getCorte() {
		return corte;
	}


	public void setCorte(Date corte) {
		this.corte = corte;
	}


	/*private void comprobarPrimeraFactura() {
		if(cliente.getMisFacturas().size() == 0) {
			this.primeraFactura = true;
		}else {
			this.primeraFactura = false;
		}	
	}*/
	
	@SuppressWarnings("deprecation")
	private void DiaCorte() {
		this.fechaEmision = new Date();
		int compare = 15 - miPlan.getFechaDeEmision().getDate();
		Calendar fecha = new GregorianCalendar();
		fecha.setTime(miPlan.getFechaDeEmision());
		if(compare < 0) {
			fecha.set(Calendar.DAY_OF_MONTH, 1);
			fecha.add(Calendar.MONTH, 1);
			this.corte = fecha.getTime(); 
		}
		else {
			fecha.add(Calendar.MONTH, 1);
			this.corte = fecha.getTime();
		}
	}
	
	@SuppressWarnings("deprecation")
	public float cobrarDiasConsumidosPrimeraFactura() {
			monto =0;
			int date = miPlan.getFechaDeEmision().getDate();
			int mes = miPlan.getFechaDeEmision().getMonth();
			if(date>15 && primeraFactura) {
				if(mes==0||mes==2||mes==4||mes==6||mes==7||mes==9||mes==11) {
					monto += (float) ((miPlan.generarPrecioTotalConImpuestos()/31)*(33-date)) - miPlan.generarPrecioTotalConImpuestos()*descuento();
				}
				if(mes==3||mes==5||mes==8||mes==10) {
					monto += (float) ((miPlan.generarPrecioTotalConImpuestos()/30)*(32-date)) - miPlan.generarPrecioTotalConImpuestos()*descuento();
				}
				if(mes==1) {
					monto += (float) ((miPlan.generarPrecioTotalConImpuestos()/28)*(30-date)) - miPlan.generarPrecioTotalConImpuestos()*descuento();
				}
			}
			else {
				monto += miPlan.generarPrecioTotalConImpuestos() - miPlan.generarPrecioTotalConImpuestos()*descuento() + miPlan.generarPrecioTotalConImpuestos()*recargo();
				primeraFactura=false;
			}
		return monto;
	}
	
	/*
	Implementar metodo para cobrar los dias consumidos si el tiempo de emision es mayor del dia 15.
	Agregar dia de corte cuando se emite la factura si es entre el dia 1 y 15 de un mes, si se emite en una fecha mayor al dia 15, agregar dia de corte predeterminado como el dia primero.
	*/
	
	/*
	 Metodo para el recargo.Hay que ver que pasa a las 2 meses
	 */
	public float recargo() {
		float recargo = 0;
		int mes = CalcMesDesdeCorte();
			if(mes == 1) {
				if(!estado.equalsIgnoreCase("Pagada")) {
					estado="Atrasada-1";
				}
				recargo = (float) (0.05);
			}else if(mes==2){
				if(!estado.equalsIgnoreCase("Pagada")) {
					estado="Atrasada-2";
				}
				recargo = (float) (0.1);
			}else if(mes >= 3) {
				if(!estado.equalsIgnoreCase("Pagada")) {
					estado="Cancelada";
				}
				recargo = (float) (0.1 + 0.05);
			}
		return recargo;
		
	}
	
	public float descuento(){
		float descuento = 0;
		if(miPlan.getNombre().equalsIgnoreCase("Dobleplay")) {
			descuento=(float) 0.1;
		}else if(miPlan.getNombre().equalsIgnoreCase("Tripleplay")){
			descuento=(float) 0.3;
		}else {
			descuento=0;
		}
		return descuento;
	}
	
	private int CalcMesDesdeCorte() {
		int meses = 0;
		Calendar hoy = new GregorianCalendar();
		Calendar aux = new GregorianCalendar();
		aux.setTime(corte);
		int diffyear = hoy.get(Calendar.YEAR)-aux.get(Calendar.YEAR);
		int diffmonth = hoy.get(Calendar.MONTH)-aux.get(Calendar.MONTH);
		meses = 12*diffyear;
		meses+=diffmonth;
		if(hoy.get(Calendar.DATE)<aux.get(Calendar.DATE)) {
			meses--;
		}
		return meses;
	}
	
	public boolean pagarFactura(float montoCliente) {
		boolean aux = false;
		float pago = monto;
		if(!verificacion) {
			if(montoCliente<pago) {
				aux = false;
			}
			else {
				aux = true;
				pago -= montoCliente;
				verificacion = true;
				setEstado("Pagada");
			}
		}
		return aux;
	}


	public float getMontoPagado() {
		return montoPagado;
	}


	public void setMontoPagado(float montoPagado) {
		this.montoPagado = montoPagado;
	}


	public Plan getMiPlan() {
		return miPlan;
	}


	public void setMiPlan(Plan miPlan) {
		this.miPlan = miPlan;
	}
	
}
