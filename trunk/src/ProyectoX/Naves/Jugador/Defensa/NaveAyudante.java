package ProyectoX.Naves.Jugador.Defensa;

import java.net.URL;

import javax.swing.ImageIcon;

import Proyecto2.Naves.Jugador.Jugador;

public class NaveAyudante extends Defensa {

	
	protected static final URL url = (Defensa.class.getClassLoader().getResource("img/PUP/escudo.gif"));
	protected static final int defaultVida = 5;
	
	
	public NaveAyudante(Jugador jugador){
		super(defaultVida, jugador.getVelocidad(), jugador.getX() - jugador.getWidth(), jugador.getY(), new ImageIcon(), new ImageIcon(), new ImageIcon(),"def");
	}
}
