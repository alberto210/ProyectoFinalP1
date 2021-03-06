package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.border.TitledBorder;

import logico.Altice;
import logico.Plan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.SpinnerNumberModel;

public class CrearPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtID;
	private JRadioButton rdbtnInternet;
	private JRadioButton rdbtnTelefono;
	private JRadioButton rdbtnCable;
	private JPanel panelDeServicios;
	private JPanel panelInicial;
	private JPanel panelInternet;
	private JSpinner spnMegas;
	private JPanel panelTelefono;
	private JSpinner spnCantidadMinutos;
	private JPanel panelCable;
	private JSpinner spnCantidadDeCanales;
	private JPanel panelNinguno;
	private JLabel lbltipo;
	private JButton btnCrear;

	public CrearPlan(String title, int mode, Plan aux) {
		setTitle(title);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Logo.jpg"));
		setBounds(100, 100, 786, 498);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panelInicial = new JPanel();
			panelInicial.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panelInicial, BorderLayout.CENTER);
			panelInicial.setLayout(null);

			panelNinguno = new JPanel() {
				public void paintComponent(Graphics g) {
					ImageIcon img = new ImageIcon(getClass().getResource("Fondo.png"));
					g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
				}
			};
			panelNinguno.setBounds(10, 11, 740, 119);
			panelInicial.add(panelNinguno);
			panelNinguno.setLayout(null);

			JLabel lblNewLabel_4 = new JLabel("ALTICE ");
			lblNewLabel_4.setBounds(202, 24, 326, 68);
			panelNinguno.add(lblNewLabel_4);
			lblNewLabel_4.setForeground(Color.WHITE);
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_4.setFont(new Font("Century Schoolbook", Font.BOLD, 51));

			panelInternet = new JPanel();
			panelInternet.setBorder(new TitledBorder(null, "Internet", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelInternet.setBounds(10, 286, 224, 119);
			panelInicial.add(panelInternet);
			panelInternet.setLayout(null);
			if(mode == 0) {
				panelInternet.setVisible(false);
			}
			if(mode == 1) {
				if(aux.getTipo().equalsIgnoreCase("Internet") || aux.getTipo().equalsIgnoreCase("Internet + TV") || aux.getTipo().equalsIgnoreCase("Internet + Voz Digital")
						|| aux.getTipo().equalsIgnoreCase("TV + Internet + Voz Digital")) {
					panelInternet.setVisible(true);
				}
				else {
					panelInternet.setVisible(false);
				}
			}

			JLabel lblNewLabel_5 = new JLabel("Megas:");
			lblNewLabel_5.setBounds(10, 42, 108, 23);
			panelInternet.add(lblNewLabel_5);

			spnMegas = new JSpinner();
			spnMegas.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			if(mode == 0) {
				spnMegas.setVisible(false);
			}
			if(mode == 1) {
				if(aux.getTipo().equalsIgnoreCase("Internet") || aux.getTipo().equalsIgnoreCase("Internet + TV") || aux.getTipo().equalsIgnoreCase("Internet + Voz Digital")
						|| aux.getTipo().equalsIgnoreCase("TV + Internet + Voz Digital")) {
					spnMegas.setVisible(true);
				}
				else {
					spnMegas.setVisible(false);
				}
			}
			spnMegas.setBounds(128, 42, 74, 20);
			panelInternet.add(spnMegas);

			panelTelefono = new JPanel();
			panelTelefono.setBorder(
					new TitledBorder(null, "Tel\u00E9fono", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelTelefono.setBounds(244, 286, 232, 119);
			panelInicial.add(panelTelefono);
			panelTelefono.setLayout(null);
			if(mode == 0) {
				panelTelefono.setVisible(false);
			}
			if(mode == 1) {
				if(aux.getTipo().equalsIgnoreCase("Voz") || aux.getTipo().equalsIgnoreCase("TV + Voz Digital") || aux.getTipo().equalsIgnoreCase("Internet + Voz Digital")
						|| aux.getTipo().equalsIgnoreCase("TV + Internet + Voz Digital")) {
					panelTelefono.setVisible(true);
				}
				else {
					panelTelefono.setVisible(false);
				}
			}
			

			JLabel lblNewLabel_6 = new JLabel("Minutos:");
			lblNewLabel_6.setBounds(10, 42, 108, 23);
			panelTelefono.add(lblNewLabel_6);

			spnCantidadMinutos = new JSpinner();
			spnCantidadMinutos.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			if(mode == 0) {
				spnCantidadMinutos.setVisible(false);
			}
			if(mode == 1) {
				if(aux.getTipo().equalsIgnoreCase("Voz") || aux.getTipo().equalsIgnoreCase("TV + Voz Digital") || aux.getTipo().equalsIgnoreCase("Internet + Voz Digital")
						|| aux.getTipo().equalsIgnoreCase("TV + Internet + Voz Digital")) {
					spnCantidadMinutos.setVisible(true);
				}
				else {
					spnCantidadMinutos.setVisible(true);
				}
			}
			spnCantidadMinutos.setBounds(128, 42, 74, 20);
			panelTelefono.add(spnCantidadMinutos);

			panelCable = new JPanel();
			panelCable.setBorder(new TitledBorder(null, "Cable", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCable.setBounds(498, 286, 232, 119);
			panelInicial.add(panelCable);
			panelCable.setLayout(null);
			if(mode == 0) {
				panelCable.setVisible(false);
			}
			if(mode == 1) {
				if(aux.getTipo().equalsIgnoreCase("TV") || aux.getTipo().equalsIgnoreCase("TV + Voz Digital") || aux.getTipo().equalsIgnoreCase("Internet + TV")
						|| aux.getTipo().equalsIgnoreCase("TV + Internet + Voz Digital")) {
					panelCable.setVisible(true);
				}
				else {
					panelCable.setVisible(false);
				}
			}

			JLabel lblNewLabel_7 = new JLabel("Canales:");
			lblNewLabel_7.setBounds(10, 42, 108, 23);
			panelCable.add(lblNewLabel_7);

			spnCantidadDeCanales = new JSpinner();
			spnCantidadDeCanales.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			if(mode == 0) {
				spnCantidadDeCanales.setVisible(false);
			}
			if(mode == 1) {
				if(aux.getTipo().equalsIgnoreCase("TV") || aux.getTipo().equalsIgnoreCase("TV + Voz Digital") || aux.getTipo().equalsIgnoreCase("Internet + TV")
						|| aux.getTipo().equalsIgnoreCase("TV + Internet + Voz Digital")) {
					spnCantidadDeCanales.setVisible(true);
				}
				else {
					spnCantidadDeCanales.setVisible(false);
				}
			}
			spnCantidadDeCanales.setBounds(128, 42, 74, 20);
			panelCable.add(spnCantidadDeCanales);

			panelDeServicios = new JPanel();
			panelDeServicios.setBounds(10, 141, 740, 139);
			panelInicial.add(panelDeServicios);
			panelDeServicios.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
					"Informaci\u00F3n para creaci\u00F3n del plan:", TitledBorder.LEADING, TitledBorder.TOP, null,
					new Color(0, 0, 0)));
			panelDeServicios.setLayout(null);

			JLabel lblNewLabel_1 = new JLabel("ID:");
			lblNewLabel_1.setBounds(10, 28, 46, 14);
			panelDeServicios.add(lblNewLabel_1);

			txtID = new JTextField();
			if(mode == 0) {
				txtID.setText("P-"+Altice.getInstance().getIdPlan());
			}
			if(mode == 1) {
				txtID.setText(aux.getId());
			}
			txtID.setEditable(false);
			txtID.setBounds(10, 53, 180, 23);
			panelDeServicios.add(txtID);
			txtID.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setBounds(10, 81, 100, 19);
			panelDeServicios.add(lblNewLabel);

			txtNombre = new JTextField();
			if(mode == 1) {
				txtNombre.setText(aux.getNombre());
			}
			txtNombre.setEditable(false);
			txtNombre.setBounds(10, 105, 180, 23);
			panelDeServicios.add(txtNombre);
			txtNombre.setColumns(10);

			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Seleccione los servicios del plan:", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panel.setBounds(240, 11, 459, 117);
			panelDeServicios.add(panel);
			panel.setLayout(null);

			rdbtnInternet = new JRadioButton("Internet");
			if(mode == 1) {
				rdbtnInternet.setEnabled(false);
			}
			rdbtnInternet.setBounds(17, 42, 109, 23);
			panel.add(rdbtnInternet);

			lbltipo = new JLabel("");
			if(mode == 1) {
				lbltipo.setText(aux.getTipo());
			}
			lbltipo.setBounds(192, 92, 223, 14);
			panel.add(lbltipo);
			
			rdbtnTelefono = new JRadioButton("Tel\u00E9fono");
			if(mode == 1) {
				rdbtnTelefono.setEnabled(false);
			}
			rdbtnTelefono.setBounds(172, 42, 109, 23);
			panel.add(rdbtnTelefono);

			rdbtnCable = new JRadioButton("Cable");
			if(mode == 1) {
				rdbtnCable.setEnabled(false);
			}
			rdbtnCable.setBounds(333, 42, 109, 23);
			panel.add(rdbtnCable);
			
			JLabel lblNewLabel_2 = new JLabel("Tipo de plan:");
			lblNewLabel_2.setBounds(112, 92, 80, 14);
			panel.add(lblNewLabel_2);
			rdbtnCable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(mode == 0) {
						if (rdbtnCable.isSelected()) {
							panelCable.setVisible(true);
							spnCantidadDeCanales.setVisible(true);
						} else {
							panelCable.setVisible(false);
							spnCantidadDeCanales.setVisible(false);
						}
						actualizarNombre();
						actualizarTipo();
					}
				}
			});
			rdbtnTelefono.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
					if(mode == 0) {
						if (rdbtnTelefono.isSelected()) {
							panelTelefono.setVisible(true);
							spnCantidadMinutos.setVisible(true);
						} else {
							panelTelefono.setVisible(false);
							spnCantidadMinutos.setVisible(false);
						}
						actualizarNombre();
						actualizarTipo();
					}
				}
			});
			rdbtnInternet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(mode == 0) {
						if (rdbtnInternet.isSelected()) {
							panelInternet.setVisible(true);
							spnMegas.setVisible(true);
						} else {
							panelInternet.setVisible(false);
							spnMegas.setVisible(false);
						}
						actualizarNombre();
						actualizarTipo();
					}
				}
					
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCrear = new JButton("");
				if(mode == 0) {
					btnCrear.setText("Crear Plan");
				}
				if(mode == 1) {
					btnCrear.setText("Modificar Plan");
				}
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(mode == 0) {
							String id = txtID.getText();
							String nombre = txtNombre.getText();
							String tipo = lbltipo.getText();
							int cantMegas = 0;
							int cantCanales = 0;
							int cantMinutos = 0;
							if ((!rdbtnCable.isSelected() && !rdbtnInternet.isSelected() && !rdbtnTelefono.isSelected()) || id.equalsIgnoreCase("") || nombre.equalsIgnoreCase("")) {
								JOptionPane.showMessageDialog(null, "Campos vac�os. Por favor, llene todos los campos","Informaci�n", JOptionPane.WARNING_MESSAGE);
							} else if(rdbtnCable.isSelected() && Float.parseFloat(spnCantidadDeCanales.getValue().toString()) == 0){
								JOptionPane.showMessageDialog(null, "Campos vac�os. Por favor, llene todos los campos","Informaci�n", JOptionPane.WARNING_MESSAGE);
							}else if(rdbtnInternet.isSelected() && Float.parseFloat(spnMegas.getValue().toString()) == 0){
								JOptionPane.showMessageDialog(null, "Campos vac�os. Por favor, llene todos los campos","Informaci�n", JOptionPane.WARNING_MESSAGE);
							}else if(rdbtnTelefono.isSelected() && Float.parseFloat(spnCantidadMinutos.getValue().toString()) == 0){
								JOptionPane.showMessageDialog(null, "Campos vac�os. Por favor, llene todos los campos","Informaci�n", JOptionPane.WARNING_MESSAGE);
							}
							else {
								if (rdbtnInternet.isSelected()) {
									cantMegas = (int) spnMegas.getValue();
								}

								if (rdbtnCable.isSelected()) {
									cantCanales = (int) spnCantidadDeCanales.getValue();
								}

								if (rdbtnTelefono.isSelected()) {
									cantMinutos = (int) spnCantidadMinutos.getValue();
								}
						
								Altice.getInstance().crearPlan(tipo,nombre, Altice.getInstance().getIdPlan(), rdbtnInternet.isSelected(),
										rdbtnTelefono.isSelected(), rdbtnCable.isSelected(), cantMinutos, cantMegas,
										cantCanales);
								Altice.getInstance().aumentarIdPlan();
								JOptionPane.showMessageDialog(null, "El plan se ha creado de manera exitosa");
								clean();
							}
							if(mode == 1) {
								if(aux != null) {
									int option = JOptionPane.showConfirmDialog(null, "Est� seguro que desea modificar la informaci�n de este plan?", "Confirmaci�n", JOptionPane.OK_CANCEL_OPTION);
									if(option == JOptionPane.OK_OPTION) {
										int megas = (int)spnMegas.getValue();
										int canales = (int)spnCantidadDeCanales.getValue();
										int minutos = (int)spnCantidadMinutos.getValue();
										aux.setCantMegas(megas);
										aux.setCantCanales(canales);
										aux.setCantMinutos(minutos);
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "Hubo un error al tratar de modificar el plan", "Informaci�n", JOptionPane.ERROR_MESSAGE);
									dispose();
								}
								ListPlanes.llenartabla();
								clean();
								dispose();
							}
						}

					}
					
					
					private void clean() {
						txtID.setText("P-" + Altice.getInstance().getIdPlan());
						txtNombre.setText("");
						spnMegas.setValue(Integer.valueOf("0"));
						spnCantidadDeCanales.setValue(Integer.valueOf("0"));
						spnCantidadMinutos.setValue(Integer.valueOf("0"));
						rdbtnCable.setSelected(false);
						rdbtnInternet.setSelected(false);
						rdbtnTelefono.setSelected(false);
						panelCable.setVisible(false);
						panelInternet.setVisible(false);
						panelTelefono.setVisible(false);
						actualizarNombre();
						actualizarTipo();

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
	
	private void actualizarNombre() {
		if(rdbtnInternet.isSelected()) {
			txtNombre.setText("Internet");
		}
		if(rdbtnCable.isSelected()) {
			txtNombre.setText("Altice TV");
		}
		if(rdbtnTelefono.isSelected()) {
			txtNombre.setText("Voz Digital");
		}
		if((rdbtnInternet.isSelected() && rdbtnCable.isSelected()) || (rdbtnCable.isSelected() && rdbtnTelefono.isSelected()) || (rdbtnTelefono.isSelected() && rdbtnInternet.isSelected())) {
			txtNombre.setText("Dobleplay");
		}
		if(rdbtnInternet.isSelected() && rdbtnCable.isSelected() && rdbtnTelefono.isSelected()) {
			txtNombre.setText("Tripleplay");
		}
		if(!rdbtnInternet.isSelected() && !rdbtnCable.isSelected() && !rdbtnTelefono.isSelected()) {
			txtNombre.setText("");
		}
	}
	
	private void actualizarTipo() {
		if(rdbtnInternet.isSelected()) {
			lbltipo.setText("Internet");
		}
		if(rdbtnCable.isSelected()) {
			lbltipo.setText("TV");
		}
		if(rdbtnTelefono.isSelected()) {
			lbltipo.setText("Voz");
		}
		if(rdbtnInternet.isSelected() && rdbtnCable.isSelected()) {
			lbltipo.setText("Internet + TV");
		}
		if(rdbtnCable.isSelected() && rdbtnTelefono.isSelected()) {
			lbltipo.setText("TV + Voz Digital");
		}
		if(rdbtnTelefono.isSelected() && rdbtnInternet.isSelected()) {
			lbltipo.setText("Internet + Voz Digital");
		}
		if(rdbtnInternet.isSelected() && rdbtnCable.isSelected() && rdbtnTelefono.isSelected()) {
			lbltipo.setText("TV + Internet + Voz Digital");
		}
		if(!rdbtnInternet.isSelected() && !rdbtnCable.isSelected() && !rdbtnTelefono.isSelected()) {
			lbltipo.setText("");
		}
	}
}
