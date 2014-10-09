package Proyecto2;

import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;

public class Jugador extends Nave {
	protected static ImageIcon icon;
	protected static ImageIcon iconDer;
	protected static ImageIcon iconIzq;
	protected int velocidad;
	
	public Jugador(int vel, ImageIcon icon, ImageIcon iconDer, ImageIcon iconIzq){
		super(icon,icon.getIconWidth(),icon.getIconHeight());
		this.icon=icon;
		this.iconDer=iconDer;
		this.iconIzq=iconIzq;
		velocidad=vel;
	}
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_SPACE) {
            disparar();
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

}
