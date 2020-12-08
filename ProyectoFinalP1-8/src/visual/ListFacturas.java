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

import logico.Altice;
import logico.Cliente;
import logico.Factura;
import logico.Nomina;

public class ListFacturas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnMod;
	private JComboBox cbxFiltro;
	private JTextField txtNombre;
	private JTable table;
	public static DefaultTableModel modelo;
	public static Object[] rows;
	private int seleccion = -1;
	private Factura aux = null;
	private Factura auxClient = null;
	private Cliente client = null;

	public ListFacturas() {
			setTitle("Lista de Facturas");
			setIconImage(Toolkit.getDefaultToolkit().getImage("Logo.jpg"));
			setBounds(100, 100, 690, 320);
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
				separator.setBounds(10, 41, 644, 2);
				panel.add(separator);
				
				JPanel panel_Listado = new JPanel();
				panel_Listado.setBounds(10, 54, 644, 173);
				panel.add(panel_Listado);
				panel_Listado.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane = new JScrollPane();
				panel_Listado.add(scrollPane, BorderLayout.CENTER);
				
				modelo = new DefaultTableModel();
				String[] headers = {"Estado","ID","Nombre","Cédula","Monto a pagar","Fecha Límite"};
				modelo.setColumnIdentifiers(headers);
				
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						seleccion = table.getSelectedRow();
						if(seleccion != -1) {
							btnMod.setEnabled(true);
							aux = Altice.getInstance().findFactura((String)table.getValueAt(seleccion, 1));
							client = Altice.getInstance().buscarCliente((String)table.getValueAt(seleccion, 3));
							auxClient = client.findFactura((String)table.getValueAt(seleccion, 1));
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
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					btnMod = new JButton("Pagar");
					btnMod.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							auxClient.setEstado("Pagada");
							aux.setEstado("Pagada");
							aux.setMontoPagado(aux.cobrarDiasConsumidosPrimeraFactura());
							llenarTabla();
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
			for (Factura fac : Altice.getInstance().getMisFacturas()) {
					rows[4] = String.format("%.1f", fac.cobrarDiasConsumidosPrimeraFactura());
					rows[0] = fac.getEstado();
					rows[1] = fac.getCodFactura();
					rows[2] = fac.getCliente().getNombre();
					rows[3] = fac.getCliente().getCedula();
					rows[5] = fac.getCorte().getDate() + "/" +(fac.getCorte().getMonth()+1) + "/" + (fac.getCorte().getYear()+1900);
				modelo.addRow(rows);
			}
		}
		
		private void FiltarTabla(String nombre) {
			TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<DefaultTableModel>(modelo);
			table.setRowSorter(filtro);
			if(cbxFiltro.getSelectedItem().toString().equalsIgnoreCase("Nombre")) {
				filtro.setRowFilter(RowFilter.regexFilter("^"+nombre,2));
			}else {
				filtro.setRowFilter(RowFilter.regexFilter("^"+nombre,3));
			}
		}

}
