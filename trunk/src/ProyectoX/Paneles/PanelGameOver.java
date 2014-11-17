package ProyectoX.Paneles;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ProyectoX.Aplication;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Sound.Reproductor;

public class PanelGameOver  extends JPanel {

	private static final URL url = (PanelGameOver.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/gameover.gif"));

	private static final String volver = "/ProyectoX/sounds/music/menu_";
	private static final String sound = "/ProyectoX/sounds/music/outro.mp3";
	
	private JButton yes,no;
	private JLabel label;
	private Aplication api;
	private Mapa mapa;
	private Reproductor rep;

	private JLabel fondo;
	
	private Random rn ;
	
	public PanelGameOver(Aplication api, Mapa map){
		api.setVisible(false);
		rep = new Reproductor();
		rep.addSound(sound, true);
		this.api = api;
		this.mapa = map;
		rn = new Random();
		setLayout(null);
		{
			yes = new JButton();
			add(yes);
			yes.setText("SI");
			yes.setBounds(800/2 - 50 - 41, 372, 41, 29);
			yes.setForeground(new java.awt.Color(0,255,0));
			yes.setBackground(new java.awt.Color(0,0,0));
			yes.setBorder(BorderFactory.createCompoundBorder(null,null));
			yes.setFont(new java.awt.Font("Segoe UI",0,20));
			yes.addActionListener(new OyenteYes());

		}
		{
			no = new JButton();
			add(no);
			no.setText("No");
			no.setBounds(800/2 + 50, 372, 41, 29);
			no.setForeground(new java.awt.Color(0,255,0));
			no.setBackground(new java.awt.Color(0,0,0));
			no.setBorder(BorderFactory.createCompoundBorder(null,null));
			no.setFont(new java.awt.Font("Segoe UI",0,20));
			no.addActionListener(new OyenteNo());
		}
		{
			label = new JLabel();
			add(label);
			label.setText("Desea continuar?");
			label.setBounds(321, 318, 155, 33);
			label.setForeground(new java.awt.Color(0,255,0));
			label.setBackground(new java.awt.Color(0,0,0));
			label.setBorder(BorderFactory.createCompoundBorder(null,null));
			label.setFont(new java.awt.Font("Segoe UI",0,20));
		}
		{
			fondo = new JLabel();
			add(fondo);
			fondo.setIcon(new ImageIcon((new ImageIcon(url)).getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT)));
			fondo.setBounds(0, 0, 800, 600);
		}
		setBounds(0,0,800,600);
		api.add(this);
		api.setVisible(true);
	}
	
	private class OyenteYes implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			rep.stop(0);
			setVisible(false);
			api.setVisible(false);
			mapa.reset();
			api.setVisible(true);

			
		}
		
	}
	
	private class OyenteNo implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			rep.stop(0);
			setVisible(false);
			
			api.initPrimerPanel();
		}
		
	}
	
	
}
