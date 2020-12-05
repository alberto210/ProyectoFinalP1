package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;

import logico.Altice;

import logico.Plan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtID;
	private JRadioButton rdbtnNinguno ;
private JRadioButton rdbtnInternet ;
private JRadioButton rdbtnTelefono ;
private JRadioButton rdbtnCable ;
private JRadioButton rdbtnInternetTelefono;
private JRadioButton rdbtnInternetCable;
private JRadioButton rdbtnTelefonoCable ;
private JRadioButton rdbtnTelefonoCableInternet ;
private JPanel panelDeServicios;
private JPanel panelInicial ;
private JPanel panelInternet;
private JSpinner spnMegas ;
private JPanel panelTelefono ;
private JSpinner spnCantidadMinutos ;
private JPanel panelCable ;
private JSpinner spnCantidadDeCanales;
private JPanel panelNinguno ;
/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearPlan dialog = new CrearPlan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearPlan() {
		setBounds(100, 100, 786, 498);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			 panelInicial = new JPanel();
			panelInicial.setBorder(new TitledBorder(null, "Informaci\u00F3n para creaci\u00F3n del plan:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panelInicial, BorderLayout.CENTER);
			panelInicial.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setBounds(397, 33, 100, 19);
			panelInicial.add(lblNewLabel);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(519, 32, 180, 20);
			panelInicial.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("ID:");
			lblNewLabel_1.setBounds(20, 35, 46, 14);
			panelInicial.add(lblNewLabel_1);
			
			txtID = new JTextField();
			txtID.setColumns(10);
			txtID.setBounds(76, 32, 180, 20);
			panelInicial.add(txtID);
			
			 panelDeServicios = new JPanel();
			panelDeServicios.setBorder(new TitledBorder(null, "Elija el servico que desea", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDeServicios.setBounds(10, 66, 740, 196);
			panelInicial.add(panelDeServicios);
			panelDeServicios.setLayout(null);
			
			rdbtnInternet = new JRadioButton("Internet");
			rdbtnInternet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnNinguno.setSelected(false);
					rdbtnInternet.setSelected(true);
					rdbtnCable.setSelected(false);
					rdbtnTelefono.setSelected(false);
					rdbtnInternetCable.setSelected(false);
					rdbtnInternetTelefono.setSelected(false);
					rdbtnTelefonoCable.setSelected(false);
					rdbtnTelefonoCableInternet.setSelected(false);
					
					panelNinguno.setVisible(false);	
					panelInternet.setVisible(true);
					panelCable.setVisible(false);
					panelTelefono.setVisible(false);
					
					
					spnCantidadMinutos.setVisible(false);
					spnMegas.setVisible(true);
					spnCantidadDeCanales.setVisible(false);
					
					
					
					
				}
			});
			rdbtnInternet.setBounds(182, 58, 109, 23);
			panelDeServicios.add(rdbtnInternet);
			
			 rdbtnTelefono = new JRadioButton("Tel\u00E9fono");
			 rdbtnTelefono.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		rdbtnNinguno.setSelected(false);
					rdbtnInternet.setSelected(false);
					rdbtnCable.setSelected(false);
					rdbtnTelefono.setSelected(true);
					rdbtnInternetCable.setSelected(false);
					rdbtnInternetTelefono.setSelected(false);
					rdbtnTelefonoCable.setSelected(false);
					rdbtnTelefonoCableInternet.setSelected(false);
					
					panelNinguno.setVisible(false);	
					panelInternet.setVisible(false);
					panelCable.setVisible(false);
					panelTelefono.setVisible(true);
					
					
					spnCantidadMinutos.setVisible(true);
					spnMegas.setVisible(false);
					spnCantidadDeCanales.setVisible(false);
			 		
			 		
			 	}
			 });
			rdbtnTelefono.setBounds(349, 58, 109, 23);
			panelDeServicios.add(rdbtnTelefono);
			
			 rdbtnCable = new JRadioButton("Cable");
			 rdbtnCable.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		
			 		rdbtnNinguno.setSelected(false);
					rdbtnInternet.setSelected(false);
					rdbtnCable.setSelected(true);
					rdbtnTelefono.setSelected(false);
					rdbtnInternetCable.setSelected(false);
					rdbtnInternetTelefono.setSelected(false);
					rdbtnTelefonoCable.setSelected(false);
					rdbtnTelefonoCableInternet.setSelected(false);
					
					panelNinguno.setVisible(false);	
					panelInternet.setVisible(false);
					panelCable.setVisible(true);
					panelTelefono.setVisible(false);
					
					
					spnCantidadMinutos.setVisible(false);
					spnMegas.setVisible(false);
					spnCantidadDeCanales.setVisible(true);	
			 		
			 		
			 		
			 		
			 		
			 	}
			 });
			rdbtnCable.setBounds(503, 58, 109, 23);
			panelDeServicios.add(rdbtnCable);
			
			JLabel lblNewLabel_2 = new JLabel("Simple:");
			lblNewLabel_2.setBounds(10, 28, 105, 23);
			panelDeServicios.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Combo:");
			lblNewLabel_3.setBounds(10, 96, 46, 14);
			panelDeServicios.add(lblNewLabel_3);
			
			 rdbtnInternetTelefono = new JRadioButton("Internet & Tel\u00E9fono");
			 rdbtnInternetTelefono.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		
			 		rdbtnNinguno.setSelected(false);
					rdbtnInternet.setSelected(false);
					rdbtnCable.setSelected(false);
					rdbtnTelefono.setSelected(false);
					rdbtnInternetCable.setSelected(false);
					rdbtnInternetTelefono.setSelected(true);
					rdbtnTelefonoCable.setSelected(false);
					rdbtnTelefonoCableInternet.setSelected(false);
					
					panelNinguno.setVisible(false);	
					panelInternet.setVisible(true);
					panelCable.setVisible(false);
					panelTelefono.setVisible(true);
					
					
					spnCantidadMinutos.setVisible(true);
					spnMegas.setVisible(true);
					spnCantidadDeCanales.setVisible(false);
			 		
			 		
			 		
			 	}
			 });
			rdbtnInternetTelefono.setBounds(6, 138, 140, 23);
			panelDeServicios.add(rdbtnInternetTelefono);
			
			 rdbtnInternetCable = new JRadioButton("Internet & Cable");
			 rdbtnInternetCable.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		
			 		rdbtnNinguno.setSelected(false);
					rdbtnInternet.setSelected(false);
					rdbtnCable.setSelected(false);
					rdbtnTelefono.setSelected(false);
					rdbtnInternetCable.setSelected(true);
					rdbtnInternetTelefono.setSelected(false);
					rdbtnTelefonoCable.setSelected(false);
					rdbtnTelefonoCableInternet.setSelected(false);
					
					panelNinguno.setVisible(false);	
					panelInternet.setVisible(true);
					panelCable.setVisible(true);
					panelTelefono.setVisible(false);
					
					
					spnCantidadMinutos.setVisible(false);
					spnMegas.setVisible(true);
					spnCantidadDeCanales.setVisible(true);
			 		
			 		
			 		
			 		
			 	}
			 });
			rdbtnInternetCable.setBounds(182, 138, 134, 23);
			panelDeServicios.add(rdbtnInternetCable);
			
			 rdbtnTelefonoCable = new JRadioButton("Tel\u00E9fono & Cable");
			 rdbtnTelefonoCable.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		
			 		rdbtnNinguno.setSelected(false);
					rdbtnInternet.setSelected(false);
					rdbtnCable.setSelected(false);
					rdbtnTelefono.setSelected(false);
					rdbtnInternetCable.setSelected(false);
					rdbtnInternetTelefono.setSelected(false);
					rdbtnTelefonoCable.setSelected(true);
					rdbtnTelefonoCableInternet.setSelected(false);
					
					panelNinguno.setVisible(false);	
					panelInternet.setVisible(false);
					panelCable.setVisible(true);
					panelTelefono.setVisible(true);
					
					
					spnCantidadMinutos.setVisible(true);
					spnMegas.setVisible(false);
					spnCantidadDeCanales.setVisible(true);
			 		
			 		
			 		
			 		
			 	}
			 });
			rdbtnTelefonoCable.setBounds(349, 138, 152, 23);
			panelDeServicios.add(rdbtnTelefonoCable);
			
			 rdbtnTelefonoCableInternet = new JRadioButton("Tel\u00E9fono, Cable & Internet");
			 rdbtnTelefonoCableInternet.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		rdbtnNinguno.setSelected(false);
					rdbtnInternet.setSelected(false);
					rdbtnCable.setSelected(false);
					rdbtnTelefono.setSelected(false);
					rdbtnInternetCable.setSelected(false);
					rdbtnInternetTelefono.setSelected(false);
					rdbtnTelefonoCable.setSelected(false);
					rdbtnTelefonoCableInternet.setSelected(true);
					
					panelNinguno.setVisible(false);	
					panelInternet.setVisible(true);
					panelCable.setVisible(true);
					panelTelefono.setVisible(true);
					
					
					spnCantidadMinutos.setVisible(true);
					spnMegas.setVisible(true);
					spnCantidadDeCanales.setVisible(true);
			 		
			 		
			 		
			 		
			 	}
			 });
			rdbtnTelefonoCableInternet.setBounds(503, 138, 198, 23);
			panelDeServicios.add(rdbtnTelefonoCableInternet);
			
			rdbtnNinguno = new JRadioButton("Ninguno");
			rdbtnNinguno.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnNinguno.setSelected(true);
					rdbtnInternet.setSelected(false);
					rdbtnCable.setSelected(false);
					rdbtnTelefono.setSelected(false);
					rdbtnInternetCable.setSelected(false);
					rdbtnInternetTelefono.setSelected(false);
					rdbtnTelefonoCable.setSelected(false);
					rdbtnTelefonoCableInternet.setSelected(false);
					
					panelNinguno.setVisible(true);
					
					panelInternet.setVisible(false);
					panelCable.setVisible(false);
					panelTelefono.setVisible(false);
					spnCantidadMinutos.setVisible(false);
					spnMegas.setVisible(false);
					spnCantidadDeCanales.setVisible(false);
					
					
					
				}
			});
			rdbtnNinguno.setSelected(true);
			rdbtnNinguno.setBounds(6, 58, 109, 23);
			panelDeServicios.add(rdbtnNinguno);
			
			 panelNinguno = new JPanel();
			panelNinguno.setBounds(10, 275, 740, 119);
			panelInicial.add(panelNinguno);
			panelNinguno.setLayout(null);
			
			JLabel lblNewLabel_4 = new JLabel("ALTICE ");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_4.setFont(new Font("Century Schoolbook", Font.BOLD, 51));
			lblNewLabel_4.setBounds(196, 21, 326, 68);
			panelNinguno.add(lblNewLabel_4);
			
			 panelInternet = new JPanel();
			panelInternet.setBorder(new TitledBorder(null, "Internet", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelInternet.setBounds(10, 275, 224, 119);
			panelInicial.add(panelInternet);
			panelInternet.setLayout(null);
			
			JLabel lblNewLabel_5 = new JLabel("Megas:");
			lblNewLabel_5.setBounds(10, 42, 108, 23);
			panelInternet.add(lblNewLabel_5);
			
			 spnMegas = new JSpinner();
			 spnMegas.setVisible(false);
			spnMegas.setBounds(128, 42, 74, 20);
			panelInternet.add(spnMegas);
			
			 panelTelefono = new JPanel();
			panelTelefono.setBorder(new TitledBorder(null, "Tel\u00E9fono", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelTelefono.setBounds(246, 275, 232, 119);
			panelInicial.add(panelTelefono);
			panelTelefono.setLayout(null);
			
			JLabel lblNewLabel_6 = new JLabel("Minutos:");
			lblNewLabel_6.setBounds(10, 42, 108, 23);
			panelTelefono.add(lblNewLabel_6);
			
			 spnCantidadMinutos = new JSpinner();
			 spnCantidadMinutos.setVisible(false);
			spnCantidadMinutos.setBounds(128, 42, 74, 20);
			panelTelefono.add(spnCantidadMinutos);
			
			 panelCable = new JPanel();
			panelCable.setBorder(new TitledBorder(null, "Cable", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCable.setBounds(498, 275, 232, 119);
			panelInicial.add(panelCable);
			panelCable.setLayout(null);
			
			JLabel lblNewLabel_7 = new JLabel("Canales:");
			lblNewLabel_7.setBounds(10, 42, 108, 23);
			panelCable.add(lblNewLabel_7);
			
			 spnCantidadDeCanales = new JSpinner();
			 spnCantidadDeCanales.setVisible(false);
			spnCantidadDeCanales.setBounds(128, 42, 74, 20);
			panelCable.add(spnCantidadDeCanales);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear Plan");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						

						
						Plan aux = null;
						
						String id = txtID.getText();
						String nombre = txtNombre.getText();
						
						
						
						if(rdbtnInternet.isSelected()) {
							
							int cantMegas = (int) spnMegas.getValue();
							
				aux = new Plan(nombre, id, true, false, false, 0, cantMegas, 0);			
							
							JOptionPane.showMessageDialog(null,"Plan Creado");
							clean();
							
						}
						
	if(rdbtnCable.isSelected()) {
							
							int cantCanales = (int) spnCantidadDeCanales.getValue();
							
				aux = new Plan(nombre, id, false, false, true, 0, 0, cantCanales);			
							
							JOptionPane.showMessageDialog(null,"Plan Creado");
							clean();
							
						}
	
	if(rdbtnTelefono.isSelected()) {
		
		int cantMinutos = (int) spnCantidadMinutos.getValue();
		
aux = new Plan(nombre, id, false, false, true, cantMinutos, 0, 0);			
		
		JOptionPane.showMessageDialog(null,"Plan Creado");
		clean();
		
	}

	if(rdbtnInternetTelefono.isSelected()) {
		
		int cantInternet = (int) spnMegas.getValue();
		int cantMinutos = (int) spnCantidadMinutos.getValue();
		
aux = new Plan(nombre, id, false, false, true, cantMinutos, cantInternet, 0);			
		
		JOptionPane.showMessageDialog(null,"Plan Creado");
		clean();
		
	}

	
if(rdbtnInternetCable.isSelected()) {
		
		int cantInternet = (int) spnMegas.getValue();
		int cantCanales = (int) spnCantidadDeCanales.getValue();
		
aux = new Plan(nombre, id, false, false, true, 0, cantInternet, cantCanales);			
		
		JOptionPane.showMessageDialog(null,"Plan Creado");
		clean();
		
	}


if(rdbtnTelefonoCable.isSelected()) {
	
	int cantMinutos = (int) spnCantidadMinutos.getValue();
	int cantCanales = (int) spnCantidadDeCanales.getValue();
	
aux = new Plan(nombre, id, false, false, true, cantMinutos, 0, cantCanales);			
	
	JOptionPane.showMessageDialog(null,"Plan Creado");
	clean();
	
}

if(rdbtnTelefonoCableInternet.isSelected()) {
	
	int cantMinutos = (int) spnCantidadMinutos.getValue();
	int cantCanales = (int) spnCantidadDeCanales.getValue();
	int cantInternet = (int) spnMegas.getValue();
	
aux = new Plan(nombre, id, false, false, true, cantMinutos, cantInternet, cantCanales);			
	
	JOptionPane.showMessageDialog(null,"Plan Creado");
	clean();
	
}

Plan plan = null;
Altice.getInstance().insertarPlan(aux);

			
						
						
						

						
						
						
					}
					private void clean() {
						txtID.setText("");
						txtNombre.setText("");
						
						spnMegas.setValue(Integer.valueOf("0"));
						spnCantidadDeCanales.setValue(Integer.valueOf("0"));
						spnCantidadMinutos.setValue(Integer.valueOf("0"));
						
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
}
