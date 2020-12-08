package logico;

public class Main {
	public static void main(String[] args) {
		
		Altice company = new Altice();
		Cliente client = new Cliente("1","Alexis","Una Casa Argentina", "1234567","test@yahoo.com");
		Empleado emp = new Comercial("2", "Darwin", 100,100,5);
		company.getMisEmpleados().add(emp);
		company.getMisClientes().add(client);
		company.crearPlan("TV", "TV", 1, false, false, true, 0, 0, 100);
		company.crearPlan("TV", "TV", 2, false, false, true, 0, 0, 200);
		company.crearPlan("TV", "TV", 3, false, false, true, 0, 0, 150);
		company.crearPlan("TV", "TV", 4, false, false, true, 0, 0, 300);
		company.agregarPlanACliente("P-1", "1", "2");
		company.agregarPlanACliente("P-2", "1", "2");
		company.agregarPlanACliente("P-3", "1", "2");
		company.agregarPlanACliente("P-4", "1", "2");
		company.getMisClientes().get(0).getMisPlanes().get(0).getFechaDeEmision().setDate(5);
		company.getMisClientes().get(0).getMisPlanes().get(0).getFechaDeEmision().setMonth(9);
		company.getMisClientes().get(0).getMisPlanes().get(1).getFechaDeEmision().setDate(15);
		company.getMisClientes().get(0).getMisPlanes().get(2).getFechaDeEmision().setDate(20);
		company.getMisClientes().get(0).getMisPlanes().get(3).getFechaDeEmision().setDate(25);
		company.generarFacturaParaUnPlan(client, company.getMisPlanes().get(0));
		company.generarFacturaParaUnPlan(client, company.getMisPlanes().get(1));
		company.generarFacturaParaUnPlan(client, company.getMisPlanes().get(2));
		company.generarFacturaParaUnPlan(client, company.getMisPlanes().get(3));
		company.getMisFacturas().get(0).cobrarDiasConsumidosPrimeraFactura();
		/*System.out.println(company.getMisFacturas().get(0).getCorte());
		System.out.println(company.getMisFacturas().get(1).getCorte());
		System.out.println(company.getMisFacturas().get(2).getCorte());
		System.out.println(company.getMisFacturas().get(3).getCorte());*/
		/*System.out.println(company.getMisFacturas().get(0).cobrarDiasConsumidosPrimeraFactura());
		System.out.println(company.getMisFacturas().get(1).cobrarDiasConsumidosPrimeraFactura());
		System.out.println(company.getMisFacturas().get(2).cobrarDiasConsumidosPrimeraFactura());
		System.out.println(company.getMisFacturas().get(3).cobrarDiasConsumidosPrimeraFactura());*/
		/*
		//company.generarFactura("1", "2");
		System.out.println(company.getMisFacturas().get(0).getPrimeraFactura());
		//company.generarFactura("1", "2");
		System.out.println(company.getMisFacturas().get(1).getPrimeraFactura());
		//company.generarFactura("1", "2");
		System.out.println(company.getMisFacturas().get(2).getPrimeraFactura());*/
		
	}
}
