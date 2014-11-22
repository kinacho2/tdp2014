package ProyectoX.Paneles;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ProyectoX.Aplication;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Minds.Ranking;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Sound.Reproductor;

/**
 * panel de fin de juego se encarga de redirigir el juego al comienzo del nivel o al primer panel de la aplicacion
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class PanelGameOver  extends JPanel {

	private static final URL url = (PanelGameOver.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/gameover.gif"));

	private static final String sound = "/ProyectoX/sounds/music/outro.mp3";
	
	private JButton yes,no;
	private JLabel label;
	private Aplication api;
	private Mapa mapa;
	private Reproductor rep;
	private Jugador jugador;
	private JLabel fondo;

	private JButton guardar;
	
	/**
	 * Constructor de la clase PanelGameOver
	 * @param api Aplicacion del Juego
	 * @param map Mapa actual del Juego
	 */
	
	public PanelGameOver(Aplication api, Mapa map,Jugador jugador){
		api.setVisible(false);
		rep = new Reproductor();
		rep.addSound(sound, true);
		this.api = api;
		this.mapa = map;
		this.jugador = jugador;
		setLayout(null);
		{
			yes = new JButton();
			add(yes);
			yes.setText("SI");
			yes.setBounds(234, 473, 41, 29);
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
			no.setBounds(375, 473, 41, 29);
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
			label.setBounds(800/2 - 155/2, 418, 155, 33);
			label.setForeground(new java.awt.Color(0,255,0));
			label.setBackground(new java.awt.Color(0,0,0));
			label.setBorder(BorderFactory.createCompoundBorder(null,null));
			label.setFont(new java.awt.Font("Segoe UI",0,20));
		}
		{
			guardar = new JButton();
			add(guardar);
			guardar.setText("GUARDAR");
			guardar.setBounds(521, 475, 104, 25);
			guardar.setForeground(new java.awt.Color(0,255,0));
			guardar.setBackground(new java.awt.Color(0,0,0));
			guardar.setBorder(BorderFactory.createCompoundBorder(null,null));
			guardar.setFont(new java.awt.Font("Segoe UI",0,20));
			guardar.addActionListener(new OyenteGuardar());
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
	
	/**
	 * se encarga de redirigir el juego al comienzo del nivel actual
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class OyenteYes implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			rep.stop(0);
			setVisible(false);
			api.setVisible(false);
			mapa.reset();
			api.setVisible(true);

			
		}
		
	}
	
	/**
	 * se encarga de redirigir el juego al primer panel de la aplicacion
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class OyenteNo implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			rep.stop(0);
			setVisible(false);
			
			api.initPrimerPanel();
		}
		
	}
	
	/**
	 * se encarga de guardar la puntuacion en el archivo de Rankings
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class OyenteGuardar implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			
			int verd;
	            
			JOptionPane dialogo = new JOptionPane();
	            
			verd = dialogo.showConfirmDialog(null, "Desea guardar su puntuación?", "Atencion",JOptionPane.YES_NO_OPTION);
				
			if(verd == 0){
				Ranking ran = new Ranking();
				ran.insertNewPuntaje(jugador.getNombre(), jugador.getPuntaje());
			}
			
			
		}
	}
	
	
}
