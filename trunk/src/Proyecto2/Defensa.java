package Proyecto2;

import javax.swing.ImageIcon;

public abstract class Defensa extends Jugador{
	private String nombre;

	public Defensa(int vida, int vel, int x, int y,ImageIcon icon, ImageIcon iconDer, ImageIcon iconIzq, String nom){
		super(vida, vel, x, y, icon, iconDer, iconIzq);
		this.nombre = nom;
	}
	
	public String getNombre() {
		return nombre;
	}

}

