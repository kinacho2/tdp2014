package Proyecto2;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Enemigo extends Nave{
	
	
	public Enemigo(int vida, int vel, int x, int y, ImageIcon ii,int w, int h){
		super(vida,vel, x, y, ii, w, h);
		velocidadMisil = -5;
		longDis=5;
	}
	
	
	public void verificarColision(){
		if (colision(jugador)) {
			int vd = getDañoColision();
			jugador.setVida(vida);
			setVida(jugador.getDañoColision());
			
		}
	}
	
	public Jugador getJugador(){
		return jugador;
	}
	
	public void disparar() {
    	if(puedeDisparar())
    		mapa.addDisparoEnemigo(new Disparo(x + width/2 , y+height, 0, 1, velocidadMisil));
    }
	
	
}
