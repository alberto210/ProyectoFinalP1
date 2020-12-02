package logico;

public class Main {
	public static void main(String[] args) {
		
		Altice company = new Altice();
		Cliente client = new Cliente("1","Alexis","Una Casa Argentina", "1234567","test@yahoo.com");
		Empleado emp = new Comercial("2", "Darwin", 100,100,5,5);
		company.getMisEmpleados().add(emp);
		company.getMisClientes().add(client);
		//company.generarFactura("1", "2");
		System.out.println(company.getMisFacturas().get(0).getPrimeraFactura());
		//company.generarFactura("1", "2");
		System.out.println(company.getMisFacturas().get(1).getPrimeraFactura());
		//company.generarFactura("1", "2");
		System.out.println(company.getMisFacturas().get(2).getPrimeraFactura());
		
	}
}
