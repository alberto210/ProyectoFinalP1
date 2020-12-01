package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Altice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Usuario> misUsuarios;
	private static Usuario loginUser;
	private static boolean firstTime;
	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Cliente> misClientes;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Factura> misFacturas;
	private static Altice empresa = null;
	
	public static Altice getInstance() {
		if(empresa==null) {
			empresa = new Altice();
		}
		return empresa;
	}

	public Altice() {
		super();
		this.misEmpleados = new ArrayList<Empleado>();
		this.misClientes = new ArrayList<Cliente>();
		this.misPlanes = new ArrayList<Plan>();
		this.misFacturas = new ArrayList<Factura>();
		misUsuarios = new ArrayList<Usuario>();
	}

	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}

	public void setMisUsuarios(ArrayList<Usuario> misUsuarios) {
		this.misUsuarios = misUsuarios;
	}

	public static Usuario getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(Usuario loginUser) {
		Altice.loginUser = loginUser;
	}

	public static boolean isFirstTime() {
		return firstTime;
	}

	public static void setFirstTime(boolean firstTime) {
		Altice.firstTime = firstTime;
	}

	public static Altice getEmpresa() {
		return empresa;
	}

	public static void setEmpresa(Altice empresa) {
		Altice.empresa = empresa;
	}

	public ArrayList<Empleado> getMisEmpleados() {
		return misEmpleados;
	}

	public void setMisEmpleados(ArrayList<Empleado> misEmpleados) {
		this.misEmpleados = misEmpleados;
	}

	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}

	public ArrayList<Plan> getMisPlanes() {
		return misPlanes;
	}

	public void setMisPlanes(ArrayList<Plan> misPlanes) {
		this.misPlanes = misPlanes;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}

	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}
	
	public void regUser(Usuario user) {
		misUsuarios.add(user);
	}
	
	public boolean confirmLogin(String usuario, String contrasena) {
		boolean login = false;
		for (Usuario user : misUsuarios) {
			if(user.getNombreDeUsuario().equals(usuario) && user.getContrasena().equals(contrasena)){
				loginUser = user;
				login = true;
			}
		}
		return login;
	}
	
	public void agregarPlanACliente(String idPlan, String cedula, String idEmpleado) {
		Cliente client = buscarCliente(cedula);
		Plan oferta = buscarPlan(idPlan);
		Empleado emp = buscarEmpleado(idEmpleado);
		if (client != null && oferta != null && emp != null) {
			Date hoy = new Date();
			oferta.setFechaDeEmision(hoy); //Se agrega fecha en que se agrego el plan al cliente
			oferta.setEmpleado(emp); // se Agrega el empleado que agrego el plan
			client.getMisPlanes().add(oferta);
			
		}
		
		
	}

	private Plan buscarPlan(String idPlan) {
		Plan aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i<misPlanes.size()){
			if(misPlanes.get(i).getId().equalsIgnoreCase(idPlan)) {
				aux = misPlanes.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	private Cliente buscarCliente(String cedula) {
		Cliente aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i<misClientes.size()){
			if(misClientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
				aux = misClientes.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	private Empleado buscarEmpleado(String id) {
		Empleado aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i<misEmpleados.size()){
			if(misEmpleados.get(i).getId().equalsIgnoreCase(id)) {
				aux = misEmpleados.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	private void generarFactura(String cedulaCliente) {
		Cliente client = buscarCliente(cedulaCliente);
		if(client != null) {
			float monto = 0;
			for(Plan aux: client.getMisPlanes()) { //Considerando si remover este for o no segun la estructura de factura
				monto += aux.getPrecioTotal(); //Sumo los montos de las facturas
			}
			Factura fac = new Factura(false,client,monto); //Comprobar que es estado??
			client.getMisFacturas().add(fac);
			misFacturas.add(fac);
		}
				
	}
	
	public void generarTodasLasFacturas() {
		for(Cliente client: misClientes) {
			generarFactura(client.getCedula());
		}
	}
	
	//crear plan contemplando que se hara con interfaz visual, toma en cuenta que servicios tiene habilitado
	public void crearPlan(String nombre, String id,boolean internet,boolean telefono,boolean cable,int cantMinutos,int cantMegas,int cantCanales) {
		int caso = verificarCaso(internet,telefono,cable);
		if (caso != -1) {
			Plan aux = null;
			switch (caso) {
				case 1: {
					aux = new Plan(nombre,id,internet,telefono,cable,0,cantMegas,0);
				}
				case 2:{
					aux = new Plan(nombre,id,internet,telefono,cable,cantMinutos,0,0);
				}
				case 3:{
					aux = new Plan(nombre,id,internet,telefono,cable,0,0,cantCanales);
				}
				case 4:{
					aux = new Plan(nombre,id,internet,telefono,cable,cantMinutos,cantMegas,0);
				}
				case 5:{
					aux = new Plan(nombre,id,internet,telefono,cable,0,cantMegas,cantCanales);
				}
				case 6: {
					aux = new Plan(nombre,id,internet,telefono,cable,cantMinutos,0,cantCanales);
				}
				case 7: {
					aux = new Plan(nombre,id,internet,telefono,cable,cantMinutos,cantMegas,cantCanales);
				}
			}
			if (aux != null) {
				misPlanes.add(aux);
			}
		}
	}

	private int verificarCaso(boolean internet, boolean telefono, boolean cable) {
		int caso = -1;
		if(internet) {
			caso = 1;
		}
		if(telefono) {
			caso = 2;
		}
		if(cable) {
			caso = 3;
		}
		if (internet && telefono) {
			caso = 4;
		}
		if (internet && cable) {
			caso = 5;
		}
		if (telefono && cable) {
			caso = 6;
		}
		if (internet && telefono && cable ) {
			caso = 7;
		}
		return caso;
	}
	
	public void Recargo() {
		
	}
}
