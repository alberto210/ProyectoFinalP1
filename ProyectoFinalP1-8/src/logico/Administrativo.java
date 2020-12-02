package logico;

public class Administrativo extends Empleado {

	private String cargo;
	//Cargos pueden ser: Gerente, Representante Local, Representante Regional, Mesa Administrativa 
	public Administrativo(String cedula, String nombre, float sueldoBase, float sueldoUnitario,
			int horasTrabajadas, String cargo) {
		super(cedula, nombre, sueldoBase, sueldoUnitario, horasTrabajadas);
		this.cargo = cargo;
	}
	
	public float sueldoTotal() {
		float sueldoTotal = 0;
		switch(cargo) {
			case "Gerente":{
				sueldoTotal = sueldoBase + 2*sueldoUnitario*horasTrabajadas;
			}
			case "Representante Local":{
				sueldoTotal = sueldoBase + 5*sueldoUnitario*horasTrabajadas;
			}
			case "Representante Regional":{
				sueldoTotal = sueldoBase + 10*sueldoUnitario*horasTrabajadas;
			}
			case "Mesa Administrativa":{
				sueldoTotal = sueldoBase + 20*sueldoUnitario*horasTrabajadas;
			}
		}
		return sueldoTotal;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
