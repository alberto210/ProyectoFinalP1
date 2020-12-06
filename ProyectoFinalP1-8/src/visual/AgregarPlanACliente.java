package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Altice;
import logico.Cliente;

import logico.Plan;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AgregarPlanACliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelPricipal ;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtBuscarC;
	private JTextField txtEmail;
	private JScrollPane scrollPanelPlanes ;
	private JScrollPane scrollPanelDeCompra ;
	ArrayList<Plan> misPlanes = new ArrayList<>();
	private JButton btnAgregar ;
	private JButton btnRegresar ;
	private JButton btnBuscar ;
	private  JButton AgregarPlan ;
	private JComboBox cbxTipo ;
	public static DefaultTableModel modelo;
	public static Object[] rows;
	private JTable tablaPlanes;
	private JTable tablePlanesElegidos;
	private JList listaDePlanes;
	private JList listaDePlanesElegidos;
	private DefaultListModel modelPlan;
	private DefaultListModel modelAgregar;
	private int seleccionarTipo=0;

	public AgregarPlanACliente() {
		setBounds(100, 100, 902, 579);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			 panelPricipal = new JPanel();
			panelPricipal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panelPricipal, BorderLayout.CENTER);
			panelPricipal.setLayout(null);
			{
				JPanel panelDatos = new JPanel();
				panelDatos.setLayout(null);
				panelDatos.setBorder(new TitledBorder(null, "Datos Del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelDatos.setBounds(10, 11, 558, 140);
				panelPricipal.add(panelDatos);
				{
					JLabel label = new JLabel("C\u00E9dula:");
					label.setBounds(10, 59, 54, 14);
					panelDatos.add(label);
				}
				{
					JLabel label = new JLabel("Nombre:");
					label.setBounds(10, 28, 54, 14);
					panelDatos.add(label);
				}
				{
					txtCedula = new JTextField();
					txtCedula.setEditable(false);
					txtCedula.setColumns(10);
					txtCedula.setBounds(66, 55, 174, 23);
					panelDatos.add(txtCedula);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setEditable(false);
					txtNombre.setColumns(10);
					txtNombre.setBounds(66, 24, 174, 23);
					panelDatos.add(txtNombre);
				}
				{
					JLabel label = new JLabel("Direcci\u00F3n:");
					label.setBounds(269, 28, 67, 14);
					panelDatos.add(label);
				}
				{
					JLabel label = new JLabel("Tel\u00E9fono:");
					label.setBounds(269, 59, 67, 14);
					panelDatos.add(label);
				}
				{
					txtDireccion = new JTextField();
					txtDireccion.setEditable(false);
					txtDireccion.setColumns(10);
					txtDireccion.setBounds(339, 24, 174, 23);
					panelDatos.add(txtDireccion);
				}
				{
					txtTelefono = new JTextField();
					txtTelefono.setEditable(false);
					txtTelefono.setColumns(10);
					txtTelefono.setBounds(339, 55, 174, 23);
					panelDatos.add(txtTelefono);
				}
				{
					JLabel lblEmail = new JLabel("Email:");
					lblEmail.setBounds(10, 93, 54, 14);
					panelDatos.add(lblEmail);
				}
				{
					txtEmail = new JTextField();
					txtEmail.setEditable(false);
					txtEmail.setColumns(10);
					txtEmail.setBounds(66, 89, 382, 23);
					panelDatos.add(txtEmail);
				}
			}
			{
				JPanel panelBuscar = new JPanel();
				panelBuscar.setLayout(null);
				panelBuscar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar o Crear Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panelBuscar.setBounds(578, 11, 266, 106);
				panelPricipal.add(panelBuscar);
				{
					JLabel label = new JLabel("Cedula:");
					label.setBounds(19, 42, 54, 14);
					panelBuscar.add(label);
				}
				{
					txtBuscarC = new JTextField();
					txtBuscarC.setColumns(10);
					txtBuscarC.setBounds(70, 38, 174, 23);
					panelBuscar.add(txtBuscarC);
				}
				{
					btnBuscar = new JButton("Buscar");
					btnBuscar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							
							Cliente cliente = Altice.getInstance().buscarCliente(txtBuscarC.getText());
							
							if(cliente != null){
								JOptionPane.showMessageDialog(null, "Cliente encontrado", null, JOptionPane.INFORMATION_MESSAGE, null);
								txtCedula.setText(String.valueOf(cliente.getCedula()));
								txtNombre.setText(String.valueOf(cliente.getNombre()));
								txtDireccion.setText(String.valueOf(cliente.getDireccion()));
								txtTelefono.setText(String.valueOf(cliente.getTelefono()));
								txtEmail.setText(String.valueOf(cliente.getTelefono()));
							
							}else{
								int verificar = JOptionPane.showConfirmDialog(null, "Cliente no registrado. ¿Desea Registrarse?", null, JOptionPane.YES_NO_OPTION);
								if (verificar == JOptionPane.YES_OPTION)
								{
								txtCedula.setEditable(true);
								txtNombre.setEditable(true);
								txtDireccion.setEditable(true);
								txtTelefono.setEditable(true);
								txtEmail.setEditable(true);
								}
						}}
					});
					btnBuscar.setActionCommand("OK");
					btnBuscar.setBounds(163, 72, 81, 23);
					panelBuscar.add(btnBuscar);
				}
			}
			{
				JPanel panel = new JPanel();
				panel.setLayout(null);
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Planes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(10, 156, 834, 330);
				panelPricipal.add(panel);
				{
					cbxTipo = new JComboBox();
					cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione un plan>", "SinglePlay", "DoublePlay", "TripePlay"}));
					cbxTipo.setBounds(109, 21, 114, 20);
					panel.add(cbxTipo);
				}
				{
					JLabel lblTipoDePlan = new JLabel("Tipo de Plan:");
					lblTipoDePlan.setBounds(10, 24, 89, 14);
					panel.add(lblTipoDePlan);
				}
				{
					JLabel lblPlanesElegidos = new JLabel("Planes Elegidos:");
					lblPlanesElegidos.setBounds(468, 27, 114, 14);
					panel.add(lblPlanesElegidos);
				}
				{
					btnAgregar = new JButton("Agregar");
					btnAgregar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(comprar().getCantCanales()!=0|| comprar().getCantMegas()!=0||comprar().getCantMinutos()!=0 ){	
								misPlanes.add(comprar());
							
								modelPlan.clear();
								modelAgregar.clear();
								//clear();
								llenarListaPlanes(seleccionarTipo);
								llenarListaCompra(txtCedula.getText());
								btnAgregar.setEnabled(false);
							}else{
								btnAgregar.setEnabled(false);				
								JOptionPane.showMessageDialog(null, "Plan no registrado", null, JOptionPane.ERROR_MESSAGE, null);
								
							}
							
						}

						

					});
					btnAgregar.setEnabled(false);
					btnAgregar.setActionCommand("OK");
					btnAgregar.setBounds(355, 93, 88, 23);
					panel.add(btnAgregar);
				}
				{
					scrollPanelPlanes = new JScrollPane();
					scrollPanelPlanes.setBounds(10, 52, 332, 252);
					panel.add(scrollPanelPlanes);
					{
						listaDePlanes = new JList();
						listaDePlanes.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								
								
								if(listaDePlanes.getSelectedIndex()!=-1){
									btnAgregar.setEnabled(true);
									
								}
								
								
								
								
							}
						});
						scrollPanelPlanes.setViewportView(listaDePlanes);
					}
					{
						
						
						
						
						listaDePlanes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
						modelPlan = new DefaultListModel();
						scrollPanelPlanes = new JScrollPane();
						scrollPanelPlanes.setBounds(10, 52, 332, 252);
						panel.add(scrollPanelPlanes);
						//scrollPanelPlanes.setViewportView(listaDePlanes);
						llenarListaPlanes(0);
						
						
						
						
						
						
						
						
						
					}
				}
				{
					scrollPanelDeCompra = new JScrollPane();
					scrollPanelDeCompra.setBounds(468, 52, 332, 252);
					panel.add(scrollPanelDeCompra);
					{
						listaDePlanesElegidos = new JList();
						listaDePlanesElegidos.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if(listaDePlanesElegidos.getSelectedIndex()!=-1){
									btnRegresar.setEnabled(true);
									
									}
								
								
							}
						});
						
						listaDePlanesElegidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
						modelAgregar = new DefaultListModel();
						scrollPanelDeCompra.setBounds(468, 52, 332, 252);
						panel.add(scrollPanelDeCompra);
						scrollPanelDeCompra.setViewportView(listaDePlanesElegidos);
						
						
						scrollPanelDeCompra.setViewportView(listaDePlanesElegidos);
					}
					
					
					
					
				}
				
				btnRegresar = new JButton("Regresar");
				btnRegresar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int aux = listaDePlanesElegidos.getSelectedIndex();
						Plan planes = misPlanes.get(aux);
						
						misPlanes.remove(aux);
						modelPlan.clear();
						modelAgregar.clear();
						llenarListaPlanes(seleccionarTipo);
						llenarListaCompra(txtCedula.getText());
						btnRegresar.setEnabled(false);
						
						
						
					}
				});
				btnRegresar.setBounds(355, 263, 88, 23);
				panel.add(btnRegresar);
				btnRegresar.setEnabled(false);
				btnRegresar.setActionCommand("OK");
				
				
				
				
				
				
				
				
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				AgregarPlan = new JButton("Agregar Plan");
				AgregarPlan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						Cliente cliente = Altice.getInstance().buscarCliente(txtCedula.getText());
					
						String cedula1 = txtCedula.getText();
						String nombre = txtNombre.getText();
						String direccion = txtDireccion.getText();
						String telefono = txtTelefono.getText();
						String email = txtEmail.getText();
						
						
						cliente = new Cliente(nombre, cedula1, direccion, telefono, email);
						Altice.getInstance().registrarCliente(cliente);
						
						
						//Altice.getInstance().agregarPlanACliente(idPlan, cedula, idEmpleado);
						
			
						JOptionPane.showMessageDialog(null, "Plan Asignado", null, JOptionPane.ERROR_MESSAGE, null);
						
						
						
						
					}
				});
				AgregarPlan.setActionCommand("OK");
				buttonPane.add(AgregarPlan);
				getRootPane().setDefaultButton(AgregarPlan);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void llenarListaPlanes(int selec) {
		

		switch (selec) {
		case 0:
			String aux = null;
			modelPlan.clear();
			for (Plan misPlanes : Altice.getInstance().getMisPlanes()) {
				
				modelPlan.addElement("Id " + misPlanes.getId() +" -- Cantidad de Megas Disponible: "  + misPlanes.getCantMegas() + " -- Cantidad de Minutos Disponible: "  + misPlanes.getCantMinutos()+ " -- Cantidad de Canales Disponible: "  + misPlanes.getCantCanales()     );
				listaDePlanes.setModel(modelPlan);
				
			}
			
			
		
		
		}
			
	}
	
	private void llenarListaCompra(String cedula) {

		
			for (Plan misPlanes : Altice.getInstance().getMisPlanes()) {
				
				modelAgregar.addElement("Id " + misPlanes.getId() + "Nombre del plan: " + misPlanes.getNombre() + " -- Cantidad de Megas Disponible: "  + misPlanes.getCantMegas() + " -- Cantidad de Minutos Disponible: "  + misPlanes.getCantMinutos()+ " -- Cantidad de Canales Disponible: "  + misPlanes.getCantCanales()     );
				listaDePlanesElegidos.setModel(modelAgregar);

			}
	}
	public Plan comprar() {
		Plan misPlanes = null;
		int temp;
		ArrayList<Plan> aux =new ArrayList<>();

		if(cbxTipo.getSelectedIndex()==0){
			temp=listaDePlanes.getSelectedIndex();
			misPlanes=Altice.getInstance().getMisPlanes().get(temp);
		}
		return misPlanes;}
	
}
