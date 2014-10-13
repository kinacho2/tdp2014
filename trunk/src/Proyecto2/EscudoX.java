package Proyecto2;

import java.net.URL;
import javax.swing.ImageIcon;

public class EscudoX extends Defensa {

	protected static final URL url = (Nave.class.getClassLoader().getResource("img/PUP/escudo.gif"));
	protected static final int defaultVida = 20;
	
	
	public EscudoX(Jugador jugador){
		super(defaultVida, jugador.getVelocidad(), jugador.getX() - jugador.getWidth(), jugador.getY(), new ImageIcon(), new ImageIcon(), new ImageIcon(),"def");
	}
	
}