package logico;

import java.io.Serializable;
import java.util.Date;

public class Plan implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7523075717313531227L;
	private String estado; //Cancelado o Activo
	private boolean internet;
	private boolean telefono;
	private boolean cable;
	private int cantMinutos;
	private int cantMegas;
	private int cantCanales;
	private String nombre;
	private String tipo; //Single, doble o triple
	private String id;
	private float precioTotal;
	private float ITBIS;
	private float CDT;
	private float ISC;
	private float precioTotalConImpuestos;
	private Date fechaDeEmision;
	private Empleado emp;
	
	public Plan(String tipo, String nombre, int id,boolean internet,boolean telefono,boolean cable,int cantMinutos,int cantMegas,int cantCanales) {
		super();
		this.estado = "Activo";
		this.tipo = tipo;
		this.nombre = nombre;
		this.id = ("P-"+id);
		this.fechaDeEmision = null;
		this.emp = null;
		this.internet = internet;
		this.telefono = telefono;
		this.cable = cable;
		this.cantMinutos = cantMinutos;
		this.cantMegas = cantMegas;
		this.cantCanales=cantCanales;
		this.precioTotal = 0;
		this.precioTotalConImpuestos = 0;
		generarPrecioTotal();
		this.ITBIS = 0;
		this.CDT = 0;
		this.ISC = 0;
		
		
	}

	private void generarPrecioTotal() {
		// Segun altice, el precio de minutos prepago es de 8 pesos, precio estimado de 1 Mbps es 160 pesos y se estima 8.55 pesos por canal
		precioTotal = (float) (cantMinutos*8 + cantMegas*160 + cantCanales*8.55);
	}

	
	public double generarPrecioTotalConImpuestos() {
		
		ITBIS = (float) (precioTotal * 0.18);
		CDT = (float) (precioTotal * 0.02);
		ISC = (float) (precioTotal * 0.10);
		
		
		precioTotalConImpuestos = (float) (precioTotal + ITBIS + CDT + ISC);
		
		return precioTotalConImpuestos;
		
	}
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	
	public Date getFechaDeEmision() {
		return fechaDeEmision;
	}
	
	public void setFechaDeEmision(Date hoy) {
		this.fechaDeEmision = hoy;
	}
	
	public Empleado getEmpleado() {
		return emp;
	}

	public void setEmpleado(Empleado aux) {
		this.emp = aux;
	}

	public int getCantMinutos() {
		return cantMinutos;
	}

	public void setCantMinutos(int cantMinutos) {
		this.cantMinutos = cantMinutos;
	}

	public int getCantMegas() {
		return cantMegas;
	}

	public void setCantMegas(int cantMegas) {
		this.cantMegas = cantMegas;
	}

	public int getCantCanales() {
		return cantCanales;
	}

	public void setCantCanales(int cantCanales) {
		this.cantCanales = cantCanales;
	}

	public boolean isInternet() {
		return internet;
	}

	public boolean isTelefono() {
		return telefono;
	}

	public boolean isCable() {
		return cable;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getITBIS() {
		return ITBIS;
	}

	public void setITBIS(float iTBIS) {
		ITBIS = iTBIS;
	}

	public float getCDT() {
		return CDT;
	}

	public void setCDT(float cDT) {
		CDT = cDT;
	}

	public float getISC() {
		return ISC;
	}

	public void setISC(float iSC) {
		ISC = iSC;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}