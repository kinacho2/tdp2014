package ProyectoX.Naves.Jugador.Defensa;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Jugador.Jugador;

public abstract class Defensa extends Jugador{
	
	public Defensa(int vida, int vel,ImageIcon icon, ImageIcon iconDer, ImageIcon iconIzq){
		super(vida, vel, icon, iconDer, iconIzq);
	}
	
	

}
