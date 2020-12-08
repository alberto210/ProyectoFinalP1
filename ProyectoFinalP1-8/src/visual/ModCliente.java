package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cliente;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JLabel lblValid;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ModCliente dialog = new ModCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ModCliente(String title, int mode, Cliente aux) {
		setResizable(false);
		setModal(true);
		setTitle(title);
		setBounds(100, 100, 576, 228);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Modificar Informaci\u00F3n del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("C\u00E9dula:");
			lblNewLabel.setBounds(10, 29, 46, 14);
			panel.add(lblNewLabel);
			
			txtCedula = new JTextField();
			if(mode == 1) {
				txtCedula.setText(aux.getCedula());
			}
			txtCedula.setEnabled(false);
			txtCedula.setBounds(66, 25, 190, 23);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			txtNombre = new JTextField();
			if(mode == 1) {
				txtNombre.setText(aux.getNombre());
			}
			txtNombre.setBounds(66, 59, 190, 23);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtDireccion = new JTextField();
			if(mode == 1) {
				txtDireccion.setText(aux.getDireccion());
			}
			txtDireccion.setBounds(349, 25, 190, 23);
			panel.add(txtDireccion);
			txtDireccion.setColumns(10);
			
			txtTelefono = new JTextField();
			if(mode == 1) {
				txtTelefono.setText(aux.getTelefono());
			}
			txtTelefono.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					char saldo = e.getKeyChar();
					if(Character.isLetter(saldo)) {
						txtTelefono.setEditable(false);
						lblValid.setText("No válido");
					}else {
						txtTelefono.setEditable(true);
						lblValid.setText("");
					}
				}
			});
			txtTelefono.setBounds(349, 59, 190, 23);
			panel.add(txtTelefono);
			txtTelefono.setColumns(10);
			
			txtEmail = new JTextField();
			if(mode == 1) {
				txtEmail.setText(aux.getEmail());
			}
			txtEmail.setBounds(66, 107, 473, 23);
			panel.add(txtEmail);
			txtEmail.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(10, 63, 53, 14);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Direcci\u00F3n: ");
			lblNewLabel_2.setBounds(281, 29, 74, 14);
			panel.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Tel\u00E9fono: ");
			lblNewLabel_3.setBounds(281, 63, 59, 14);
			panel.add(lblNewLabel_3);
			
			lblValid = new JLabel("");
			lblValid.setForeground(Color.RED);
			lblValid.setBounds(349, 82, 190, 14);
			panel.add(lblValid);
			
			JLabel lblNewLabel_5 = new JLabel("Email: ");
			lblNewLabel_5.setBounds(10, 111, 46, 14);
			panel.add(lblNewLabel_5);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Modificar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(mode == 1) {
							if(aux != null) {
								int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea modificar la información del cliente "+ aux.getNombre()+"?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
								if(option == JOptionPane.OK_OPTION) {
									String nombre = txtNombre.getText();
									String direccion = txtDireccion.getText();
									String telefono = txtTelefono.getText();
									String email = txtEmail.getText();
									aux.setNombre(nombre);
									aux.setDireccion(direccion);
									aux.setTelefono(telefono);
									aux.setEmail(email);
									JOptionPane.showMessageDialog(null, "Se ha modificado la información del cliente.", null, JOptionPane.INFORMATION_MESSAGE, null);
								}
								
							}
							ListClientes.llenarTabla();
							clean();
							dispose();
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
	
	private void clean() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtEmail.setText("");
	}
	
}
