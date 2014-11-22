package ProyectoX.Paneles;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ProyectoX.Aplication;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Normal;
import ProyectoX.Naves.Jugador.Resistente;
import ProyectoX.Naves.Jugador.Veloz;
import ProyectoX.Sound.OyenteSonido;
/**
 * Panel de seleccion de Jugador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */
public class PanelSelect  extends JPanel {

	private static final URL urlEn = (PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/sonido-enabled.png"));
	private static final URL urlDis = (PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/sonido-disabled.png"));
	
	private JButton resistente;
	private JButton sonido;
	private ImageIcon en,dis;
	private JButton normal;
	private JButton veloz;
	private JButton atras;
	private JLabel labelResistente;
	private JLabel labelNormal;
	private JLabel labelVeloz;
	private JLabel resistenteStatics, normalStatics, velozStatics, fondo;
	private Aplication api;
	private int round = 0;
	/**
	 * Constructor de la clase PanelSelect
	 * @param api Aplicacion principal del juego
	 */
	public PanelSelect(Aplication api){
		super();
		
		setLayout(null);
		setBackground(new java.awt.Color(0,0,0));
		this.api = api;
		
		{
			veloz = new JButton();
			add(veloz);
			veloz.setText("Akai Tenshi");
			veloz.setBounds(400 - 288/2, 88 +50, 288, 64);
			veloz.setBackground(new java.awt.Color(0,0,0));
			veloz.setFont(new java.awt.Font("Segoe UI",0,20));
			veloz.setForeground(new java.awt.Color(0,255,0));
			veloz.addActionListener(new OyenteVeloz());
		}
		{
			normal = new JButton();
			add(normal);
			normal.setText("Midori Tenshi");
			normal.setBounds(256, 188 +50, 288, 64);
			normal.setFont(new java.awt.Font("Segoe UI",0,20));
			normal.setForeground(new java.awt.Color(0,255,0));
			normal.setBackground(new java.awt.Color(0,0,0));
			normal.addActionListener(new OyenteNormal());
		}
		{
			resistente = new JButton();
			add(resistente);
			resistente.setText("Aoi Tenshi");
			resistente.setBounds(400 - 288/2, 288 +50, 288, 64);
			resistente.setBackground(new java.awt.Color(0,0,0));
			resistente.setForeground(new java.awt.Color(0,255,0));
			resistente.setFont(new java.awt.Font("Segoe UI",0,20));
			resistente.addActionListener(new OyenteResistente());
		}
		{
			labelVeloz = new JLabel();
			add(labelVeloz);
			ImageIcon icon = new ImageIcon(Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/veloz.gif"));
			labelVeloz.setIcon(icon);
			labelVeloz.setBounds(175 + icon.getIconWidth() / 2, 88 +50, icon.getIconWidth(), 64);
			labelVeloz.setOpaque(false);
			
			velozStatics = new JLabel();
			add(velozStatics);
			velozStatics.setFont(new java.awt.Font("Segoe UI",0,9));
			velozStatics.setForeground(new java.awt.Color(0,255,0));
			velozStatics.setText(Veloz.getEstadisticas());
			velozStatics.setBounds(80, 88 +50, 164, 64);
			velozStatics.setOpaque(false);
			
		}
		{
			labelNormal = new JLabel();
			add(labelNormal);
			ImageIcon icon = new ImageIcon(Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/normal.gif"));
			labelNormal.setIcon(icon);
			labelNormal.setBounds(175 + icon.getIconWidth() / 2, 188 +50, icon.getIconWidth(), 64);
			labelNormal.setOpaque(false);
			
			normalStatics = new JLabel();
			add(normalStatics);
			normalStatics.setFont(new java.awt.Font("Segoe UI",0,9));
			normalStatics.setForeground(new java.awt.Color(0,255,0));
			normalStatics.setText(Normal.getEstadisticas());
			normalStatics.setBounds(80, 188 +50, 164, 64);
			normalStatics.setOpaque(false);
			
		}
		{
			labelResistente = new JLabel();
			add(labelResistente);
			ImageIcon icon = new ImageIcon(Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/resistente.gif"));
			labelResistente.setIcon(icon);
			labelResistente.setBounds(175, 288 +50, icon.getIconWidth(), 64);
			labelResistente.setOpaque(false);
			
			resistenteStatics = new JLabel();
			add(resistenteStatics);
			resistenteStatics.setFont(new java.awt.Font("Segoe UI",0,9));
			resistenteStatics.setForeground(new java.awt.Color(0,255,0));
			resistenteStatics.setText(Resistente.getEstadisticas());
			resistenteStatics.setBounds(80, 288 +50, 164, 64);
			resistenteStatics.setOpaque(false);
		}
		{
			atras = new JButton();
			add(atras);
			ImageIcon ii = new ImageIcon(Nave.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/back.png"));
			atras.setIcon(new ImageIcon(ii.getImage().getScaledInstance(61,44,Image.SCALE_DEFAULT)));
			
			atras.setBounds(70, 461, 61, 44);
			atras.setBackground(new java.awt.Color(0,0,0));
			atras.setFont(new java.awt.Font("Segoe UI",0,20));
			atras.setForeground(new java.awt.Color(0,255,0));
			atras.addActionListener(new OyenteAtras());
		}
		{
			sonido = new JButton("");
			dis = new ImageIcon(urlDis);
			dis = new ImageIcon(dis.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
			en = new ImageIcon(urlEn);
			en = new ImageIcon(en.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
			
			sonido.setBounds(750, 10, 25, 25);
			sonido.setForeground(new java.awt.Color(0,255,0));
			sonido.setBackground(new java.awt.Color(0,0,0));
			sonido.setBorder(BorderFactory.createCompoundBorder(null,null));
			sonido.setFont(new java.awt.Font("Segoe UI",0,20));
			sonido.setFocusable(false);
			sonido.addActionListener(new OyenteSonido(sonido,api.getReproductor(),en,dis));
			add(sonido);
		}
		{
			
			ImageIcon icon = new ImageIcon(Nave.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/menu.gif"));
			fondo = new JLabel(icon);
			fondo.setBounds(0, 0, 800, 600);
			fondo.setBorder(null);
			add(fondo);
		}
		round = 0;
	}

	
	/**
	 * Implementa ActionListener
	 * Indica que se selecciono la Nave Veloz
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class OyenteVeloz implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			api.showSplashBeforeGame(1);
			
		}
	}
	
	/**
	 * Implementa ActionListener
	 * Indica que se selecciono la Nave Resistente
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class OyenteNormal implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			api.showSplashBeforeGame(2);
            
		}
	}
	
	/**
	 * Implementa ActionListener
	 * Indica que se selecciono la Nave Resistente
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class OyenteResistente implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			api.showSplashBeforeGame(3);
           
		}
	}
	
	/**
	 * Implementa ActionListener
	 * Retorna al primer panel
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class OyenteAtras implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			setVisible(false);
			api.initPrimerPanel();
		}
	}

	
}
