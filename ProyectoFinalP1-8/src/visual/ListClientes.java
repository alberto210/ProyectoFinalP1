

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
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
import logico.Comercial;
import logico.Empleado;
import logico.Plan;

public class ListClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnMod;
	private JButton btnPlan;
	private JComboBox cbxFiltro;
	private JTextField txtNombre;
	private JTable table;
	public static DefaultTableModel modelo;
	public static Object[] rows;
	private int seleccion = -1;
	private Cliente aux = null;
	private JTable tableConsulta;
	public static DefaultTableModel modelo2;
	public static ArrayList<Object[]> rows2;
	private int index = -1;
	private Plan plan = null;
	private JButton btnCancelarPlan;

	public ListClientes() {
			setTitle("Lista de Clientes");
			setIconImage(Toolkit.getDefaultToolkit().getImage("Logo.jpg"));
			setBounds(100, 100, 1081, 491);
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
					JLabel lblNewLabel = new JLabel("Filtrar por:");
					lblNewLabel.setBounds(10, 11, 162, 14);
					panel.add(lblNewLabel);
				}
				
				txtNombre = new JTextField();
				txtNombre.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						String nombre = txtNombre.getText();
						FiltarTabla(nombre);
					}
				});
				txtNombre.setBounds(398, 7, 237, 23);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
				
				JSeparator separator = new JSeparator();
				separator.setBounds(10, 41, 1035, 2);
				panel.add(separator);
				
				JPanel panel_Listado = new JPanel();
				panel_Listado.setBounds(10, 54, 1035, 173);
				panel.add(panel_Listado);
				panel_Listado.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
				panel_Listado.add(scrollPane, BorderLayout.CENTER);
				
				modelo = new DefaultTableModel();
				String[] headers = {"Nombre","Cédula","Email","Dirección"};
				modelo.setColumnIdentifiers(headers);
				
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						seleccion = table.getSelectedRow();
						if(seleccion != -1) {
							btnPlan.setEnabled(true);
							btnMod.setEnabled(true);
							aux = Altice.getInstance().buscarCliente((String)table.getValueAt(seleccion, 1));
						}
					}
				});
				table.setModel(modelo);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(table);
				
				cbxFiltro = new JComboBox();
				cbxFiltro.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						String nombre = txtNombre.getText();
						FiltarTabla(nombre);
					}
				});
				cbxFiltro.setModel(new DefaultComboBoxModel(new String[] {"Nombre", "C\u00E9dula"}));
				cbxFiltro.setBounds(72, 8, 123, 23);
				panel.add(cbxFiltro);
				
				JLabel lblNewLabel_1 = new JLabel("Filtro:");
				lblNewLabel_1.setBounds(357, 11, 52, 14);
				panel.add(lblNewLabel_1);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(10, 238, 1035, 2);
				panel.add(separator_1);
				
				JPanel panel_ConsultaPlanes = new JPanel();
				panel_ConsultaPlanes.setBorder(new TitledBorder(null, "Consulta de planes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_ConsultaPlanes.setBounds(10, 251, 1035, 133);
				panel.add(panel_ConsultaPlanes);
				panel_ConsultaPlanes.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel_ConsultaPlanes.add(scrollPane_1, BorderLayout.CENTER);
				
				modelo2 = new DefaultTableModel();
				String[] headers2 = {"Estado","ID","Nombre","Tipo","Megas","Canales","Minutos","Precio sin Impuestos"};
				modelo2.setColumnIdentifiers(headers2);
				
				tableConsulta = new JTable();
				tableConsulta.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						index = tableConsulta.getSelectedRow();
						if(index != -1) {
							btnCancelarPlan.setEnabled(true);
							plan = aux.buscarPlan(tableConsulta.getValueAt(index, 1).toString());
						}
					}
				});
				tableConsulta.setModel(modelo2);
				scrollPane_1.setViewportView(tableConsulta);
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				
				btnCancelarPlan = new JButton("Cancelar Plan");
				btnCancelarPlan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
					}
				});
				btnCancelarPlan.setEnabled(false);
				buttonPane.add(btnCancelarPlan);
				{
					btnPlan = new JButton("Consultar Planes");
					btnPlan.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cargarTablaConsulta(aux);
							btnPlan.setEnabled(false);
						}
					});
					btnPlan.setEnabled(false);
					buttonPane.add(btnPlan);
				}
				{
					btnMod = new JButton("Modificar Cliente");
					btnMod.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ModCliente mod = new ModCliente("Modificar Cliente",1,aux);
							mod.setVisible(true);
							seleccion = -1;
							btnMod.setEnabled(false);
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
			llenarTabla();
		}
		
		public static void llenarTabla() {
			modelo.setRowCount(0);
			rows = new Object[modelo.getColumnCount()];
			for (Cliente client : Altice.getInstance().getMisClientes()) {
					rows[0] = client.getNombre();
					rows[1] = client.getCedula();
					rows[2] = client.getEmail();
					rows[3] = client.getDireccion();
				modelo.addRow(rows);
			}
		}
		
		private void FiltarTabla(String nombre) {
			TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<DefaultTableModel>(modelo);
			table.setRowSorter(filtro);
			if(cbxFiltro.getSelectedItem().toString().equalsIgnoreCase("Nombre")) {
				filtro.setRowFilter(RowFilter.regexFilter("^"+nombre,0));
			}else {
				filtro.setRowFilter(RowFilter.regexFilter("^"+nombre,1));
			}
		}
		
		private void cargarTablaConsulta(Cliente cliente) {
			modelo2.setRowCount(0);
			rows2 = new ArrayList<Object[]>();
			Object[] consultaPlanes = null;
			for (Plan planes : cliente.getMisPlanes()) {
				consultaPlanes = new Object[] {planes.getEstado(),planes.getId(),planes.getNombre(),planes.getTipo(),
						planes.getCantMegas(),planes.getCantCanales(),planes.getCantMinutos(),
						String.format("%.1f", planes.getPrecioTotal())};
				rows2.add(consultaPlanes);
			}
			for (Object[] row : rows2) {
				modelo2.addRow(row);
			}
		}
		
}