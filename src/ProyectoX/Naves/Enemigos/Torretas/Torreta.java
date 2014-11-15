package ProyectoX.Naves.Enemigos.Torretas;

import java.awt.geom.AffineTransform;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Sound.Sonido;

public abstract class Torreta extends Enemigo {
	
	

	public Torreta(int vida, ImageIcon ii,int xx, int yy,int w, int h) {
		super(vida, 0, ii, w, h);
		x = xx;
		y = yy;
		velocidadMisil = -6;
		puntaje = 10;
		setFrecuenciaDeDisparo(5 , 50);
	}

	public void disparar() {
		if(puedeDisparar() && y < 600 && y > -width) {
			Disparo d = apuntarYDisparar();
			d.setPosicion((int)(d.getX() + (width/2)*Math.sin(rotacion)), (int)(d.getY() -height/2 - (height/2)*Math.cos(rotacion)));
			mapa.addDisparoEnemigo(d);
			reproductor.addSound(sonido,false);
		}
	}

	public boolean isEspecial() {
		return false;
	}

	public void move() {
		dx = jugador.getX() - x;
		dy = jugador.getY() - y;
		setRotacion();
	}
	
	public void setPosition(int xx, int yy){
		x += xx;
		y += yy;
		
	}
	
	public boolean colision(Nave n){
		return false;
	}
	
	public AffineTransform getRotacion() 
    {
        return AffineTransform.getRotateInstance(rotacion + Math.PI, getX() + getWidth()/2, getY() + getHeight()/2);
    }
	
	public int bomba(){
		return 0;
	}
	
	
}
