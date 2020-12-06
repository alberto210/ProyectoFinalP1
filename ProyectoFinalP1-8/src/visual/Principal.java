package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Altice;
import logico.Usuario;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Principal extends JFrame {

	private JPanel contentPane;
			//Se movio el metodo main a la pestaña principal
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream altice;
				FileOutputStream altice2;
				ObjectInputStream alticeRead;
				ObjectOutputStream alticeWrite;
				try {
					altice = new FileInputStream ("Altice.dat");
					alticeRead = new ObjectInputStream(altice);
					Altice temp = (Altice)alticeRead.readObject();
					Altice.setEmpresa(temp);
					altice.close();
					alticeRead.close();
				} catch (FileNotFoundException e) {
					try {
						altice2 = new  FileOutputStream("Altice.dat");
						alticeWrite = new ObjectOutputStream(altice2);
						Usuario aux = new Usuario("Administrador", "Altice", "Altice");
						Altice.getInstance().regUserDefault(aux);
						alticeWrite.writeObject(Altice.getInstance());
						altice2.close();
						alticeWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Principal() {
		if(Altice.getInstance().admin) {
			setTitle("Altice - Usuario: Master");
		}else {
			Altice.getInstance();
			setTitle("Altice - Usuario: " + Altice.getLoginEmpleado().getNombre());
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage("Logo.jpg"));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream altice2;
				ObjectOutputStream alticeWrite;
				try {
					altice2 = new  FileOutputStream("Altice.dat");
					alticeWrite = new ObjectOutputStream(altice2);
					alticeWrite.writeObject(Altice.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 385);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Altice                                                                     ");
		menuBar.add(mnClientes);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listado Clientes / Remover Planes");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListClientes aux = new ListClientes();
				aux.setVisible(true);
			}
		});
		mnClientes.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Agregar Plan a Cliente");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarPlanACliente agregar = new AgregarPlanACliente("Agregar Plan a Cliente");
				agregar.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Listado de Planes");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListPlanes aux = new ListPlanes();
				aux.setVisible(true);
			}
		});
		mnClientes.add(mntmNewMenuItem_6);
		mnClientes.add(mntmNewMenuItem_5);
		
		JMenu mnAdministracin = new JMenu("Administraci\u00F3n                                                    ");
		if(!Altice.getLoginUser().getTipoDeUsuario().equalsIgnoreCase("Administrador")){
			mnAdministracin.setEnabled(false);
		}
		menuBar.add(mnAdministracin);
		
		JMenuItem mntmCrearNuevoEmpleado = new JMenuItem("Crear Nuevo Empleado");
		mnAdministracin.add(mntmCrearNuevoEmpleado);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Crear Nuevo Plan");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearPlan aux = new CrearPlan();
				aux.setVisible(true);
			}
		});
		mnAdministracin.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Listado de Empleados");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListEmpleados list = new ListEmpleados("Listado de Empleados");
				list.setResizable(false);
				list.setModal(true);
				list.setVisible(true);
			}
		});
		mnAdministracin.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Estad\u00EDsticas");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estadisticas aux = new Estadisticas();
				aux.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Listado de Nominas");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListNominas aux = new ListNominas();
				aux.setVisible(true);
			}
		});
		mnAdministracin.add(mntmNewMenuItem_8);
		mnAdministracin.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Generar Nomina");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean aux = Altice.getInstance().crearNominas();
				if(aux) {
					JOptionPane.showMessageDialog(null, "No se ha podido emitir las nominas ya que se ha hecho anteriormente", "Información", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Nominas emitidas satisfactoriamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		mnAdministracin.add(mntmNewMenuItem_7);
		mntmCrearNuevoEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearUsuario aux = new CrearUsuario("Nuevo Usuario",0,null);
				aux.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cerrar Sesi\u00F3n");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileOutputStream altice2;
				ObjectOutputStream alticeWrite;
				try {
					altice2 = new  FileOutputStream("Altice.dat");
					alticeWrite = new ObjectOutputStream(altice2);
					alticeWrite.writeObject(Altice.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Login aux = new Login();
				aux.setVisible(true);
				dispose();
			}
		});
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel(){  
			public void paintComponent(Graphics g){  
				Image img = Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("Fondo.png"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
			}  
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
