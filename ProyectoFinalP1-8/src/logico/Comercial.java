package logico;

public class Comercial extends Empleado {

	int cantA�os;
	public Comercial(String id, String cedula, String nombre, float sueldoBase, float sueldoUnitario,
			int horasTrabajadas, int cantA�os) {
		super(cedula, cedula, cedula, sueldoBase, sueldoBase, horasTrabajadas);
		this.cantA�os = cantA�os;
	}

	public float sueldoTotal() {
		float sueldoTotal = 0;
		sueldoTotal = (float) (sueldoBase + sueldoUnitario*horasTrabajadas + 0.20*cantA�os*sueldoUnitario);
		return sueldoTotal;
		
	}

	public int getCantA�os() {
		return cantA�os;
	}

	public void setCantA�os(int cantA�os) {
		this.cantA�os = cantA�os;
	}


}