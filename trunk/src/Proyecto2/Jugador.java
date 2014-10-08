package Proyecto2;

import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;

public class Jugador extends Nave {
	protected static URL url = (Nave.class.getClassLoader().getResource("img/Jugador/Balanceado.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("img/Jugador/BalanceadoDerecha.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("img/Jugador/BalanceadoIzquierda.gif"));
	
	
	public Jugador(URL url,URL urlDer, URL urlIzq){
		super(new ImageIcon(url),32,44);
		this.url=url;
		this.urlDer=urlDer;
		this.urlIzq=urlIzq;
	}
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_SPACE) {
            disparar();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
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
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

}
