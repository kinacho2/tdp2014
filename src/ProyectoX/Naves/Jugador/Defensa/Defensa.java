package ProyectoX.Naves.Jugador.Defensa;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Jugador.Jugador;

public abstract class Defensa extends Jugador{
	protected int index;
	
	public Defensa(int vida, int vel,ImageIcon icon, ImageIcon iconDer, ImageIcon iconIzq){
		super(vida, vel, icon, iconDer, iconIzq);
	}
	
	public void setIndex(int i){
		index = i;
	}

	public void setVida(int vd){
		
		vida-=vd;	
		if(vida <= -0) 
			setVisible();
		
	}
	
	public Defensa getAyudante(){
		return new NaveAyudante(jugador);
	}
	
	public Defensa getEscudo(){
		return new EscudoX(jugador);
	}
}
