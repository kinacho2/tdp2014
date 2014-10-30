package ProyectoX.Naves.Jugador.Defensa;

import javax.swing.ImageIcon;

import Proyecto2.Naves.Jugador.Jugador;

public abstract class Defensa extends Jugador{
	private String nombre;

	public Defensa(int vida, int vel, int x, int y,ImageIcon icon, ImageIcon iconDer, ImageIcon iconIzq, String nom){
		super(vida, vel, icon, iconDer, iconIzq);
		this.nombre = nom;
	}
	
	public String getNombre() {
		return nombre;
	}

}
