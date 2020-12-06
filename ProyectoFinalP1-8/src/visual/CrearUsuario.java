package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Administrativo;
import logico.Altice;
import logico.Comercial;
import logico.Empleado;
import logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CrearUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuarioN;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordConfirm;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JLabel lblCargo;
	private JComboBox cbxCargo;
	private JRadioButton rdbAdministrador;
	private JRadioButton rdbComercial;
	private JSpinner spnSueldoBase;
	private JSpinner spnSueldoUnitario;
	private JButton btnCrear;

	public CrearUsuario(String title, int mode, Empleado aux) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Logo.jpg"));
		setModal(true);
		setTitle(title);
		setBounds(100, 100, 361, 602);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Datos de la Cuenta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
		lblNombreUsuario.setBounds(20, 23, 127, 20);
		contentPanel.add(lblNombreUsuario);
		
		txtUsuarioN = new JTextField();
		if(mode == 1) {
			txtUsuarioN.setText(aux.getUser().getNombreDeUsuario());
			txtUsuarioN.setEditable(false);
		}
		txtUsuarioN.setBounds(20, 49, 127, 23);
		contentPanel.add(txtUsuarioN);
		txtUsuarioN.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(20, 138, 97, 14);
		contentPanel.add(lblTipo);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(20, 79, 127, 14);
		contentPanel.add(lblPassword);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar Contrase\u00F1a:");
		lblConfirmarPassword.setBounds(188, 79, 167, 14);
		contentPanel.add(lblConfirmarPassword);
		
		txtPassword = new JPasswordField();
		if(mode == 1) {
			txtPassword.setText(aux.getUser().getContrasena());
			txtPassword.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if(!txtPassword.getText().equals(aux.getUser().getContrasena())) {
						txtPasswordConfirm.setEditable(true);
					}
				}
			});
		}
		txtPassword.setBounds(20, 104, 127, 23);
		contentPanel.add(txtPassword);
		
		txtPasswordConfirm = new JPasswordField();
		if(mode == 1) {
			txtPasswordConfirm.setEditable(false);
			txtPasswordConfirm.setText("");
			
		}
		txtPasswordConfirm.setBounds(188, 104, 127, 23);
		contentPanel.add(txtPasswordConfirm);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 200, 321, 14);
		contentPanel.add(separator);
		
		txtCedula = new JTextField();
		if(mode == 1) {
			txtCedula.setText(aux.getCedula());
			txtCedula.setEditable(false);
		}
		txtCedula.setBounds(20, 275, 240, 23);
		contentPanel.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblCedula = new JLabel("C\u00E9dula: ");
		lblCedula.setBounds(20, 250, 86, 14);
		contentPanel.add(lblCedula);
		
		JLabel lbNombre = new JLabel("Nombre Completo: ");
		lbNombre.setBounds(20, 309, 167, 14);
		contentPanel.add(lbNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(20, 334, 240, 23);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		spnSueldoBase = new JSpinner();
		spnSueldoBase.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnSueldoBase.setBounds(20, 393, 127, 23);
		contentPanel.add(spnSueldoBase);
		
		spnSueldoUnitario = new JSpinner();
		spnSueldoUnitario.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnSueldoUnitario.setBounds(189, 393, 127, 23);
		contentPanel.add(spnSueldoUnitario);
		
		JLabel lblNewLabel = new JLabel("Sueldo Base");
		lblNewLabel.setBounds(20, 368, 97, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sueldo Unitario");
		lblNewLabel_1.setBounds(189, 368, 86, 14);
		contentPanel.add(lblNewLabel_1);
		cbxCargo = new JComboBox();
		if(mode == 1) {
			if(aux instanceof Comercial) {
				cbxCargo.setVisible(false);
			}
			else if(aux instanceof Administrativo) {
				cbxCargo.setVisible(true);
				cbxCargo.setModel(new DefaultComboBoxModel(new String[] {((Administrativo) aux).getCargo()}));
			}
		}
		if(mode == 0){
			cbxCargo.setVisible(false);
			cbxCargo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
			if(Altice.getInstance().admin) {
				
				cbxCargo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Gerente", "Representante Local","Representante Regional","Mesa Administrativa"}));
				
			}else if(((Administrativo) Altice.getLoginEmpleado()).getCargo().equalsIgnoreCase("Gerente")) {
				
				cbxCargo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Gerente"}));
				
			}else if(((Administrativo) Altice.getLoginEmpleado()).getCargo().equalsIgnoreCase("Representante Local")) {
				
				cbxCargo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Gerente", "Representante Local"}));
				
			}else if(((Administrativo) Altice.getLoginEmpleado()).getCargo().equalsIgnoreCase("Representante Regional")) {
				
				cbxCargo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Gerente", "Representante Local","Representante Regional"}));
				
			}else{
				
				cbxCargo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Gerente", "Representante Local","Representante Regional","Mesa Administrativa"}));
			}
		}
		cbxCargo.setBounds(20, 452, 240, 23);
		contentPanel.add(cbxCargo);
		
		lblCargo = new JLabel("Cargo");
		if(mode == 1) {
			if(aux instanceof Comercial) {
				lblCargo.setVisible(false);
			}
			else if(aux instanceof Administrativo) {
				lblCargo.setVisible(true);
			}
		}
		lblCargo.setBounds(20, 427, 127, 14);
		contentPanel.add(lblCargo);

		JLabel lblNewLabel_2 = new JLabel("Datos del Empleado");
		lblNewLabel_2.setBounds(10, 225, 161, 14);
		contentPanel.add(lblNewLabel_2);
		
		rdbComercial = new JRadioButton("Comercial");
		if(mode == 1) {
			rdbComercial.setEnabled(false);
			rdbComercial.setSelected(false);
		}
		rdbComercial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbAdministrador.setSelected(false);
				lblCargo.setVisible(false);
				cbxCargo.setVisible(false);
			}
		});
		rdbComercial.setSelected(true);
		rdbComercial.setBounds(20, 159, 109, 23);
		contentPanel.add(rdbComercial);
		
		rdbAdministrador = new JRadioButton("Administrador");
		if(mode == 1) {
			rdbAdministrador.setEnabled(false);
			rdbAdministrador.setSelected(false);
		}
		rdbAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbComercial.setSelected(false);
				lblCargo.setVisible(true);
				cbxCargo.setVisible(true);
			}
		});
		rdbAdministrador.setBounds(189, 159, 109, 23);
		contentPanel.add(rdbAdministrador);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCrear = new JButton("");
				if(mode == 0) {
					btnCrear.setText("Crear");
				}
				else if(mode == 1) {
					btnCrear.setText("Modificar");
				}
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(mode == 0) {
							String nombre = txtUsuarioN.getText();
							String password = txtPassword.getText();
							String verpass = txtPasswordConfirm.getText();
							boolean verificarNombreDuplicado = false;
							Empleado emp = null;
							Usuario user = null;
							for(Empleado temp: Altice.getInstance().getMisEmpleados()) {
								if(temp.getUser().getNombreDeUsuario().equals(nombre)) {
									verificarNombreDuplicado = true;
								}
							}
							
							if(!verificarNombreDuplicado && !nombre.equalsIgnoreCase("Altice")) {
							
								if(nombre.equalsIgnoreCase("") || password.equalsIgnoreCase("") || verpass.equalsIgnoreCase("")|| txtCedula.getText().equalsIgnoreCase("") || txtNombre.getText().equalsIgnoreCase("") || Float.parseFloat(spnSueldoBase.getValue().toString()) == 0 || Float.parseFloat(spnSueldoUnitario.getValue().toString()) == 0 || (!rdbAdministrador.isSelected() && !rdbComercial.isSelected())) {
									JOptionPane.showMessageDialog(null, "Campos vacíos. Por favor, llene todos los campos", "Información", JOptionPane.WARNING_MESSAGE);
								
								}else{
									if(password.equals(verpass)) {
										if(rdbAdministrador.isSelected()){
											if(!cbxCargo.getSelectedItem().toString().equalsIgnoreCase("<Seleccione>")) {
												emp = new Administrativo(txtCedula.getText(),txtNombre.getText(), Float.parseFloat(spnSueldoBase.getValue().toString()), Float.parseFloat(spnSueldoUnitario.getValue().toString()), 0, cbxCargo.getSelectedItem().toString());
												user = new Usuario("Administrador",nombre,password);
											}
										}
										if(rdbComercial.isSelected()) {
											emp = new Comercial(txtCedula.getText(),txtNombre.getText(), Float.parseFloat(spnSueldoBase.getValue().toString()), Float.parseFloat(spnSueldoUnitario.getValue().toString()), 0,0);
											user = new Usuario("Comercial",nombre,password);
										}
										if(rdbAdministrador.isSelected() && cbxCargo.getSelectedItem().toString().equalsIgnoreCase("<Seleccione>")) {
											JOptionPane.showMessageDialog(null, "Seleccione un cargo para el personal administrativo.", "Información", JOptionPane.WARNING_MESSAGE);
										}else {
											emp.setUser(user);
											Altice.getInstance().getMisEmpleados().add(emp);
											JOptionPane.showMessageDialog(null, "Usuario creado correctamente", null, JOptionPane.INFORMATION_MESSAGE, null);
											clear();
										}
									}else {
										JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden, reingrese las contraseñas.", "Información", JOptionPane.WARNING_MESSAGE);
									}
										
								}
							}else {
								if(!nombre.equalsIgnoreCase("Altice")) {
									JOptionPane.showMessageDialog(null, "Este Usuario es no valido, ingrese uno nuevo", "Información", JOptionPane.WARNING_MESSAGE);
								}else {
									JOptionPane.showMessageDialog(null, "Este nombre de usuario ya existe. Ingrese uno diferente.", "Información", JOptionPane.WARNING_MESSAGE);
								}
							}
						}
						if(mode == 1) {
							String nombre = txtNombre.getText();
							float sueldoBase = new Float(spnSueldoBase.getValue().toString());
							float sueldoUni = new Float(spnSueldoUnitario.getValue().toString());
							String password = txtPassword.getText();
							if(aux != null) {
								if(nombre.equalsIgnoreCase("") || sueldoBase == 0 || sueldoUni == 0) {
									JOptionPane.showMessageDialog(null, "Campos vacíos. Por favor, llene todos los campos", "Información", JOptionPane.WARNING_MESSAGE);
								}
								else {
									int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea modificar al cliente "+ aux.getNombre(), "Confirmación", JOptionPane.OK_CANCEL_OPTION);
									if(option == JOptionPane.OK_OPTION) {
										aux.setNombre(nombre);;
										aux.setSueldoBase(sueldoBase);
										aux.setSueldoUnitario(sueldoUni);
										if(!password.equals(aux.getUser().getContrasena())) {
											String verPassword = txtPasswordConfirm.getText();
											if(password.equals("") || verPassword.equals("")) {
												JOptionPane.showMessageDialog(null, "Campos vacíos. Por favor, llene todos los campos", "Información", JOptionPane.WARNING_MESSAGE);
											}
											else {
												if(!password.equals(verPassword)) {
													JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden, reingrese las contraseñas.", "Información", JOptionPane.WARNING_MESSAGE);
												}
												else {
													aux.getUser().setContrasena(password);
												}
											}
										}
										JOptionPane.showMessageDialog(null, "Usuario creado correctamente", null, JOptionPane.INFORMATION_MESSAGE, null);
									}
								}
							}
							ListEmpleados.llenartabla();
							clear();
							dispose();
						}
						
					}
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
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
	}
	
	private void clear() {
		// TODO Auto-generated method stub
		txtUsuarioN.setText("");
		txtPassword.setText("");
		txtPasswordConfirm.setText("");
		txtCedula.setText("");
		txtNombre.setText("");
		spnSueldoBase.setValue(Integer.valueOf("0"));
		
		spnSueldoUnitario.setValue(Integer.valueOf("0"));
	}
}
