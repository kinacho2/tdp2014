package ProyectoX.Disparos.Wave;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVII;

public class WaveLVIII extends DisparoWave {

	/**
	 * Constructor de la clase WaveLVIII
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public WaveLVIII(int x, int y, double dx, double dy) {
		super(x, y, dx, dy);
	}
	
	/**
	 * redefine nextLevel() de la clase Disparo
	 * retorna una instancia si mismo
	 * @return instancia de Disparo de tipo dinamico WaveLVIII
	 */
	
	public Disparo nextLevel(){
		return new WaveLVIII(x,y,dx,dy);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * @return Disparo[] con 2 elementos de tipo dinamico DisparoWave y 4 elementos de tipo dinamico WaveLVIII
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[6];
		
		d[0] = new WaveLVIII(x  - 10 , y, 2, -1);
		d[1] = new WaveLVIII(x  + 10 , y, -2, -1);
		d[2] = new DisparoWave(x  - 10 , y, 2, 1);
		d[3] = new DisparoWave(x  + 10 , y, -2, 1);
		d[4] = new WaveLVIII(x  - 10 , y, 2, 1);
		d[5] = new WaveLVIII(x  + 10 , y, -2, 1);
		setearReproductor(d);
		return d;
	}

	/**
	 * redefine move() de la clase DisparoWave
	 * genera un movimiento sinuidal diferente
	 */
	
	public void move(){
		y = (int)(y + dy*(velocidad + Math.sin(variacion) * velocidad)) ;
		x = x + (int)(dx*variacion)/4 + (int)(dx *Math.sin(variacion));
		variacion = variacion + 1;
		verificarColisionBorde();
	}
}
