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
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Sound.Reproductor;

/**
 * Panel final del juego donde se muestra la puntuacion obtenida y la opcion de guardar el  puntaje
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class PanelFinal extends JPanel {

	
	private static final URL url = (PanelGameOver.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/gameover.gif"));

	private static final String sound = "/ProyectoX/sounds/music/outro2.mp3";
	
	private JButton atras;

	private boolean guardado = false;
	private Aplication api;
	private Reproductor rep;
	private Jugador jugador;
	private JLabel fondo;


	private JLabel puntuacion;

	private JLabel nombre;
	
	/**
	 * Constructor de la clase PanelFinal
	 * @param api Aplicacion del Juego
	 * @param jugador Jugador actual
	 */
	
	public PanelFinal(Aplication api,Reproductor rep,Jugador jugador){
		api.setVisible(false);
		rep.addSound(sound, true);
		this.rep = rep;
		this.api = api;
		this.jugador = jugador;
		setLayout(null);
		
		{
			atras = new JButton();
			add(atras);
			atras.setText("CONTINUAR");
			atras.setBounds(800/2-147/2, 478, 147, 29);
			atras.setForeground(new java.awt.Color(0,255,0));
			atras.setBackground(new java.awt.Color(0,0,0));
			atras.setBorder(BorderFactory.createCompoundBorder(null,null));
			atras.setFont(new java.awt.Font("Segoe UI",0,20));
			atras.addActionListener(new OyenteAtras());
		}
		{
			puntuacion = new JLabel();
			add(puntuacion);
			puntuacion.setText(jugador.getNombre());
			puntuacion.setBounds(102, 66, 280, 43);
			puntuacion.setForeground(new java.awt.Color(0,255,0));
			puntuacion.setBackground(new java.awt.Color(0,0,0));
			puntuacion.setFont(new java.awt.Font("Segoe UI",0,20));
		}
		{
			nombre = new JLabel();
			add(nombre);
			nombre.setText(""+jugador.getPuntaje());
			nombre.setBounds(625, 66, 112, 43);
			nombre.setForeground(new java.awt.Color(0,255,0));
			nombre.setBackground(new java.awt.Color(0,0,0));
			nombre.setFont(new java.awt.Font("Segoe UI",0,20));
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
	 * Implementa ActionListener
	 * Retorna al primer panel
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class OyenteAtras implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!guardado){
				int verd;
	            
				JOptionPane dialogo = new JOptionPane();
		            
				verd = dialogo.showConfirmDialog(null, "Desea salir sin guardar su puntuación?", "Atencion",JOptionPane.YES_NO_OPTION);
					
				if(verd == 0){
					rep.stop(0);
					setVisible(false);
					api.initPrimerPanel();
				}
			}
			else{
				rep.stop(0);
				setVisible(false);
				api.initPrimerPanel();
			}
		}
	}
	

}
