package visual;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import logico.Altice;
import logico.Usuario;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JCheckBox;

public class Login extends JFrame {
	

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;


	public Login() {
		setTitle("Inicio de Sesi\u00F3n");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 321);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.cyan);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon(getClass().getResource("Fondo.png"));
				g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		panel.setForeground(Color.GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setBounds(90, 74, 191, 20);
		panel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setBounds(90, 136, 191, 20);
		panel.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setForeground(Color.BLACK);
		txtUsuario.setBounds(90, 105, 191, 23);
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtUsuario.setForeground(Color.BLACK);
		txtUsuario.setBounds(90, 105, 191, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnLogin = new JButton("Iniciar sesi\u00F3n");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Altice.getInstance().confirmLogin(txtUsuario.getText(),txtContrasena.getText()))
				{
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				}
				else{
											
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorecta", null, JOptionPane.ERROR_MESSAGE, null);
				}
				
			}
		});
		btnLogin.setBounds(124, 238, 121, 23);
		panel.add(btnLogin);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		txtContrasena.setForeground(Color.BLACK);
		txtContrasena.setBounds(90, 162, 191, 23);
		txtContrasena.setForeground(Color.BLACK);
		txtContrasena.setBounds(90, 162, 191, 20);
		panel.add(txtContrasena);
		
		JLabel lblNewLabel = new JLabel("BIENVENIDO A ALTICE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(10, 22, 351, 41);
		panel.add(lblNewLabel);
	}
}

