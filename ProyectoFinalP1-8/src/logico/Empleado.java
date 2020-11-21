package logico;

public abstract class Empleado {

	protected String id;
	protected String cedula;
	protected String nombre;
	protected float sueldoBase;
	protected float sueldoUnitario;
	protected int horasTrabajadas;
	
	
	public Empleado(String id, String cedula, String nombre, float sueldoBase, float sueldoUnitario,
			int horasTrabajadas) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.sueldoBase = sueldoBase;
		this.sueldoUnitario = sueldoUnitario;
		this.horasTrabajadas = horasTrabajadas;
	}


	public abstract float sueldoTotal();


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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
	
}
