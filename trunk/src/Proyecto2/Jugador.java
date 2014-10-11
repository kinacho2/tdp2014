package Proyecto2;

import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;

public class Jugador extends Nave {
	protected static ImageIcon icon;
	protected static ImageIcon iconDer;
	protected static ImageIcon iconIzq;
	protected int power;
	
	public Jugador(int vida, int vel,int x, int y, ImageIcon icon, ImageIcon iconDer, ImageIcon iconIzq){
		super(vida, vel, x, y, icon, icon.getIconWidth(), icon.getIconHeight());
		this.icon=icon;
		this.iconDer=iconDer;
		this.iconIzq=iconIzq;
		velocidad=vel;
		setJugador(this);
		velocidadMisil = 20;
		power=1;
	}
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_SPACE) {
            if(puedeDisparar()){
            	disparar();
            }
        }
       
        if (key == KeyEvent.VK_LEFT) {
            dx = -velocidad;
            image = iconIzq.getImage();
        }
        
        if (key == KeyEvent.VK_RIGHT) {
            dx = velocidad;
            image = iconDer.getImage();
        }
       
        if (key == KeyEvent.VK_UP) {
            dy = -velocidad;
        }
        
        if (key == KeyEvent.VK_DOWN) {
            dy = velocidad;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            image = icon.getImage();
        }
      
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            image = icon.getImage();
        }
        
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
       
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    
    public void disparar() {
    	
    	Disparo d = new Disparo(x + width/2 , y, 0, 1, velocidadMisil);
    	Disparo[] array = d.cloneNivel(power);
    	if(puedeDisparar()){
    		for(int i = 0;i<array.length;i++){
    			mapa.addDisparoJugador(array[i]);
    		}
    	}
    }

}
