package ProyectoX.Naves.Jugador.Defensa;

import javax.swing.ImageIcon;
import ProyectoX.Naves.Jugador.Jugador;

/**
 * Representa a la ayuda del Jugador 
 * poseen movimiento propio dependiente de la posicion del Jugaodor
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public abstract class Defensa extends Jugador{
	
	/**
	 * Cosntructor de la clase Defensa
	 * @param vida, cantidad de vida de la Defensa
	 * @param vel, cantidad de pixeles que se mueve por iteracion
	 * @param icon Imagen estatica vertical
	 * @param iconDer Imagen inclinada hacia la derecha
	 * @param iconIzq Imagen inclinada hacia la izquierda
	 */
	public Defensa(int vida, int vel,ImageIcon icon, ImageIcon iconDer, ImageIcon iconIzq){
		super(vida, vel, icon, iconDer, iconIzq);
	}
	
	/**
	 * redefine setVida(int vd) de la clase Jugador
	 * 
	 */

	public void setVida(int vd){
		
		vida-=vd;	
		if(vida <= -0) 
			setVisible();
		
	}
	
	/**
	 * @return instancia de NaveAyudante
	 */
	
	public Defensa getAyudante(){
		return new NaveAyudante(jugador);
	}
	
	/**
	 * @return instancia de EscudoX
	 */
	
	public Defensa getEscudo(){
		return new EscudoX(jugador);
	}
	
	/**
	 * las Defensas no son invulnerables
	 * return false
	 */
	
	public boolean isInvulnerable(){
		return false;
	}
}
