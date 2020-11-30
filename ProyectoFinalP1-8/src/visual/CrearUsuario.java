package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Altice;
import logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CrearUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuarioN;
	private JComboBox cmbTipo;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordConfirm;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			CrearUsuario dialog = new CrearUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public CrearUsuario() {
		setTitle("Nuevo Usuario");
		setBounds(100, 100, 407, 240);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
		lblNombreUsuario.setBounds(20, 23, 127, 20);
		contentPanel.add(lblNombreUsuario);
		
		txtUsuarioN = new JTextField();
		txtUsuarioN.setBounds(20, 49, 127, 20);
		contentPanel.add(txtUsuarioN);
		txtUsuarioN.setColumns(10);
		
		cmbTipo = new JComboBox();
		cmbTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Administrador", "Comercial"}));
		cmbTipo.setBounds(20, 113, 127, 20);
		contentPanel.add(cmbTipo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(20, 88, 97, 14);
		contentPanel.add(lblTipo);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(189, 26, 97, 14);
		contentPanel.add(lblPassword);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar Contrase\u00F1a:");
		lblConfirmarPassword.setBounds(189, 88, 167, 14);
		contentPanel.add(lblConfirmarPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(189, 49, 127, 20);
		contentPanel.add(txtPassword);
		
		txtPasswordConfirm = new JPasswordField();
		txtPasswordConfirm.setBounds(189, 113, 127, 20);
		contentPanel.add(txtPasswordConfirm);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String tipo = cmbTipo.getSelectedItem().toString();
						String nombre = txtUsuarioN.getText();
						String password = txtPassword.getText();
						String verpass = txtPasswordConfirm.getText();
						Usuario user = null;
						if(nombre.equalsIgnoreCase("") || password.equalsIgnoreCase("") || verpass.equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Campos vacíos. Porfavor llene todos los campos", "Información", JOptionPane.WARNING_MESSAGE);
							
						}else if(tipo=="<Seleccione>") {
							JOptionPane.showMessageDialog(null, "Porfavor, seleccione el tipo de usuario", "Información", JOptionPane.WARNING_MESSAGE);
							
						}else if(!verpass.equals(password)) {
							JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden. Porfavor, ingrese de nuevo", null, JOptionPane.ERROR_MESSAGE, null);
							
						}
						else {
							user = new Usuario(tipo,nombre,password);
							Altice.getInstance().regUser(user);
							JOptionPane.showMessageDialog(null, "Usuario creado correctamente", null, JOptionPane.INFORMATION_MESSAGE, null);
							clear();
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
		cmbTipo.setSelectedIndex(0);
		txtUsuarioN.setText("");
		txtPassword.setText("");
		txtPasswordConfirm.setText("");
	}
	
}
