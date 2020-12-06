package logico;

public class Comercial extends Empleado {

	int cantAños;
	public Comercial(String cedula, String nombre, float sueldoBase, float sueldoUnitario,
			int horasTrabajadas, int cantAños) {
		super(cedula, nombre, sueldoBase, sueldoUnitario, horasTrabajadas);
		this.cantAños = cantAños;
	}
	
	public Comercial() {
		
	}
	public float sueldoTotal() {
		float sueldoTotal = 0;
		sueldoTotal = (float) (sueldoBase + sueldoUnitario*horasTrabajadas + 0.20*cantAños*sueldoUnitario);
		return sueldoTotal;
		
	}

	public int getCantAños() {
		return cantAños;
	}

	public void setCantAños(int cantAños) {
		this.cantAños = cantAños;
	}


}
