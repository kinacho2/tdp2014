package ProyectoX.Sound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Activa o desactiva el Reproductor de sonido segun corresponda
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class OyenteSonido implements ActionListener{

	private static boolean enabled;
	private JButton sonido;
	private ImageIcon en, dis;
	private Reproductor rep;
	
	/**
	 * 
	 * @param sonido JButton al que seteara la etiqueta
	 * @param rep Reproductor actual
	 * @param en ImageIcon de sonido habilitado
	 * @param dis ImageIcon de sonido desabilitado
	 */

	public OyenteSonido(JButton sonido, Reproductor rep, ImageIcon en, ImageIcon dis){
		this.sonido = sonido;
		this.en = en;
		this.dis = dis;
		this.rep = rep;
		if(rep.getEnabled()){
			sonido.setIcon(en);
		}
		else{
			sonido.setIcon(dis);
		}
	}
	
	/**
	 * Define la operacion actionPerformed(ActionEvent arg0) de ActionListener
	 * habilita o desabilita el sonido
	 */
	
	public void actionPerformed(ActionEvent arg0) {
		enabled = !rep.getEnabled();
		rep.setEnabled(enabled);
		if(rep.getEnabled()){
			sonido.setIcon(en);
		}
		else{
			sonido.setIcon(dis);
		}
		
	}
	
}