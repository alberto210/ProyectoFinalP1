package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

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

	public CrearUsuario() {
		setTitle("Nuevo Usuario");
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
		txtUsuarioN.setBounds(20, 49, 127, 23);
		contentPanel.add(txtUsuarioN);
		txtUsuarioN.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(20, 138, 97, 14);
		contentPanel.add(lblTipo);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(20, 79, 97, 14);
		contentPanel.add(lblPassword);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar Contrase\u00F1a:");
		lblConfirmarPassword.setBounds(188, 79, 167, 14);
		contentPanel.add(lblConfirmarPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(20, 104, 127, 23);
		contentPanel.add(txtPassword);
		
		txtPasswordConfirm = new JPasswordField();
		txtPasswordConfirm.setBounds(188, 104, 127, 23);
		contentPanel.add(txtPasswordConfirm);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 200, 311, 14);
		contentPanel.add(separator);
		
		txtCedula = new JTextField();
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
		cbxCargo.setBounds(20, 452, 240, 23);
		contentPanel.add(cbxCargo);
		
		lblCargo = new JLabel("Cargo");
		lblCargo.setVisible(false);
		lblCargo.setBounds(20, 427, 127, 14);
		contentPanel.add(lblCargo);

		JLabel lblNewLabel_2 = new JLabel("Datos del Empleado");
		lblNewLabel_2.setBounds(10, 225, 161, 14);
		contentPanel.add(lblNewLabel_2);
		
		rdbComercial = new JRadioButton("Comercial");
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
				JButton okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
							}
						}else {
							if(!nombre.equalsIgnoreCase("Altice")) {
								JOptionPane.showMessageDialog(null, "Este Usuario es no valido, ingrese uno nuevo", "Información", JOptionPane.WARNING_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null, "Este nombre de usuario ya existe. Ingrese uno diferente.", "Información", JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
