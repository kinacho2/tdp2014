package ProyectoX.Disparos;

import ProyectoX.Disparos.Laser.LaserLVI;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Disparos.Wave.WaveLVI;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;

public class DisparoJugador extends Disparo{

	
	protected Nave nave;
	
	public DisparoJugador(int x, int y, double dx, double dy, int missileSpeed, Nave nave) {
		super(x, y, dx, dy, missileSpeed);
		this.nave = nave;
	}
	
	/**
	 * Retorna el siguiente nivel del disparo 
	 * en esta clase base es el mismo disparo
	 * @return instancia de Disparo
	 */
	
	public DisparoJugador nextLevel(){
		return new DisparoJugador(x,y,dx,dy,velocidad,nave);
	}
	
	public DisparoJugador getLaser(){
		return new LaserLVI(nave,1);
	}
	public DisparoJugador getMultiplicador(){
		return new MultiplicadorLVI(x,y,dx,dy,nave);
	}
	public DisparoJugador getWave(){
		return new WaveLVI(x,y,dx,dy,nave);
	}
	
	public void setAtributes(int x, int y, double dx, double dy){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

}
