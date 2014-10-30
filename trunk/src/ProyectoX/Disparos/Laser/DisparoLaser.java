package ProyectoX.Disparos.Laser;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;

public abstract class DisparoLaser extends Disparo {
	

	protected Image laser;
	
	public DisparoLaser(int x, int y, ImageIcon ii) {
		super(x, y, 0, 1, 20);
		
		laser = ii.getImage();
		
		
	}

	public Image getImage(){
		return laser;
	}
}
