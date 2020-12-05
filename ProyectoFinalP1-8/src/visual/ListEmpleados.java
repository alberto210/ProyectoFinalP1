package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logico.Administrativo;
import logico.Altice;
import logico.Comercial;
import logico.Empleado;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnElim;
	private JButton btnMod;
	private JTextField txtNombre;
	private JTable table;
	public static DefaultTableModel modelo;
	public static Object[] rows;
	private int seleccion = -1;
	private Empleado aux = null;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ListEmpleados dialog = new ListEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListEmpleados(String title) {
		setTitle(title);
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
				JLabel lblNewLabel = new JLabel("Nombre del Empleado:");
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
			txtNombre.setBounds(165, 7, 237, 23);
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
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			panel_Listado.add(scrollPane, BorderLayout.CENTER);
			
			modelo = new DefaultTableModel();
			String[] headers = {"Nombre","Cédula","Tipo"};
			modelo.setColumnIdentifiers(headers);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					seleccion = table.getSelectedRow();
					if(seleccion != -1) {
						btnMod.setEnabled(true);
						aux = Altice.getInstance().buscarEmpleado((String)table.getValueAt(seleccion, 1));
					}
				}
			});
			table.setModel(modelo);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnMod = new JButton("Modificar");
				btnMod.setEnabled(false);
				buttonPane.add(btnMod);
			}
			{
				btnElim = new JButton("Eliminar");
				btnElim.setEnabled(false);
				btnElim.setActionCommand("OK");
				buttonPane.add(btnElim);
				getRootPane().setDefaultButton(btnElim);
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
		for (Empleado empleado : Altice.getInstance().getMisEmpleados()) {
			String tipo = null;
			if(empleado instanceof Administrativo) {
				tipo = "Administrativo";
				rows[0] = empleado.getNombre();
				rows[1] = empleado.getCedula();
				rows[2] = tipo;
			}
			if(empleado instanceof Comercial) {
				tipo = "Comercial";
				rows[0] = empleado.getNombre();
				rows[1] = empleado.getCedula();
				rows[2] = tipo;
			}
			modelo.addRow(rows);
		}
	}
	
	private void FiltarTabla(String nombre) {
		TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<DefaultTableModel>(modelo);
		table.setRowSorter(filtro);
		filtro.setRowFilter(RowFilter.regexFilter(nombre));
	}
	
}
