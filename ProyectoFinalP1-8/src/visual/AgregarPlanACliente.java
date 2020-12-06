package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logico.Altice;
import logico.Cliente;
import logico.Plan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;

public class AgregarPlanACliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCedula;
	private JTextField txtDireccion;
	private JTextField txtEmail;
	private JTextField txtCedulaConfirm;
	private JLabel lblValid;
	private JButton btnRegistrar;
	private JButton btnBuscar;
	private JButton btnForward;
	private JButton btnBack;
	private JButton btnFacturar;
	private JComboBox cbxNombre;
	private JTable tablePlanesDisp;
	private JTable tablePlanesElegidos;
	private ArrayList<Plan> planesDisponibles = new ArrayList<Plan>();
	private ArrayList<Plan> planesElegidos = new ArrayList<Plan>();
	public static DefaultTableModel modeloDisp;
	public static DefaultTableModel modeloElegido;
	public static ArrayList<Object[]> filasDisp;
	public static ArrayList<Object[]> filasElegido;
	private int indexDisp = -1;
	private int indexElegidos = -1;
	private String seleccion = "<Todos>";
	private Cliente cliente = null;
	private Plan auxDisp = null;
	private Plan auxElegido = null;
	

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 * @param string 
	 */
	public AgregarPlanACliente(String title) {
		setTitle(title);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Logo.jpg"));
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 805, 545);
		setLocationRelativeTo(null);
		for(Plan copiaplan: Altice.getInstance().getMisPlanes()) {
			if(copiaplan.getEstado().equalsIgnoreCase("Activo")) {
				planesDisponibles.add(copiaplan);
			}
		}
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panel_DatosCliente = new JPanel();
			panel_DatosCliente.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_DatosCliente.setBounds(10, 11, 525, 167);
			panel.add(panel_DatosCliente);
			panel_DatosCliente.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Nombre: ");
			lblNewLabel.setBounds(10, 22, 59, 14);
			panel_DatosCliente.add(lblNewLabel);
			
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setBounds(66, 18, 173, 23);
			panel_DatosCliente.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Tel\u00E9fono: ");
			lblNewLabel_1.setBounds(261, 56, 59, 14);
			panel_DatosCliente.add(lblNewLabel_1);
			
			txtTelefono = new JTextField();
			txtTelefono.setEditable(false);
			txtTelefono.setBounds(330, 52, 185, 23);
			panel_DatosCliente.add(txtTelefono);
			txtTelefono.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("C\u00E9dula: ");
			lblNewLabel_2.setBounds(10, 55, 46, 14);
			panel_DatosCliente.add(lblNewLabel_2);
			
			txtCedula = new JTextField();
			txtCedula.setEditable(false);
			txtCedula.setBounds(66, 52, 173, 23);
			panel_DatosCliente.add(txtCedula);
			txtCedula.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Direcci\u00F3n: ");
			lblNewLabel_3.setBounds(261, 22, 74, 14);
			panel_DatosCliente.add(lblNewLabel_3);
			
			txtDireccion = new JTextField();
			txtDireccion.setEditable(false);
			txtDireccion.setBounds(330, 18, 185, 23);
			panel_DatosCliente.add(txtDireccion);
			txtDireccion.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Email: ");
			lblNewLabel_4.setBounds(10, 100, 46, 14);
			panel_DatosCliente.add(lblNewLabel_4);
			
			lblValid = new JLabel("");
			lblValid.setForeground(new Color(255, 0, 0));
			lblValid.setBounds(330, 76, 185, 14);
			panel_DatosCliente.add(lblValid);
			
			txtEmail = new JTextField();
			txtEmail.setEditable(false);
			txtEmail.setBounds(66, 96, 449, 23);
			panel_DatosCliente.add(txtEmail);
			txtEmail.setColumns(10);
			
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nombre = txtNombre.getText();
					String cedula = txtCedula.getText();
					String direccion = txtDireccion.getText();
					String telefono = txtTelefono.getText();
					String email = txtEmail.getText();
					if(nombre.equalsIgnoreCase("") || cedula.equalsIgnoreCase("") || direccion.equalsIgnoreCase("") || telefono.equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null, "Campos vacíos. Porfavor llene todos los campos", "Información", JOptionPane.WARNING_MESSAGE);
					}
					else {
						cliente = new Cliente(cedula, nombre, direccion, telefono,email);
						Altice.getInstance().registrarCliente(cliente);;
						JOptionPane.showMessageDialog(null, "Cliente registrado satisfactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
						txtNombre.setText(cliente.getNombre());
						txtNombre.setEditable(false);
						txtCedula.setText(cliente.getCedula());
						txtCedula.setEditable(false);
						txtDireccion.setText(cliente.getDireccion());
						txtDireccion.setEditable(false);
						txtTelefono.setText(cliente.getTelefono());
						txtTelefono.setEditable(false);
						txtEmail.setText(cliente.getEmail());
						txtEmail.setEditable(false);
						btnRegistrar.setEnabled(false);
					}
				}
			});
			btnRegistrar.setEnabled(false);
			btnRegistrar.setBounds(426, 130, 89, 23);
			panel_DatosCliente.add(btnRegistrar);
			
			JPanel panel_BuscarCliente = new JPanel();
			panel_BuscarCliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_BuscarCliente.setBounds(545, 11, 234, 167);
			panel.add(panel_BuscarCliente);
			panel_BuscarCliente.setLayout(null);
			
			JLabel lblNewLabel_5 = new JLabel("Cedula del Cliente:");
			lblNewLabel_5.setBounds(10, 46, 214, 14);
			panel_BuscarCliente.add(lblNewLabel_5);
			
			txtCedulaConfirm = new JTextField();
			txtCedulaConfirm.setBounds(10, 71, 214, 23);
			panel_BuscarCliente.add(txtCedulaConfirm);
			txtCedulaConfirm.setColumns(10);
			
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cedulaVer = txtCedulaConfirm.getText();
					cliente = Altice.getInstance().buscarCliente(cedulaVer);
					if(cliente != null && cedulaVer != null) {
						txtNombre.setText(cliente.getNombre());
						txtNombre.setEditable(false);
						txtCedula.setText(cliente.getCedula());
						txtCedula.setEditable(false);
						txtDireccion.setText(cliente.getDireccion());
						txtDireccion.setEditable(false);
						txtTelefono.setText(cliente.getTelefono());
						txtTelefono.setEditable(false);
						txtEmail.setText(cliente.getEmail());
						txtEmail.setEditable(false);
						btnRegistrar.setEnabled(false);
					}
					else {
						txtNombre.setText("");
						txtNombre.setEditable(true);
						txtCedula.setText("");
						txtCedula.setEditable(true);
						txtDireccion.setText("");
						txtDireccion.setEditable(true);
						txtTelefono.setText("");
						txtTelefono.setEditable(true);
						txtEmail.setText("");
						txtEmail.setEditable(true);
						JOptionPane.showMessageDialog(null, "Cliente no encontrado. Porfavor, registrar al cliente", "Información", JOptionPane.WARNING_MESSAGE);
						btnRegistrar.setEnabled(true);
					}
				}
			});
			btnBuscar.setBounds(135, 133, 89, 23);
			panel_BuscarCliente.add(btnBuscar);
			
			JPanel panel_Planes = new JPanel();
			panel_Planes.setBorder(new TitledBorder(null, "Planes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_Planes.setBounds(10, 189, 769, 273);
			panel.add(panel_Planes);
			panel_Planes.setLayout(null);
			
			JLabel lblNewLabel_6 = new JLabel("Nombre del Plan:");
			lblNewLabel_6.setBounds(10, 22, 96, 14);
			panel_Planes.add(lblNewLabel_6);
			
			cbxNombre = new JComboBox();
			cbxNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccion = cbxNombre.getSelectedItem().toString();
					cargarTablaNombre(seleccion);
				}
			});
			cbxNombre.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Tripleplay", "Dobleplay", "Altice TV", "Internet", "Voz Digital"}));
			cbxNombre.setBounds(116, 18, 186, 23);
			panel_Planes.add(cbxNombre);
			
			JPanel panel_PlanesDisp = new JPanel();
			panel_PlanesDisp.setBounds(10, 47, 323, 215);
			panel_Planes.add(panel_PlanesDisp);
			panel_PlanesDisp.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel_PlanesDisp.add(scrollPane, BorderLayout.CENTER);
			
			modeloDisp = new DefaultTableModel();
			String[] headers = {"ID","Nombre","Megas","Canales","Minutos"};
			modeloDisp.setColumnIdentifiers(headers);
			
			tablePlanesDisp = new JTable();
			tablePlanesDisp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					indexDisp = tablePlanesDisp.getSelectedRow();
					if(indexDisp != -1) {
						btnForward.setEnabled(true);
						auxDisp = Altice.getInstance().buscarPlan(tablePlanesDisp.getValueAt(indexDisp, 0).toString());
					}
				}
			});
			
			
			modeloElegido = new DefaultTableModel();
			String[] headers2 = {"ID","Nombre","Megas","Canales","Minutos"};
			modeloElegido.setColumnIdentifiers(headers2);
			
			tablePlanesDisp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablePlanesDisp.setModel(modeloDisp);
			scrollPane.setViewportView(tablePlanesDisp);
			
			JPanel panel_PlanesElegidos = new JPanel();
			panel_PlanesElegidos.setBounds(436, 47, 323, 215);
			panel_Planes.add(panel_PlanesElegidos);
			panel_PlanesElegidos.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel_PlanesElegidos.add(scrollPane_1, BorderLayout.CENTER);
			
			tablePlanesElegidos = new JTable();
			tablePlanesElegidos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					indexElegidos = tablePlanesElegidos.getSelectedRow();
					if(indexElegidos != -1) {
						btnBack.setEnabled(true);
						auxElegido = buscarPlan(tablePlanesElegidos.getValueAt(indexDisp, 0).toString());
					}
				}
			});
			tablePlanesElegidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablePlanesElegidos.setModel(modeloElegido);
			scrollPane_1.setViewportView(tablePlanesElegidos);
			
			btnForward = new JButton(">>");
			btnForward.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(indexDisp != -1 && auxDisp != null) {
						int i = indexPlanDisp(auxDisp);
						if(i != -1 && !buscarPlanElegido()) {
							planesElegidos.add(auxDisp);
							planesDisponibles.remove(i);
							btnForward.setEnabled(false);
							cargarTablaDisp();
							cargarTablaElegidos();
						}
					}
				}
			});
			btnForward.setBounds(357, 74, 55, 23);
			panel_Planes.add(btnForward);
			btnForward.setEnabled(false);
			
			btnBack = new JButton("<<");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(indexElegidos != -1 && auxElegido != null) {
						int i = indexPlanElegido(auxElegido);
						if(i!= -1 && !buscarPlanDisp()) {
							planesElegidos.remove(i);
							planesDisponibles.add(auxElegido);;
							btnBack.setEnabled(false);
							cargarTablaDisp();
							cargarTablaElegidos();
						}
					}
				}
			});
			btnBack.setBounds(357, 190, 55, 23);
			panel_Planes.add(btnBack);
			btnBack.setEnabled(false);
			
			JLabel lblNewLabel_7 = new JLabel("Planes Elegidos");
			lblNewLabel_7.setBounds(436, 22, 141, 14);
			panel_Planes.add(lblNewLabel_7);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAgregar = new JButton("Agregar Planes");
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cliente != null) {
								int cantPlanes = planesElegidos.size();
								if(cantPlanes > 0) {
									int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea agregarle estos planes al cliente "+cliente.getNombre(), "Confirmación", JOptionPane.OK_CANCEL_OPTION);
									if(option == JOptionPane.OK_OPTION) {
										for (Plan planesEleg : planesElegidos) {
											cliente.getMisPlanes().add(planesEleg);
										}
										int cantPlanesCliente = cliente.getMisPlanes().size();
										if(cantPlanesCliente == 0) {
											if(cantPlanes == 1) {
												JOptionPane.showMessageDialog(null, "Error al agregar el plan al cliente", "Información", JOptionPane.ERROR_MESSAGE);
											}else if(cantPlanes > 1) {
												JOptionPane.showMessageDialog(null, "Error al agregar los planes al cliente", "Información", JOptionPane.ERROR_MESSAGE);
											}
											
										}else {
											if(cantPlanes == 1) {
												JOptionPane.showMessageDialog(null, "Plan Agregado Satisfactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
											}else if(cantPlanes > 1) {
												JOptionPane.showMessageDialog(null, "Planes Agregados Satisfactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
											}
											planesElegidos.clear();
											planesDisponibles.clear();
											for(Plan copiaplan: Altice.getInstance().getMisPlanes()) {
												if(copiaplan.getEstado().equalsIgnoreCase("Activo")) {
													planesDisponibles.add(copiaplan);
												}
											}
											cargarTablaDisp();
											cargarTablaElegidos();
											clean();
											btnFacturar.setEnabled(true);
										}
									}
								}else {
									JOptionPane.showMessageDialog(null, "Porfavor elija los planes que desea agregarle al cliente", "Información", JOptionPane.ERROR_MESSAGE);
								}
						}
					}
				});
				
				btnFacturar = new JButton("Facturar");//boton para pasar a ventana facturar cuando los planes se le hayan agregado al cliente
				btnFacturar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnFacturar.setEnabled(false);
					}
				});
				btnFacturar.setEnabled(false);
				buttonPane.add(btnFacturar);
				btnAgregar.setActionCommand("OK");
				buttonPane.add(btnAgregar);
				getRootPane().setDefaultButton(btnAgregar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargarTablaDisp();
		cargarTablaElegidos();
	}
	
	private void cargarTablaDisp() {
		modeloDisp.setRowCount(0);
		filasDisp = new ArrayList<Object[]>();
		Object[] plan = null;
		for (Plan planDisp : planesDisponibles) {
			plan = new Object[] {planDisp.getId(),planDisp.getNombre(),planDisp.getCantMegas(),planDisp.getCantCanales(),planDisp.getCantMinutos()};
			filasDisp.add(plan);
		}
		for (Object[] filas1 : filasDisp) {
			modeloDisp.addRow(filas1);
		}
	}
	private void cargarTablaNombre(String seleccion) {
		TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<DefaultTableModel>(modeloDisp);
		tablePlanesDisp.setRowSorter(filtro);
		if(seleccion != "<Todos>") {
			filtro.setRowFilter(RowFilter.regexFilter(seleccion));
		}
		else {
			tablePlanesDisp.setRowSorter(filtro);
		}
	}
	
	private void cargarTablaElegidos() {
		modeloElegido.setRowCount(0);
		filasElegido = new ArrayList<Object[]>();
		Object[] plan = null;
		for (Plan planElegidos : planesElegidos) {
			plan = new Object[] {planElegidos.getId(),planElegidos.getNombre(),planElegidos.getCantMegas(),planElegidos.getCantCanales(),planElegidos.getCantMinutos()};
			filasElegido.add(plan);
		}
		for (Object[] filas1 : filasElegido) {
			modeloElegido.addRow(filas1);
		}
	}
	
	private Plan buscarPlan(String idPlan) {
		Plan aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i<planesElegidos.size()){
			if(planesElegidos.get(i).getId().equalsIgnoreCase(idPlan)) {
				aux = planesElegidos.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public boolean buscarPlanElegido() {
		boolean encontrado = false;
		for(Plan temp: planesElegidos) {
			if(auxDisp.getId().equalsIgnoreCase(temp.getId())) {
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public boolean buscarPlanDisp() {
		boolean encontrado = false;
		for(Plan temp: planesDisponibles) {
			if(auxElegido.getId().equalsIgnoreCase(temp.getId())) {
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public int indexPlanDisp(Plan aux) {
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i<planesDisponibles.size()){
			if(planesDisponibles.get(i).getId().equalsIgnoreCase(aux.getId())) {
				index = i;
				encontrado = true;
			}
			i++;
		}
		return index;
	}
	
	public int indexPlanElegido(Plan aux) {
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i<planesElegidos.size()){
			if(planesElegidos.get(i).getId().equalsIgnoreCase(aux.getId())) {
				index = i;
				encontrado = true;
			}
			i++;
		}
		return index;
	}
	private void clean() {
		txtCedulaConfirm.setText("");
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
	}
	
}
