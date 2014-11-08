package ProyectoX.Disparos.Wave;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;


public class DisparoWave extends Disparo {

	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Wave/wave.png");

	
	protected int variacion = 0;
	public DisparoWave(int x, int y, double dx, double dy) {
		super(x, y, dx, dy, -10);
		ImageIcon ii = new ImageIcon(url);
        image = ii.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}
	
	public void move(){
		y = y + (int)(dy*velocidad);
		x = x + (int)(dx * Math.sin(variacion) * velocidad);
		variacion = variacion + 1;
		verificarColisionBorde();
	}
	

}