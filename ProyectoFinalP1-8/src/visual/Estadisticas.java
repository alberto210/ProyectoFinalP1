package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
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
import logico.Factura;

public class Estadisticas extends JDialog {

	private JPanel contentPane = new JPanel();
	private  JFreeChart chart1;
	private  JFreeChart chart2;
	private ChartPanel chartPanel1;
	private ChartPanel chartPanel2;


	public Estadisticas() {
		getContentPane().setForeground(Color.BLUE);
		setTitle("Estadisticas");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Logo.jpg"));
		setBounds(100, 100, 450, 300);
		setSize(1417,811);
		setLocationRelativeTo(null);
		init();
		init2();
	}

	  private void init() {
	        contentPane.setForeground(Color.CYAN);

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
	        chart1 = ChartFactory.createBarChart3D
	        ("Cantidad de usuarios por plan","Planes", "Usuarios", 
	        dataset, PlotOrientation.VERTICAL, true,true, false);
	        chart1.setBackgroundImage(img.getImage());
	        chart1.getTitle().setPaint(Color.black); 
	        CategoryPlot p = chart1.getCategoryPlot(); 
	        p.setRangeGridlinePaint(Color.red); 
	        contentPane.setLayout(null);
	        // Mostrar Grafico
	        chartPanel1 = new ChartPanel(chart1);
	        chartPanel1.setBounds(10, 11, 1381, 370);
	        contentPane.add(chartPanel1);
	    }
	  	
	  private void init2() {

	        getContentPane().add(contentPane);
	        // Fuente de Datos
	        float montoEnero = 0;
	        float montoFebrero = 0;
	        float montoMarzo = 0;
	        float montoAbril = 0;
	        float montoMayo = 0;
	        float montoJunio = 0;
	        float montoJulio = 0;
	        float montoAgosto = 0;
	        float montoSeptiembre = 0;
	        float montoOctubre = 0;
	        float montoNoviembre = 0;
	        float montoDiciembre = 0;
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        for(Factura fac : Altice.getInstance().getMisFacturas()) {
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==0) {
	        		montoEnero += fac.getMontoPagado();
	        	}
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==1) {
	        		montoFebrero += fac.getMontoPagado();
	        	}
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==2) {
	        		montoMarzo += fac.getMontoPagado();
	        	}
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==3) {
	        		montoAbril += fac.getMontoPagado();
	        	}
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==4) {
	        		montoMayo += fac.getMontoPagado();
	        	}
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==5) {
	        		montoJunio += fac.getMontoPagado();
	        	}
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==6) {
	        		montoJulio += fac.getMontoPagado();
	        	}
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==7) {
	        		montoAgosto += fac.getMontoPagado();
	        	}
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==8) {
	        		montoSeptiembre += fac.getMontoPagado();
	        	}
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==9) {
	        		montoOctubre += fac.getMontoPagado();
	        	}
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==10) {
	        		montoNoviembre += fac.getMontoPagado();
	        	}
	        	if(fac.getEstado().equalsIgnoreCase("Pagada") && fac.getFechaEmision().getMonth()==11) {
	        		montoDiciembre += fac.getMontoPagado();
	        	}
	        }
	        dataset.setValue(montoEnero,"Ganancias","Enero");
	        dataset.setValue(montoFebrero,"Ganancias","Febrero");
	        dataset.setValue(montoMarzo,"Ganancias","Marzo");
	        dataset.setValue(montoAbril,"Ganancias","Abril");
	        dataset.setValue(montoMayo,"Ganancias","Mayo");
	        dataset.setValue(montoJunio,"Ganancias","Junio");
	        dataset.setValue(montoJulio, "Ganancias", "Julio");
	        dataset.setValue(montoAgosto, "Ganancias", "Agosto");
	        dataset.setValue(montoSeptiembre, "Ganancias", "Septiembre");
	        dataset.setValue(montoOctubre, "Ganancias", "Octubre");
	        dataset.setValue(montoNoviembre, "Ganancias", "Noviembre");
	        dataset.setValue(montoDiciembre, "Ganancias", "Diciembre");
	        
	        // Creando el Grafico
	        ImageIcon img = new ImageIcon(getClass().getResource("Fondo.png"));
	        chart2 = ChartFactory.createBarChart3D
	        ("Ventas por Mes","Meses", "Ventas", 
	        dataset, PlotOrientation.VERTICAL, true,true, false);
	        chart2.setBackgroundImage(img.getImage());
	        chart2.getTitle().setPaint(Color.black); 
	        CategoryPlot p = chart2.getCategoryPlot(); 
	        p.setRangeGridlinePaint(Color.red); 
	        contentPane.setLayout(null);
	        // Mostrar Grafico
	        chartPanel2 = new ChartPanel(chart2);
	        chartPanel2.setBounds(10, 392, 1381, 370);
	        contentPane.add(chartPanel2);
	    }
}
