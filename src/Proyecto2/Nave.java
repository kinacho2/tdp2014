package Proyecto2;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Nave {
	
	
	
	protected int vida, x, y, dx, dy;
	//imagen de la nave
	protected Image image;
	
	//url de la image
	
	private ArrayList missiles;
	
	//largo y ancho de la image
	
	protected int height;
	protected int width;
	
	// basado en el codigo http://zetcode.com/tutorials/javagamestutorial/movingsprites/ para mover el jugador
	public Nave(ImageIcon icon, int w, int h){
		
		height = h;
		width = w;
		
		//ajusta la imagen al tamaño de los parametros w = ancho y h = alto
		ImageIcon ii = new ImageIcon(icon.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
		
		image = ii.getImage();
		
		missiles = new ArrayList();
		x = 40;
		y = 60;
	}
	
	
	 public void move() {
	        x += dx;
	        y += dy;
    }
	 
	public int getX() {
	        return x;
    }

    public int getY() {
        return y;
    }
     
    
    public Image getImage() {
		return image;
	}
    
    public ArrayList getMissiles() {
        return missiles;
    }
    
    public void disparar() {
        missiles.add(new Disparo(x + width/2 , y ));
    }

	
    


}
