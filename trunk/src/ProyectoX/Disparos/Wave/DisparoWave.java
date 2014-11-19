package ProyectoX.Disparos.Wave;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;


public class DisparoWave extends DisparoJugador {

	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Wave/wave.png");

	
	protected int variacion = 0;
	
	/**
	 * Constructor de la clase DisparoWave
	 * setea una nueva imagen al Disparo
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 * 
	 */
	
	public DisparoWave(int x, int y, double dx, double dy,Nave nave) {
		super(x, y, dx, dy, -10,nave);
		ImageIcon ii = new ImageIcon(url);
        image = ii.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}
	
	/**
	 * redefine move() de la clase Disparo
	 * genera un movimiento sinuidal
	 */
	
	public void move(){
		y = y + (int)(dy*velocidad);
		x = x + (int)(dx * Math.sin(variacion) * velocidad);
		variacion = variacion + 1;
		verificarColisionBorde();
	}
	
	/**
	 * redefine getWave() de la clase DisparoJugador
	 * retorna el siguiente nivel de la instancia
	 */
	
	public DisparoJugador getWave(){
		return nextLevel();
	}
	

}
