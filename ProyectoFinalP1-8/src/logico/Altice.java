package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Altice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Usuario loginUser;
	private static Empleado loginEmpleado;
	private int idPlan = 1;
	private int idNomina = 1;
	private int idFactura = 1;
	private static boolean firstTime;
	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Cliente> misClientes;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Factura> misFacturas;
	private ArrayList<Nomina> misNominas;
	private Usuario usuarioDefault;
	private static Altice empresa = null;
	public boolean admin = false;
	
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
		this.misNominas = new ArrayList<Nomina>();
		usuarioDefault= new Usuario();
	}

	public  void aumentarIdPlan() {
		idPlan++;
	}
	public  int getIdPlan() {
		return idPlan;
	}
	public  void aumentarIdNomina() {
		idNomina++;
	}
	public  int getIdNomina() {
		return idNomina;
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
	
	
	public boolean confirmLogin(String usuario, String contrasena) {
		boolean login = false;
		if(usuarioDefault.getNombreDeUsuario().equals(usuario) && usuarioDefault.getContrasena().equals(contrasena)) {
			loginUser = usuarioDefault;
			login = true;
			admin=true;
		}
		for (Empleado user : misEmpleados) {
			if(user.getUser().getNombreDeUsuario().equals(usuario) && user.getUser().getContrasena().equals(contrasena)){
				loginUser = user.getUser();
				loginEmpleado = user;
				login = true;
				admin = false;
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

	public Plan buscarPlan(String idPlan) {
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

	public Cliente buscarCliente(String cedula) {
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
	
	public Empleado buscarEmpleado(String cedula) {
		Empleado aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i<misEmpleados.size()){
			if(misEmpleados.get(i).getCedula().equalsIgnoreCase(cedula)) {
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
			Factura fac = new Factura(idFactura,false,client,null); 
			idFactura++;
			client.getMisFacturas().add(fac);
			misFacturas.add(fac);
		}
				
	}
	
	public void generarFacturaParaUnPlan(Cliente client,Plan plan) {
		if(client != null) {
			Factura fac = new Factura(idFactura,true,client,plan); 
			idFactura++;
			client.getMisFacturas().add(fac);
			misFacturas.add(fac);
		}
				
	}
	
	public boolean generarTodasLasFacturas() {
		boolean generar = false;
		if(misClientes.size() != 0){
			for(Cliente client: misClientes) {
				for(Plan plansito: client.getMisPlanes()) {
					generarFacturaParaUnPlan(client, plansito);
					generar=true;	
				}
			}

		}else {
			generar = false;
		}
			
		return generar;
	}
	
	//crear plan contemplando que se hara con interfaz visual, toma en cuenta que servicios tiene habilitado
	public void crearPlan(String tipo,String nombre, int id,boolean internet,boolean telefono,boolean cable,int cantMinutos,int cantMegas,int cantCanales) {
		int caso = verificarCaso(internet,telefono,cable);
		if (caso != -1) {
			Plan aux = null;
			switch (caso) {
				case 1: {
					aux = new Plan(tipo,nombre,id,internet,telefono,cable,0,cantMegas,0);
				}
				case 2:{
					aux = new Plan(tipo,nombre,id,internet,telefono,cable,cantMinutos,0,0);
				}
				case 3:{
					aux = new Plan(tipo,nombre,id,internet,telefono,cable,0,0,cantCanales);
				}
				case 4:{
					aux = new Plan(tipo,nombre,id,internet,telefono,cable,cantMinutos,cantMegas,0);
				}
				case 5:{
					aux = new Plan(tipo,nombre,id,internet,telefono,cable,0,cantMegas,cantCanales);
				}
				case 6: {
					aux = new Plan(tipo,nombre,id,internet,telefono,cable,cantMinutos,0,cantCanales);
				}
				case 7: {
					aux = new Plan(tipo,nombre,id,internet,telefono,cable,cantMinutos,cantMegas,cantCanales);
				}
			}
			if (aux != null) {
				Date hoy = new Date();
				aux.setFechaDeEmision(hoy);
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

	public void regUserDefault(Usuario aux) {
		this.usuarioDefault = aux;
		
	}

	public static Empleado getLoginEmpleado() {
		return loginEmpleado;
	}

	public static void setLoginEmpleado(Empleado loginEmpleado) {
		Altice.loginEmpleado = loginEmpleado;
	}
	
	
	public int[] CantUsiarosPorPlan() {
		int[] usuarios = new int[7];
		String nombrePlan = null;
		String tipoPlan = null;
		int cantInternet = 0;
		int cantVoz = 0;
		int cantTV = 0;
		int cantDoblePlay1 = 0;
		int cantDoblePlay2 = 0;
		int cantDoblePlay3 = 0;
		int cantTriplePlay = 0;
		for (Cliente usuario : misClientes) {
			for (Plan plan : usuario.getMisPlanes()) {
				nombrePlan = plan.getNombre();
				tipoPlan = plan.getTipo();
				switch(nombrePlan) {
				case "Tripleplay":
					cantTriplePlay++;
					break;
				case "Dobleplay":
					if(tipoPlan.equalsIgnoreCase("Internet + TV")) {
						cantDoblePlay1++;
					}
					else if(tipoPlan.equalsIgnoreCase("Internet + Voz Digital")){
						cantDoblePlay2++;
					}
					else if(tipoPlan.equalsIgnoreCase("TV + Voz Digital")){
						cantDoblePlay3++;
					}
					break;
				case "Internet":
					cantInternet++;
					break;
				case "Altice TV":
					cantTV++;
					break;
				case "Voz Digital":
					cantVoz++;
					break;
				}
			}
		}
		usuarios[0] = cantTriplePlay;
		usuarios[1] = cantDoblePlay1;
		usuarios[2] = cantDoblePlay2;
		usuarios[3] = cantDoblePlay3;
		usuarios[4] = cantInternet;
		usuarios[5] = cantTV;
		usuarios[6] = cantVoz;
		return usuarios;
	}
	
	public boolean pagarFactura(float monto, String id, String cedula) {
		boolean pagar = false;
		Cliente cliente = buscarCliente(cedula);
		if(cliente != null) {
			Factura factura = cliente.findFactura(id);
			if(factura != null) {
				if(monto>0) {
					pagar = factura.pagarFactura(monto);
				}
			}
		}
		return pagar;
	}
	
	public Factura findFactura(String id) {
		Factura aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i<misFacturas.size()){
			if(misFacturas.get(i).getCodFactura().equalsIgnoreCase(id)) {
				aux = misFacturas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	//REVISAR
	
	public void insertarPlan(Plan planes) {
		misPlanes.add(planes);
	}

	public void registrarCliente(Cliente cliente) {
		misClientes.add(cliente);
	}
	
	public boolean crearNominas(){
		Date hoy = new Date();
		boolean emitir = false;
		/*for(Nomina temp : misNominas) {
			if (hoy.getMonth() == temp.getFechaDeEmision().getMonth() && hoy.getDate() > 5) {
				emitir=true;
			}
				
		}*/
		if(!emitir) {
			for(Empleado aux : misEmpleados) {
				Nomina e= new Nomina(idNomina,aux, hoy, "No Pagada");
				idNomina++;
				misNominas.add(e);
			}
		}
		return emitir;
	}

	public ArrayList<Nomina> getMisNominas() {
		return misNominas;
	}

	public void setMisNominas(ArrayList<Nomina> misNominas) {
		this.misNominas = misNominas;
	}

	public Nomina buscarNomina(String id) {
		Nomina aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i<misNominas.size()){
			if(misNominas.get(i).getId().equalsIgnoreCase(id)) {
				aux = misNominas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	@SuppressWarnings("deprecation")
	public void actualizarCantHoras() {
		Date hoy = new Date();
		for(Empleado emp : misEmpleados) {
			if(emp.getFechaDeInicio().getDate() < hoy.getDate()) {
				emp.setHorasTrabajadas(emp.getHorasTrabajadas()+(8*(hoy.getDate()-emp.getFechaDeInicio().getDate())));
				emp.setFechaDeInicio(hoy);	
			}	
		}
	}
	
}
