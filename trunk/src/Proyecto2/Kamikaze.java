package Proyecto2;

import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public class Kamikaze extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/kamikaze.png"));

	private static final int defaultWidth = 40;
	private static final int defaultHeight = 32;
	private static final int defaultVel = 10;
	private static final int defaultVida = 3;

	
	public Kamikaze(){
		super(defaultVida,defaultVel,0,-defaultHeight,new ImageIcon(url),defaultWidth,defaultHeight);
		Random rand = new Random();
		x = rand.nextInt(800);
		
	}
	
	public synchronized void move(){
		
		if(y<300){
			dx=jugador.getX()-x;
			dy=jugador.getY()-y;
			double mod = Math.sqrt(dy*dy+dx*dx);
			setRotacion();
			//centra la direccion del Enemigo hacia el jugador
			dx+=jugador.getWidth()/2;
			dy+=jugador.getHeight()/2;
			if(dx!=0 || dy!=0){
				dx = dx / mod;
				dy = dy / mod;
			}
			else {
				dy=0.1d;
				dx=0.1d;
			}
			y+=dy*velocidad;
			x+=dx*velocidad;
		}
		else{
			y += dy*velocidad;
			x+=dx*velocidad;
		}
		verificarColision();
	}
	
	public void disparar(){
		
		if(puedeDisparar()){
			double dxAux=jugador.getX() - x + jugador.getWidth()/2;
			double dyAux=jugador.getY() - y + jugador.getHeight()/2;
			double mod = Math.sqrt(dyAux*dyAux+dxAux*dxAux);
			if(dxAux!=0 || dyAux!=0){
				dxAux = dxAux / mod;
				dyAux = dyAux / mod;
			}
			else {
				dyAux=0.1d;
				dxAux=0.1d;
			}
			if(y<300)
				mapa.addDisparoEnemigo(new Disparo(x + width/2 , y+height, -dxAux, dyAux, velocidadMisil));
		}
	}
}
