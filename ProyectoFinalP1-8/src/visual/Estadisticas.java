package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import logico.Altice;

public class Estadisticas extends JFrame {

	private JPanel contentPane;


	public Estadisticas() {
		setTitle("Estadisticas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(690,453);
		setLocationRelativeTo(null);
		init();
	}

	  private void init() {

		  	contentPane = new JPanel();
	        getContentPane().add(contentPane);
	        // Fuente de Datos
	        int[] usuarios = Altice.getInstance().CantUsiarosPorPlan();
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        dataset.setValue(usuarios[6], "Voz", "Singleplay");
	        dataset.setValue(usuarios[5], "Internet", "Singleplay");
	        dataset.setValue(usuarios[4], "TV", "Singleplay");
	        dataset.setValue(usuarios[3], "TV + Voz Digital", "Dobleplay");
	        dataset.setValue(usuarios[2], "Internet + Voz Digital", "Dobleplay");
	        dataset.setValue(usuarios[1], "Internet + TV", "Dobleplay");
	        dataset.setValue(usuarios[0], "Internet + TV + Voz Digital", "Tripleplay");
	        
	        // Creando el Grafico
	        ImageIcon img = new ImageIcon(getClass().getResource("Fondo.png"));
	        JFreeChart chart = ChartFactory.createBarChart3D
	        ("Cantidad de usuarios por plan","Planes", "Usuarios", 
	        dataset, PlotOrientation.VERTICAL, true,true, false);
	        chart.setBackgroundImage(img.getImage());
	        chart.getTitle().setPaint(Color.black); 
	        CategoryPlot p = chart.getCategoryPlot(); 
	        p.setRangeGridlinePaint(Color.red); 
	        contentPane.setLayout(null);
	        // Mostrar Grafico
	        ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setBounds(10, 11, 654, 370);
	        contentPane.add(chartPanel);
	    }
}
