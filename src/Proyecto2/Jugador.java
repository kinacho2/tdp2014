package Proyecto2;

import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;

public class Jugador extends Nave {
	protected static ImageIcon icon;
	protected static ImageIcon iconDer;
	protected static ImageIcon iconIzq;
	
	
	public Jugador(URL url,URL urlDer, URL urlIzq){
		super(new ImageIcon(url),32,44);
		this.icon=new ImageIcon(url);
		this.iconDer=new ImageIcon(urlDer);
		this.iconIzq=new ImageIcon(urlIzq);
	}
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_SPACE) {
            disparar();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            image = iconIzq.getImage();
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            image = iconDer.getImage();
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
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
