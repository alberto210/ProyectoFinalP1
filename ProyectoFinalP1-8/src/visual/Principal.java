package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Altice;
import logico.Usuario;
import javax.swing.SwingConstants;

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
		
		JMenu mnAdministracin = new JMenu("Administraci\u00F3n                                                    ");
		if(!Altice.getLoginUser().getTipoDeUsuario().equalsIgnoreCase("Administrador")){
			mnAdministracin.setEnabled(false);
		}
		menuBar.add(mnAdministracin);
		
		JMenu menu = new JMenu("Usuario");
		mnAdministracin.add(menu);
		
		JMenuItem menuItem = new JMenuItem("Crear Nuevo Usuario");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearUsuario aux = new CrearUsuario();
				aux.setVisible(true);
			}
		});
		menu.add(menuItem);
		
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
