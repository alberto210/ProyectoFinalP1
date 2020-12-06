package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logico.Administrativo;
import logico.Altice;
import logico.Cliente;
import logico.Plan;

public class ListPlanes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnMod;
	private JButton btnEliminarPlan;
	private JButton btnActivarPlan;
	private JComboBox cbxFiltro;
	private JTable table;
	public static DefaultTableModel modelo;
	public static Object[] rows;
	private int seleccion = -1;
	private Plan aux = null;

	public ListPlanes() {
			setTitle("Lista de Planes");
			setIconImage(Toolkit.getDefaultToolkit().getImage("Logo.jpg"));
			setBounds(100, 100, 1004, 320);
			setLocationRelativeTo(null);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				contentPanel.add(panel, BorderLayout.CENTER);
				panel.setLayout(null);
				{
					JLabel lblNewLabel = new JLabel("Filtrar por Nombre:");
					lblNewLabel.setBounds(10, 11, 162, 14);
					panel.add(lblNewLabel);
				}
				
				JSeparator separator = new JSeparator();
				separator.setBounds(10, 41, 943, 2);
				panel.add(separator);
				
				JPanel panel_Listado = new JPanel();
				panel_Listado.setBounds(10, 54, 943, 173);
				panel.add(panel_Listado);
				panel_Listado.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
				panel_Listado.add(scrollPane, BorderLayout.CENTER);
				
				modelo = new DefaultTableModel();
				String[] headers = {"Estado","ID","Nombre","Tipo","Megas","Canales","Minutos","Precio sin Impuestos"};
				modelo.setColumnIdentifiers(headers);
				
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						seleccion = table.getSelectedRow();
						if(seleccion != -1) {
							if(Altice.getLoginEmpleado() instanceof Administrativo) {
								btnActivarPlan.setEnabled(true);
								btnEliminarPlan.setEnabled(true);
								btnMod.setEnabled(true);
								aux = Altice.getInstance().buscarPlan((String)table.getValueAt(seleccion, 1));
							}
						}
					}
				});
				table.setModel(modelo);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(table);
				
				cbxFiltro = new JComboBox();
				cbxFiltro.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						String nombre = cbxFiltro.getSelectedItem().toString();
						FiltarTabla(nombre);
					}
				});
				cbxFiltro.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Internet", "Altice TV", "Voz Digital", "Dobleplay", "Tripleplay"}));
				cbxFiltro.setBounds(128, 7, 123, 23);
				panel.add(cbxFiltro);
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					btnEliminarPlan = new JButton("Eliminar Plan");
					btnEliminarPlan.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							aux.setEstado("Cancelado");
							llenartabla();
							btnActivarPlan.setEnabled(false);
							btnEliminarPlan.setEnabled(false);
							btnMod.setEnabled(false);
							JOptionPane.showMessageDialog(null, "El plan seleccionado ha sido cancelado", null, JOptionPane.INFORMATION_MESSAGE, null);
						}
					});
					{
						btnActivarPlan = new JButton("Activar Plan");
						btnActivarPlan.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								aux.setEstado("Activo");
								llenartabla();
								btnActivarPlan.setEnabled(false);
								btnEliminarPlan.setEnabled(false);
								btnMod.setEnabled(false);
								JOptionPane.showMessageDialog(null, "El plan seleccionado ha sido Activado", null, JOptionPane.INFORMATION_MESSAGE, null);
							}
						});
						btnActivarPlan.setEnabled(false);
						buttonPane.add(btnActivarPlan);
					}
					btnEliminarPlan.setEnabled(false);
					buttonPane.add(btnEliminarPlan);
				}
				{
					btnMod = new JButton("Modificar Plan");
					btnMod.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
						}
					});
					btnMod.setEnabled(false);
					btnMod.setActionCommand("OK");
					buttonPane.add(btnMod);
					getRootPane().setDefaultButton(btnMod);
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
			llenartabla();
		}
		
		public static void llenartabla() {
			modelo.setRowCount(0);
			rows = new Object[modelo.getColumnCount()];
			for (Plan temp : Altice.getInstance().getMisPlanes()) {
					rows[0] = temp.getEstado();
					rows[1] = temp.getId();
					rows[2] = temp.getNombre();
					rows[3] = temp.getTipo();
					rows[4] = temp.getCantMegas();
					rows[5] = temp.getCantCanales();
					rows[6] = temp.getCantMinutos();
					rows[7] = String.format("%.1f", temp.getPrecioTotal());
				modelo.addRow(rows);
			}
		}
		
		private void FiltarTabla(String nombre) {
			TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<DefaultTableModel>(modelo);
			table.setRowSorter(filtro);
			if(!nombre.equalsIgnoreCase("<Seleccione>")) {
				filtro.setRowFilter(RowFilter.regexFilter("^"+nombre,2));
			}else {
				filtro.setRowFilter(null);
			}
		}


}
