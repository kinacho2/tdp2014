package ProyectoX.Disparos.Laser;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;

public abstract class DisparoLaser extends Disparo {
	

	protected Image laser;
	protected int delay = 0;
	Jugador jugador;
	
	public DisparoLaser(int width, int height, ImageIcon ii, Jugador jugador) {
		super(0, 0, 0, 0, 0);
		this.jugador = jugador;
		this.height = height;
		this.width = width;
		laser = (new ImageIcon(ii.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT))).getImage();
		
	}

	public Image getImage(){
		return laser;
	}
	
	public boolean colision(Nave nave){
		boolean A, B, C, toRet = false;
		A = x > nave.getX();
		B = x + width < nave.getX() + nave.getWidth();
		C = y + height > nave.getY() + nave.getHeight();
		if(delay > 30 && delay < 70 ){
			toRet = A && B && C;
		}
		if(delay<70){
			delay++;
		}
		else{
			setVisible();
		}
		
		
		
		return toRet;
	}
	
	public void move(){
		x = jugador.getX() + jugador.getWidth()/2 - width/2;
		y = jugador.getY() - height;
	}
	
}
