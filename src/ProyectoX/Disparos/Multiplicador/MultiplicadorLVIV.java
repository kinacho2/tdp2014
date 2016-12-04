
package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Disparos.Laser.LaserLVI;
import ProyectoX.Disparos.Wave.WaveLVI;
import ProyectoX.Naves.Nave;

/**
 * Tercer nivel de DisparoMultiplicador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class MultiplicadorLVIV extends DisparoMultiplicador{

	
	double variacion = 0;
	private int contador=0;
	private static boolean estado;
	private static final String sound = "/ProyectoX/sounds/mul.mp3";
		
	/**
	 * Constructor de la clase MultiplicadorLVIII
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public MultiplicadorLVIV(int x, int y, double dx, double dy, Nave nave, int contador) {
		super(x, y, dx, dy, nave);
		
		sonido = sound;
		this.contador = contador;
		estado=true;
		damage=1;
	}
	
	/**
	 * redefine nextLevel() de la clase DisparoJugador
	 * retorna una instancia si mismo
	 * @return instancia de Disparo de tipo dinamico MultiplicadorLVIII
	 */
	
	public DisparoJugador nextLevel(){
		return new MultiplicadorLVIV(x,y,dx,dy,nave,contador);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * @return Disparo[] con 5 elementos
	 */
		
	public Disparo[] cloneNivel(){
		int max = 5;
		Disparo[] d = new Disparo[contador<max?13:9];
		d[0] = new DisparoJugador(x-width/4, y, 0, 1, velocidad,null);
		d[1] = new DisparoJugador(x+30-width/2, y, 0, 1, velocidad,null);
		d[2] = new DisparoJugador(x-30, y, 0, 1, velocidad,null);
		d[3] = new DisparoJugador(x - width/4, y + 30, 0.4d, -1, velocidad,null);
		d[4] = new DisparoJugador(x - width/4, y + 30, -0.4d, -1, velocidad,null);
		d[5] = new DisparoJugador(x, y, 0.4d, -0.1d, velocidad*3/2,null);
		d[6] = new DisparoJugador(x, y, -0.4d, -0.1d, velocidad*3/2,null);
		d[7] = new DisparoJugador(x, y, 0.4d, 0.1d, velocidad*3/2,null);
		d[8] = new DisparoJugador(x, y, -0.4d, 0.1d, velocidad*3/2,null);

		if(contador<max){
			contador++;
			d[9] = new MultiplicadorLVIV(x, y, -1, 1,nave, 0);
			d[10] = new MultiplicadorLVIV(x, y, 1, 1,nave, 0);
			d[11] = new MultiplicadorLVIV(x, y, -1, -1,nave, 0);
			d[12] = new MultiplicadorLVIV(x, y, 1, -1,nave, 0);
		}
		setearReproductor(d);
		return d;
	}
	
	 public void move() {
		 if(dy==1){
	        y = nave.getY() + nave.getHeight()/2 -height/2 + (int)(80*dx*Math.sin(variacion));;
	        x = nave.getX() + nave.getWidth()/2 -width/2 + (int)(80*dx*Math.cos(variacion));
		 }
		 else{
			y = nave.getY() + nave.getHeight()/2 -height/2 + (int)(80*dx*Math.cos(variacion));
		    x = nave.getX() + nave.getWidth()/2 -width/2 + (int)(80*dx*Math.sin(variacion));
		 }
        variacion+=0.03;
        //verificarColisionBorde();
        if(!nave.getVisible() || !estado){
			 super.setVisible();
		}
	 }
	 
	 public DisparoJugador getLaser(){
		 estado=false;
		 return new LaserLVI(nave,1);
	}
		
		/**
		 * retorna una instancia de tipo dinamico WaveLVI
		 * @return instancia de WaveLVI
		 */
		
	public DisparoJugador getWave(){
		estado=false;
		return new WaveLVI(x,y,dx,dy,nave);
	}
	
	public void reset() {
		contador=0;
	}
	
	public void setVisible(){
		 
	}

	
	public int getLevel() {
		return 4;
	}
}


