package logico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Factura {

	private String codFactura;
	private boolean verificacion; //Pagada o no
	private String estado; // Que es estado????
	private Cliente cliente;
	private ArrayList<Plan> misPlanes;
	private boolean primeraFactura;
	private float monto;
	private Date fechaEmision;
	private Date corte;
	
	public Factura(boolean verificacion, Cliente cliente, float monto) {
		super();
		this.verificacion = verificacion;
		this.cliente = new Cliente();
		this.cliente = cliente;
		this.misPlanes = new ArrayList<Plan>();
		this.monto = monto;
		comprobarPrimeraFactura();
		DiaCorte();
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


	private void comprobarPrimeraFactura() {
		if(cliente.getMisFacturas().size() == 0) {
			this.primeraFactura = true;
		}else {
			this.primeraFactura = false;
		}	
	}
	
	private void DiaCorte() {
		this.fechaEmision = new Date();
		int compare = 15 - fechaEmision.getDate();
		Calendar fecha = new GregorianCalendar();
		fecha.setTime(fechaEmision);
		if(compare < 0 && primeraFactura == true) {
			fecha.set(Calendar.DAY_OF_MONTH, 1);
			fecha.add(Calendar.MONTH, 1);
			this.corte = fecha.getTime(); 
		}
		else if(Calendar.DATE < 15 && primeraFactura == true) {
			fecha.add(Calendar.MONTH, 1);
			this.corte = fecha.getTime();
		}else {
			fecha.add(Calendar.MONTH, 1);
			this.corte = fecha.getTime();
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
	
	/*
	 Metodo para el recargo.Hay que ver que pasa a las 2 meses
	 */
	public float Recargo() {
		float recargo = 0;
		int mes = CalcMesDesdeCorte();
		if(mes == 1) {
			recargo = (float) (monto*0.05);
		}else if(mes == 3) {
			recargo = (float) (monto*0.1 + monto*0.05);
		}
		return recargo;
		
	}
	
	public float Descuento() {
		float descuento = 0;
		int cantPlanes = cliente.getMisPlanes().size();
		switch(cantPlanes) {
			case 1:
				descuento = 0;
				break;
			case 2:
				descuento = (float) (monto*0.1);
				break;
			case 3:
				descuento = (float) (monto*0.3);
				break;
			default:
				descuento = 0;
				break;
		}
		return descuento;
	}
	
	private int CalcMesDesdeCorte() {
		int mes = 0;
		Calendar inicio = new GregorianCalendar();
		Calendar fin = new GregorianCalendar();
		inicio.setTime(corte);
		fin.setTime(new Date());
		int difY = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
		int difM = fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
		int difD = fin.get(Calendar.DAY_OF_MONTH) - inicio.get(Calendar.DAY_OF_MONTH);
		if(difM > 0) {
			difY--;
		}
		if(difD < 0) {
			mes = difY*12 + difM;
			mes--;
		}
		else {
			mes = difY*12 + difM;
		}
		return mes;
	}
	
}
